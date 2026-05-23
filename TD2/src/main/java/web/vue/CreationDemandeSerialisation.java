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

/**
 *
 * @author dsteferra
 */
public class CreationDemandeSerialisation extends Serialisation {
    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObjectBuilder jsonLogged = Json.createObjectBuilder();
        jsonLogged.add("reussit", request.getAttribute("reussit").toString());
        jsonLogged.add("idDemande", request.getAttribute("idDemande").toString());
        jsonLogged.add("lienDemande", request.getAttribute("lienDemande").toString());

        PrintWriter out = response.getWriter();
        String builtJson = jsonLogged.build().toString();
        out.print(builtJson);
        System.out.println("jsonLogged.build() : " + builtJson);
    }
}
