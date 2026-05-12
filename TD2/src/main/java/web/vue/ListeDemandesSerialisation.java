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
import java.math.BigDecimal;
import java.util.List;
import web.test.DemandeTest;

/**
 * private Long id;
    private LocalDateTime dateCreation;
    private String description;

 * @author dsteferra
 */
public class ListeDemandesSerialisation extends Serialisation{

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
       List<DemandeTest> listeDemandes = (List<DemandeTest>) request.getAttribute("listeDemandes");
       
       JsonObjectBuilder jsonContainer = Json.createObjectBuilder();
       
       JsonArrayBuilder jsonListeDemande = Json.createArrayBuilder();
       
       for (DemandeTest d : listeDemandes) {
           System.out.println("d : " + d);
           JsonObjectBuilder jsonDemande = Json.createObjectBuilder();
           jsonDemande.add("id", d.getId());
           jsonDemande.add("dateCreation", d.getDateCreation().toString());
           jsonDemande.add("description", d.getDescription());
           
           jsonListeDemande.add(jsonDemande);
       }
       
       jsonContainer.add("demandes", jsonListeDemande);
       
       PrintWriter out = response.getWriter();
       out.print(jsonContainer.build().toString());
       System.out.println(jsonContainer.build().toString());
    }
}
