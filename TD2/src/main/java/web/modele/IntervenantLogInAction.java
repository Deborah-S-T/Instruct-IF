/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.modele;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import metier.modele.Intervenant;
import metier.service.Service;

/**
 *
 * @author dsteferra
 */
public class IntervenantLogInAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        request.setAttribute("reussit", false);
        
        Service service = new Service();
        
        var login = request.getParameter("login");
        var mdp = request.getParameter("password");
        System.out.println("login : " + login + " mdp : " + mdp);
        Intervenant intervenant = service.intervenantLogIn(login, mdp);
        
        if (intervenant != null) {
            HttpSession s = request.getSession();
            s.setAttribute("intervenantId", intervenant.getId());
            request.setAttribute("reussit", true);
        }
    }
    
}