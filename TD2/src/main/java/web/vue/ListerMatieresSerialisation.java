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
import metier.modele.Matiere;

/**
 *
 * @author dsteferra
 */
public class ListerMatieresSerialisation extends Serialisation {
    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
       List<Matiere> listeMatieres = (List<Matiere>) request.getAttribute("listeMatieres");
       
       JsonObjectBuilder jsonContainer = Json.createObjectBuilder();
       
       JsonArrayBuilder jsonListeMatieres = Json.createArrayBuilder();
       
       for (Matiere m : listeMatieres) {
           System.out.println("m : " + m);
           JsonObjectBuilder jsonMatiere = Json.createObjectBuilder();
           jsonMatiere.add("id", m.getId());
           jsonMatiere.add("nom", m.getNom());
           jsonListeMatieres.add(jsonMatiere);
       }
       
       jsonContainer.add("matieres", jsonListeMatieres);
       
       PrintWriter out = response.getWriter();
       String builtJson = jsonContainer.build().toString();
       out.print(builtJson);
       System.out.println("jsonContainer.build() : " + builtJson);
    }
}
