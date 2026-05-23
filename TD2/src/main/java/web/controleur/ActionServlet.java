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
import metier.service.Service;
import web.modele.Action;
import web.modele.ConsulterListeDemandesAction;
import web.modele.CreationDemandeAction;
import web.modele.EleveLogInAction;
import web.modele.InscrireEleveAction;
import web.modele.IntervenantLogInAction;
import web.modele.ListerMatieresAction;
import web.modele.ListerThemesAction;
import web.modele.LogOutAction;
import web.modele.RecupThemeMatiereAction;
import web.modele.UpdateMatiereDemandeeAction;
import web.modele.mettreProfilAction;
import web.test.DemandeTest;
import web.vue.CreationDemandeSerialisation;
import web.vue.EleveSerialisation;
import web.vue.ListeDemandesSerialisation;
import web.vue.ListerMatieresSerialisation;
import web.vue.ListerThemesSerialisation;
import web.vue.RecupThemeMatiereSerialisation;
import web.vue.ReussiteSerialisation;
import web.vue.Serialisation;
import web.vue.UpdateMatiereDemandeeSerialisation;

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
//            case "consulter-liste-demandes": {
//                System.out.println("case 1");  
//                action = new ConsulterListeDemandesAction();
//                serialisation = new ListeDemandesSerialisation();
//                action.execute(request);
//                serialisation.appliquer(request, response);
//                break;
//            }
            case "eleve-connexion": {
                System.out.println("case eleve-connexion");
                action = new EleveLogInAction();
                action.execute(request);
                
                //serialisation
                System.out.println("eleveConnection réussit : " + request.getAttribute("reussit").toString());
                serialisation = new ReussiteSerialisation();
                serialisation.appliquer(request, response);
                
                HttpSession s = request.getSession();
                System.out.println("session att eleve id : " + s.getAttribute("eleveId"));
                break;
            }
            
            case "intervenant-connexion": {
                System.out.println("case intervenant-connexion");
                action = new IntervenantLogInAction();
                action.execute(request);
                
                //serialisation
                System.out.println("intervenantConnection réussit : " + request.getAttribute("reussit").toString());
                serialisation = new ReussiteSerialisation();
                serialisation.appliquer(request, response);
                
                HttpSession s = request.getSession();
                System.out.println("session att intervenant id : " + s.getAttribute("intervenantId"));
                break;
            }
            case "eleve-inscription": {
                System.out.println("case eleve-inscription");
                action = new InscrireEleveAction();
                action.execute(request);
                
                //serialisation
                System.out.println("eleveInscription réussit : " + request.getAttribute("reussit").toString());
                serialisation = new ReussiteSerialisation();
                serialisation.appliquer(request, response);
                break;
            }
            case "log-out": {
                System.out.println("case log-out");
                action = new LogOutAction();
                action.execute(request);
                
                //serialisation
                System.out.println("déconnexion réussit : " + request.getAttribute("reussit").toString());
                serialisation = new ReussiteSerialisation();
                serialisation.appliquer(request, response);
                break;
            }
            case "lister-matieres": {
                System.out.println("case lister-matieres");
                action = new ListerMatieresAction();
                action.execute(request);
                
                //serialisation
                serialisation = new ListerMatieresSerialisation();
                serialisation.appliquer(request, response);
                break;
            }
            case "lister-themes": {
                System.out.println("case lister-themes");
                action = new ListerThemesAction();
                action.execute(request);
                
                //serialisation
                serialisation = new ListerThemesSerialisation();
                serialisation.appliquer(request, response);
                break;
            }
            case "update-matiere" : {
                System.out.println("case update-matiere");
                action = new UpdateMatiereDemandeeAction();
                action.execute(request);
                
                //serialisation
                serialisation = new UpdateMatiereDemandeeSerialisation();
                serialisation.appliquer(request, response);
                break;
            }
            case "mettre-profil" : {
                System.out.println("case mettre-profil");
                action = new mettreProfilAction();
                action.execute(request);
                
                //serialisation
                serialisation = new EleveSerialisation();
                serialisation.appliquer(request, response);
                break;
            }
            case "recup-theme-matiere" : {
                System.out.println("case recup-theme-matiere");
                action = new RecupThemeMatiereAction();
                action.execute(request);
                
                //serialisation
                serialisation = new RecupThemeMatiereSerialisation();
                serialisation.appliquer(request, response);
                break;
            }
            case "creer-demande" : {
                System.out.println("case creer-demande");
                action = new CreationDemandeAction();
                action.execute(request);
                
                //serialisation
                System.out.println("creation demande réussit : " + request.getAttribute("reussit").toString());
                serialisation = new CreationDemandeSerialisation();
                serialisation.appliquer(request, response);
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
        Service service = new Service();
        service.initialisation();
        System.out.println("a initialise la base");
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
