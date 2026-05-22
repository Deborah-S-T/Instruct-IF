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
import metier.modele.Matiere;
import metier.modele.Theme;

/**
 *
 * @author dsteferra
 */
public class RecupThemeMatiereSerialisation extends Serialisation {
    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
       Matiere matiere = (Matiere) request.getAttribute("matiere");
        Theme theme = (Theme) request.getAttribute("theme");
       
       JsonObjectBuilder jsonContainer = Json.createObjectBuilder();
       
       jsonContainer.add("matiereNom", matiere.getNom());
       jsonContainer.add("themeNom", theme.getNom());
       
       PrintWriter out = response.getWriter();
       String builtJson = jsonContainer.build().toString();
       out.print(builtJson);
       System.out.println("jsonContainer.build() : " + builtJson);
    }
}
