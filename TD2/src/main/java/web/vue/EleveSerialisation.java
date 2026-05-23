/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.vue;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObjectBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import metier.modele.Demande;
import metier.modele.Eleve;
import metier.modele.Etablissement;
import metier.service.Service;

/**
 * private Long id;
    private LocalDateTime dateCreation;
    private String description;

*  private Long id;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private String prenom;
    @Column(unique = true, nullable = false)
    private String mail;
    @Column(nullable = false)
    private String motDePasse;

    @Column(nullable = false)
    private LocalDate dateNaissance;
    @Column(nullable = false)
    private Integer classe;

    @ManyToOne
    private Etablissement etablissement;

    @OneToMany(mappedBy = "eleve")
    private List<Demande> histoDemandes;

* 
 * @author dsteferra
 */
public class EleveSerialisation extends Serialisation{

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
       Eleve eleve = (Eleve) request.getAttribute("eleve");
       List<Demande> demandes = (List<Demande>) request.getAttribute("demandes");
       
       JsonObjectBuilder jsonEleve = Json.createObjectBuilder();
       
       jsonEleve.add("id", eleve.getId());
       jsonEleve.add("nom", eleve.getNom());
       jsonEleve.add("prenom", eleve.getPrenom());
       jsonEleve.add("mail", eleve.getMail());
       jsonEleve.add("motDePasse", eleve.getMotDePasse());
       jsonEleve.add("dateNaissance", eleve.getDateNaissance().toString());
       jsonEleve.add("classe", eleve.getClasse());
       
       jsonEleve.add("idEtablissement", eleve.getEtablissement().getId().toString());
       JsonArrayBuilder jsonDemandeArray = Json.createArrayBuilder();
       //for (Demande d : demandes)
       for (Demande d : eleve.getHistoDemande())
       {
           JsonObjectBuilder jsonDemande = Json.createObjectBuilder();
           jsonDemande.add("id", d.getId());
           jsonDemande.add("date", d.getDate().toString());
           jsonDemande.add("matiere", d.getTheme().getMatiere().getNom());
           
           jsonDemandeArray.add(jsonDemande);
       }
       jsonEleve.add("histoDemandes", jsonDemandeArray);
       
       PrintWriter out = response.getWriter();
       String builtJson = jsonEleve.build().toString();
       out.print(builtJson);
       System.out.println("jsonContainer.build() : " + builtJson);
    }
}