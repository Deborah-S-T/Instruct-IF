/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.vue;

import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import metier.modele.Demande;

/**
 *
 * @author dsteferra
 */
public class DemandeSerialisation extends Serialisation {
    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
       Demande demande = (Demande) request.getAttribute("demande");

       JsonObjectBuilder jsonContainer = Json.createObjectBuilder();
       
       jsonContainer.add("id", demande.getId());
       jsonContainer.add("matiere", demande.getTheme().getMatiere().getNom());
       jsonContainer.add("theme", demande.getTheme().getNom());
       jsonContainer.add("loginInter", demande.getIntervenant().getLogin());
       jsonContainer.add("nomEleve", demande.getEleve().getNom());
       jsonContainer.add("prenomEleve", demande.getEleve().getPrenom());
       if (demande.getDuree() == null) {
           jsonContainer.add("duree", "null");
       }
       else {
           jsonContainer.add("duree", demande.getDuree().toString());
       }
       jsonContainer.add("date", demande.getDate().toString());
       if (demande.getBilan() == null) {
           jsonContainer.add("bilan", "null");
       }
       else {
           jsonContainer.add("bilan", demande.getBilan());
       }
       jsonContainer.add("description", demande.getDescription());
       if (demande.getLien()== null) {
           jsonContainer.add("lien", "null");
       }
       else {
           jsonContainer.add("lien", demande.getBilan());
       }
       jsonContainer.add("termine", demande.getTermine());
       
       PrintWriter out = response.getWriter();
       String builtJson = jsonContainer.build().toString();
       out.print(builtJson);
       System.out.println("jsonContainer.build() : " + builtJson);
    }
}
