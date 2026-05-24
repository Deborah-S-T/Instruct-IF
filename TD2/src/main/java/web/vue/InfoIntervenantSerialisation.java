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
import metier.modele.Demande;

/**
 *
 * @author dsteferra
 */
public class InfoIntervenantSerialisation extends Serialisation{
    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Boolean dispo = (Boolean) request.getAttribute("dispo");

        JsonObjectBuilder jsonContainer = Json.createObjectBuilder();

        jsonContainer.add("dispo", dispo);
        if (!dispo) {
            
            Demande demande = (Demande) request.getAttribute("demande");
            System.out.println("demande dans info inter ser : " + demande);
            jsonContainer.add("id", demande.getId());
            jsonContainer.add("matiere", demande.getTheme().getMatiere().getNom());
            jsonContainer.add("theme", demande.getTheme().getNom());
            jsonContainer.add("description", demande.getDescription());
            jsonContainer.add("lien", demande.getLien());
            jsonContainer.add("eleveId", demande.getEleve().getId());
        }
        else {
            jsonContainer.add("id", "-");
            jsonContainer.add("matiere", "-");
            jsonContainer.add("theme", "-");
            jsonContainer.add("description", "-");
            jsonContainer.add("lien", "-");
            jsonContainer.add("eleveId", "-");
        }
        

        PrintWriter out = response.getWriter();
        String builtJson = jsonContainer.build().toString();
        out.print(builtJson);
        System.out.println("jsonContainer.build() : " + builtJson);
    }
}
