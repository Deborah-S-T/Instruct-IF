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
import metier.modele.Theme;

/**
 *
 * @author dsteferra
 */
public class ListerThemesSerialisation extends Serialisation {
    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
       List<Theme> listeThemes = (List<Theme>) request.getAttribute("listeThemes");
       
       JsonObjectBuilder jsonContainer = Json.createObjectBuilder();
       
       JsonArrayBuilder jsonListeThemes = Json.createArrayBuilder();
       
       for (Theme t : listeThemes) {
           System.out.println("t : " + t);
           JsonObjectBuilder jsonTheme = Json.createObjectBuilder();
           jsonTheme.add("id", t.getId());
           jsonTheme.add("nom", t.getNom());
           jsonListeThemes.add(jsonTheme);
       }
       
       jsonContainer.add("themes", jsonListeThemes);
       
       PrintWriter out = response.getWriter();
       String builtJson = jsonContainer.build().toString();
       out.print(builtJson);
       System.out.println("jsonContainer.build() : " + builtJson);
    }
}
