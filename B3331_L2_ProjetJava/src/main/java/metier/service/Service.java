/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metier.service;

import dao.ThemeDao;
import dao.DemandeDao;
import dao.EleveDao;
import dao.EtablissementDao;
import dao.IntervenantDao;
import dao.JpaUtil;
import dao.MatiereDao;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import metier.modele.Autre;
import metier.modele.Demande;
import metier.modele.Eleve;
import metier.modele.Etablissement;
import metier.modele.Etudiant;
import metier.modele.Intervenant;
import metier.modele.Matiere;
import metier.modele.Theme;
import util.Message;
import static util.Message.envoyerNotification;

public class Service {

    public void initialisation() {
        Matiere mat1 = new Matiere("Maths");
        Matiere mat2 = new Matiere("Physique");
        Theme te1 = new Theme("algebre", mat1);
        Theme te2 = new Theme("mécanique", mat2);
        Theme te3 = new Theme("électricité", mat2);
        Intervenant it1 = new Autre("sfavro", "sfavro@free.fr", "mdp", "0642049305", 3, 6, "sylvieprime");
        Intervenant it2 = new Etudiant("jfred", "jfred@free.fr", "mdp", "0729654215", 3, 6, "insalyon", "informatique");
        
        
        IntervenantDao intervenantDao = new IntervenantDao();
        ThemeDao themeDao = new ThemeDao();
        MatiereDao matiereDao = new MatiereDao();

        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();

            intervenantDao.create(it1);
            intervenantDao.create(it2);
            
            themeDao.create(te1);
            themeDao.create(te2);
            themeDao.create(te3);
            
            matiereDao.create(mat1);
            matiereDao.create(mat2);

            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }

    public boolean inscrireEleve(Eleve eleve, String code_etablissement) {
        boolean reussite = true;
        EleveDao EleveDao = new EleveDao();
        EtablissementDao EtablissementDao = new EtablissementDao();

        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            Etablissement et1 = EtablissementDao.findByCode(code_etablissement);
            if (et1 == null) {
                et1 = new Etablissement(code_etablissement);
                Boolean eta_trouve = this.trouverEtablissement(et1);

                if (eta_trouve == false) {
                    reussite = false;
                } else {
                    EtablissementDao.create(et1);
                }
            }
            if (reussite == true) {
                eleve.setEtablissement(et1);
                EleveDao.create(eleve);
                JpaUtil.validerTransaction();
                Message.envoyerMail("ServiceInscription@instructif.com", eleve.getMail(), "Réussite inscription", "Inscription au réseau Instructif réalisée avec succès.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            reussite = false;
            Message.envoyerMail("ServiceInscription@instructif.com", eleve.getMail(), "Echec inscription", "L'inscription n'a pas abouti. Veuillez réessayer ultérieurement.");

        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return reussite;

    }

    public boolean trouverEtablissement(Etablissement et1) {
        Boolean reussite = true;
        JsonObject result = null;

        try {

            URI requestUri = URI.create(
                    "https://data.education.gouv.fr/api/explore/v2.1/catalog/datasets/fr-en-adresse-et-geolocalisation-etablissements-premier-et-second-degre/records"
                    + "?refine=numero_uai:" + URLEncoder.encode(et1.getCode(), StandardCharsets.UTF_8)
            );

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder(requestUri).GET().build();
            HttpResponse httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if (httpResponse.statusCode() == 200) {
                String body = (String) httpResponse.body();

                result = Json.createReader(new StringReader(body)).readObject();
            } else {
                throw new IOException("HTTP Error Status Code " + httpResponse.statusCode());
            }

        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            result = null;
            reussite = false;
        }
        Double coord_x = result.getJsonArray("results").get(0).asJsonObject().getJsonNumber("coordonnee_x").doubleValue();
        Double coord_y = result.getJsonArray("results").get(0).asJsonObject().getJsonNumber("coordonnee_y").doubleValue();
        String nom = result.getJsonArray("results").get(0).asJsonObject().getString("appellation_officielle");
        if (nom == null) {
            reussite = false;
        } else {
            try {
                URI requestUri = URI.create(
                        "https://data.education.gouv.fr/api/explore/v2.1/catalog/datasets/fr-en-ips_colleges/records"
                        + "?refine=uai:" + URLEncoder.encode(et1.getCode(), StandardCharsets.UTF_8) + "&order_by=rentree_scolaire%20DESC&limit=1"
                );
                HttpClient httpClient = HttpClient.newHttpClient();
                HttpRequest httpRequest = HttpRequest.newBuilder(requestUri).GET().build();
                HttpResponse httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

                if (httpResponse.statusCode() == 200) {
                    String body = (String) httpResponse.body();

                    result = Json.createReader(new StringReader(body)).readObject();
                } else {
                    throw new IOException("HTTP Error Status Code " + httpResponse.statusCode());
                }

            } catch (Exception ex) {
                ex.printStackTrace(System.err);
                result = null;
                reussite = false;
            }

            Double ips = result.getJsonArray("results").get(0).asJsonObject().getJsonNumber("ips").doubleValue();

            et1.setIps(ips);
            et1.setNom(nom);
            et1.setCoordonnee_x(coord_x);
            et1.setCoordonnee_y(coord_y);
        }
        return reussite;
    }

    public Intervenant intervenantLogIn(String login, String mdp) {
        IntervenantDao intervenantDao = new IntervenantDao();
        JpaUtil.creerContextePersistance();
        Intervenant it1 = intervenantDao.findByLogin(login);
        JpaUtil.fermerContextePersistance();
        if (!(it1 != null && it1.getMdp().equals(mdp))) {
            it1 = null;
        }
        return it1;
    }

    public Eleve eleveLogIn(String mail, String mdp) {
        EleveDao eleveDao = new EleveDao();
        JpaUtil.creerContextePersistance();
        Eleve e1 = eleveDao.findByMail(mail);
        JpaUtil.fermerContextePersistance();

        if (!(e1 != null && e1.getMotDePasse().equals(mdp))) {
            e1 = null;
        }
        return e1;
    }

    public boolean creationDemande(String description, Theme theme, Eleve eleve, String lien) {
        IntervenantDao intDao = new IntervenantDao();
        Boolean reussite = true;
        JpaUtil.creerContextePersistance();

        Intervenant prof = intDao.findIntervenant(eleve.getClasse());
        JpaUtil.fermerContextePersistance();

        if (prof == null) {
            reussite = false;
        } else {
            prof.setDisponible(false);

            Demande nvl_demande = new Demande(description, lien, LocalDate.now(), null, null, false, theme, eleve, prof);
            DemandeDao demandeDao = new DemandeDao();
            try {
                JpaUtil.creerContextePersistance();
                JpaUtil.ouvrirTransaction();

                intDao.update(prof);
                demandeDao.create(nvl_demande);

                JpaUtil.validerTransaction();
                envoyerNotification(prof.getTel(), "Nouvelle demande de soutien ! Connectez-vous pour voir son contenu.");
            } catch (Exception ex) {
                ex.printStackTrace();
                JpaUtil.annulerTransaction();
                reussite = false;
            } finally {
                JpaUtil.fermerContextePersistance();

            }
        }
        return reussite;
    }

    public List<Theme> listerThemes() {
        ThemeDao teDao = new ThemeDao();
        JpaUtil.creerContextePersistance();
        List<Theme> listeThemes = teDao.getListeThemes();
        JpaUtil.fermerContextePersistance();

        return listeThemes;
    }

    public List<Matiere> listerMatieres() {
        MatiereDao mtDao = new MatiereDao();
        JpaUtil.creerContextePersistance();
        List<Matiere> listeMatieres = mtDao.getListeMatieres();
        JpaUtil.fermerContextePersistance();

        return listeMatieres;
    }

    public Theme recupererThemeSelectionne(String nomtheme) {
        ThemeDao tdao = new ThemeDao();
        JpaUtil.creerContextePersistance();
        Theme t = tdao.getThemeSelected(nomtheme);
        JpaUtil.fermerContextePersistance();

        return t;
    }

    public void cloreDemande(Intervenant it, String bilan, Integer duree) {
        IntervenantDao intDao = new IntervenantDao();
        DemandeDao demDao = new DemandeDao();
        EleveDao eleDao = new EleveDao();

        it.setDisponible(true);

        JpaUtil.creerContextePersistance();
        Demande demandeEnCours = intDao.recupererDemandeEnCours(it);

        demandeEnCours.setBilan(bilan);
        demandeEnCours.setduree(duree);
        demandeEnCours.setTermine(true);
        it.addDemande(demandeEnCours);
        Eleve eleve = demandeEnCours.getEleve();
        eleve.addDemande(demandeEnCours);

        try {
            JpaUtil.ouvrirTransaction();
            intDao.update(it);

            demDao.update(demandeEnCours);
            intDao.update(it);
            eleDao.update(eleve);

            JpaUtil.validerTransaction();
            Message.envoyerMail("ServiceInscription@instructif.com", eleve.getMail(), "Bilan de la séance", bilan);
        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }

    public List<Demande> historiqueDemandeEleve(Eleve e1) {
        EleveDao eleveDao = new EleveDao();
        JpaUtil.creerContextePersistance();
        List<Demande> listeDemande = eleveDao.findHistoDemandeByID(e1);
        JpaUtil.fermerContextePersistance();
        return listeDemande;
    }

    public List<Demande> historiqueDemandeIntervenant(Intervenant it) {
        IntervenantDao intDao = new IntervenantDao();
        JpaUtil.creerContextePersistance();
        List<Demande> listeDemande = intDao.findHistoDemandeByID(it);
        JpaUtil.fermerContextePersistance();
        return listeDemande;
    }

    public Integer statsNbSoutien(Intervenant i1) {
        return i1.getHistoDemande().size();
    }

    public Integer statsMoyenneDureeSoutien(Intervenant i1) {
        Integer moyenne = 0;
        Integer nombreSoutien = i1.getHistoDemande().size();
        List<Demande> h1 = i1.getHistoDemande();
        for (Demande demande : h1) {
            moyenne += demande.getduree();
        }
        if (nombreSoutien != 0) {
            moyenne = moyenne / nombreSoutien;
        } else {
            moyenne = 0;
        }
        return moyenne;
    }

    public Integer statsNbEleve(Intervenant i1) {
        Integer nbEleve = 0;
        IntervenantDao intDao = new IntervenantDao();
        JpaUtil.creerContextePersistance();
        nbEleve = intDao.findNbEleveByInt(i1).size();
        JpaUtil.fermerContextePersistance();
        return nbEleve;
    }

    public Long statsNbEleveEtablissement(Etablissement et1) {
        EtablissementDao edao = new EtablissementDao();
        JpaUtil.creerContextePersistance();
        Long nbEleves = edao.nbEleveEtablissement(et1);
        JpaUtil.fermerContextePersistance();
        return nbEleves;
    }

    public List<Etablissement> getListeEtablissments() {
        EtablissementDao edao = new EtablissementDao();
        JpaUtil.creerContextePersistance();
        List<Etablissement> liste_res = edao.findAll();
        JpaUtil.fermerContextePersistance();
        return liste_res;
    }
    
    public List<Integer> statsProportionsDurees(Intervenant it){
        Integer soutien_inf20 = 0, soutien_20et40 = 0, soutien_40et60 = 0, soutien_sup60 = 0, i;
        List <Demande> histo_demande = this.historiqueDemandeIntervenant(it);
        for (i = 0 ; i < histo_demande.size() ; i++)
        {
           if (histo_demande.get(i).getDuree() <= 20)
           {
               soutien_inf20 ++;
           }
           else if (20 < histo_demande.get(i).getDuree() && histo_demande.get(i).getDuree() <= 40)
           {
               soutien_20et40 ++;
           }
           else if (40 < histo_demande.get(i).getDuree() && histo_demande.get(i).getDuree() <= 60)
           {
               soutien_40et60 ++;
           }
           else
           {
               soutien_sup60 ++;
           }
        }
        List<Integer> liste_proportions = Arrays.asList(soutien_inf20, soutien_20et40, soutien_40et60, soutien_sup60);
        return liste_proportions;
    } 
    
    public Matiere getMatiereById(long id) {
        MatiereDao mdao = new MatiereDao();
        JpaUtil.creerContextePersistance();
        Matiere m = mdao.findById(id);
        JpaUtil.fermerContextePersistance();
        return m;
    }
    
    public Theme getThemeById(long id) {
        ThemeDao tdao = new ThemeDao();
        JpaUtil.creerContextePersistance();
        Theme t = tdao.findById(id);
        JpaUtil.fermerContextePersistance();
        return t;
    }
    
    public Eleve getEleveById(long id) {
        EleveDao edao = new EleveDao();
        JpaUtil.creerContextePersistance();
        Eleve e = edao.findById(id);
        JpaUtil.fermerContextePersistance();
        return e;
    }
    
    public Demande getDemandeById(long id) {
        DemandeDao ddao = new DemandeDao();
        JpaUtil.creerContextePersistance();
        Demande d = ddao.findById(id);
        JpaUtil.fermerContextePersistance();
        return d;
    }
    
    public Intervenant getIntervenantById(long id) {
        IntervenantDao idao = new IntervenantDao();
        JpaUtil.creerContextePersistance();
        Intervenant i = idao.findById(id);
        JpaUtil.fermerContextePersistance();
        return i;
    }
}
