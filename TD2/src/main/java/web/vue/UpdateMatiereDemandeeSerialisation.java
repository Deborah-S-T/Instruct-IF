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
public class UpdateMatiereDemandeeSerialisation extends Serialisation{
    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
       Matiere matiere = (Matiere) request.getAttribute("matiere");
       
       JsonObjectBuilder jsonContainer = Json.createObjectBuilder();
       
       jsonContainer.add("matiere", matiere.getId());
       
       PrintWriter out = response.getWriter();
       String builtJson = jsonContainer.build().toString();
       out.print(builtJson);
       System.out.println("jsonContainer.build() : " + builtJson);
    }
}
