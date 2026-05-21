/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.modele;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import metier.service.Service;
import metier.modele.Eleve;

/**
 *
 * @author dsteferra
 */
public class EleveLogInAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        request.setAttribute("reussit", false);
        
        Service service = new Service();
        
        var mail = request.getParameter("mail");
        var mdp = request.getParameter("password");
        System.out.println(mail + " " + mdp);
        Eleve eleve = service.eleveLogIn(mail, mdp);
        
        if (eleve != null) {
            HttpSession s = request.getSession();
            s.setAttribute("eleveId", eleve.getId());
            request.setAttribute("reussit", true);
        }
        
    }
    
}