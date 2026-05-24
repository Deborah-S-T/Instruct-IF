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
import metier.modele.Intervenant;

/**
 *
 * @author dsteferra
 */
public class IntervenantSerialisation extends Serialisation {
    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
       Intervenant intervenant = (Intervenant) request.getAttribute("intervenant");
       List<Demande> demandes = (List<Demande>) request.getAttribute("demandes");
       
       JsonObjectBuilder jsonIntervenant = Json.createObjectBuilder();
       
       jsonIntervenant.add("id", intervenant.getId());
       jsonIntervenant.add("login", intervenant.getLogin());
       jsonIntervenant.add("mail", intervenant.getMail());
       jsonIntervenant.add("motDePasse", intervenant.getMdp());
       jsonIntervenant.add("disponible", intervenant.getDisponible());
       jsonIntervenant.add("nbIntervention", intervenant.getNb_intervention());
       jsonIntervenant.add("niveauMax", intervenant.getNiveau_max());
       jsonIntervenant.add("niveauMin", intervenant.getNiveau_min());
       jsonIntervenant.add("telephone", intervenant.getTel());
       
       JsonArrayBuilder jsonDemandeArray = Json.createArrayBuilder();
       //for (Demande d : demandes)
       for (Demande d : intervenant.getHistoDemande())
       {
           JsonObjectBuilder jsonDemande = Json.createObjectBuilder();
           jsonDemande.add("id", d.getId());
           jsonDemande.add("date", d.getDate().toString());
           jsonDemande.add("matiere", d.getTheme().getMatiere().getNom());
           
           jsonDemandeArray.add(jsonDemande);
       }
       jsonIntervenant.add("histoDemandes", jsonDemandeArray);
       
       PrintWriter out = response.getWriter();
       String builtJson = jsonIntervenant.build().toString();
       out.print(builtJson);
       System.out.println("jsonContainer.build() : " + builtJson);
    }
}
