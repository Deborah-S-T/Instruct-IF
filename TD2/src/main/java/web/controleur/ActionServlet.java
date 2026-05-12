/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package web.controleur;

import dao.JpaUtil;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import web.modele.Action;
import web.modele.ConsulterListeDemandesAction;
import web.modele.EleveLogInAction;
import web.modele.InscrireEleveAction;
import web.test.DemandeTest;
import web.vue.ListeDemandesSerialisation;
import web.vue.Serialisation;

/**
 *
 * @author dsteferra
 */
@WebServlet(name = "ActionServlet", urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        var todo = request.getParameter("todo");
        Action action;
        Serialisation serialisation;
        System.out.println("todo : " + todo);  
        switch(todo) {
            case "consulter-liste-demandes": {
                System.out.println("case 1");  
                action = new ConsulterListeDemandesAction();
                serialisation = new ListeDemandesSerialisation();
                action.execute(request);
                serialisation.appliquer(request, response);
                break;
            }
            case "eleve-connexion": {
                System.out.println("case 1");
                action = new EleveLogInAction();
                action.execute(request);
                
                //serialisation
                response.setContentType("application/json;charset=UTF-8");
                JsonObjectBuilder jsonLogged = Json.createObjectBuilder();
                System.out.println("attribut eleveConnecte : " + request.getAttribute("eleveConnecte").toString());
                jsonLogged.add("eleveConnecte", request.getAttribute("eleveConnecte").toString());
                PrintWriter out = response.getWriter();
                out.print(jsonLogged.build().toString());
                System.out.println("jsonLogged.build() : " + jsonLogged.build().toString());
                
                HttpSession s = request.getSession();
                System.out.println("session att eleve id : " + s.getAttribute("eleveId"));
                break;
            }
            case "eleve-inscription": {
                System.out.println("case 2");
                action = new InscrireEleveAction();
                action.execute(request);
                
                //serialisation
                response.setContentType("application/json;charset=UTF-8");
                JsonObjectBuilder jsonSigned = Json.createObjectBuilder();
                System.out.println("attribut eleveInscrit : " + request.getAttribute("eleveInscrit").toString());
                jsonSigned.add("eleveInscrit", request.getAttribute("eleveInscrit").toString());
                PrintWriter out = response.getWriter();
                out.print(jsonSigned.build().toString());
                System.out.println("jsonSigned.build() : " + jsonSigned.build().toString());
                break;
            }
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Action non reconnue");
                break;
        }
        
    }

    @Override
    public void init() throws ServletException {
        super.init(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        JpaUtil.creerFabriquePersistance();
    }

    @Override
    public void destroy() {
        JpaUtil.fermerFabriquePersistance();
        super.destroy(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
