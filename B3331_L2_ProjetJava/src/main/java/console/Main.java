/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package console;

import dao.JpaUtil;
import java.time.LocalDate;
import java.util.List;
import metier.modele.Demande;
import metier.modele.Eleve;
import metier.modele.Etablissement;
import metier.modele.Intervenant;
import metier.modele.Matiere;
import metier.modele.Theme;
import metier.service.Service;


public class Main {

    public static void main(String[] args) {
        JpaUtil.creerFabriquePersistance();
        Service service = new Service();
        
        //Initialisation de l'application : Ajout des thèmes, matières et intervenants
        service.initialisation();
        
        //Inscription d'un élève
        Eleve e1 = new Eleve("Siegel", "Arthur", "maildearthur","mdp", LocalDate.parse("2005-04-16"), 4);
        service.inscrireEleve(e1, "0691664J");
        
        //Connexion d'un élève
        Eleve e2 = service.eleveLogIn("maildearthur", "mdp");
        
        //Connexion d'un intervenant
        //Intervenant i1 = service.intervenantLogIn("sfavro", "mdp");
        Intervenant i1 = service.intervenantLogIn("sfavro", "dcgjhg");

        //Création d'une demande par un élève : la récupération du thème sélectionné se fait directement en fonction du nom
        service.creationDemande("description demande", service.recupererThemeSelectionne("algebre"), e1, "lien");
        
        //Cloture d'une demande par l'intervenant
        service.cloreDemande(i1,"bilan", 55);
        
        //Historique des demandes d'un élève
        List<Demande> histo_dem_el = service.historiqueDemandeEleve(e1);
        
        //Historique des demandes d'un intervenant
        List<Demande> histo_dem_it = service.historiqueDemandeIntervenant(i1);

        //Récupérer les matières et thèmes
        List<Theme> liste_themes = service.listerThemes();
        List<Matiere> liste_matieres = service.listerMatieres();
        
        //Statistiques 
        Integer stat_duree = service.statsMoyenneDureeSoutien(i1);
        Integer stat_nb_eleves = service.statsNbEleve(i1);
        Integer stat_nb_soutients = service.statsNbSoutien(i1);
        List <Integer> stat_proportions_soutien = service.statsProportionsDurees(i1);
        
        
        List<Etablissement> liste_etablissements = service.getListeEtablissments();
        
        Long stat_nb_eleve_etablissement = service.statsNbEleveEtablissement(liste_etablissements.get(0));
           
        JpaUtil.fermerFabriquePersistance();
    }
}
