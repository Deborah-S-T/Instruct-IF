/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.modele;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import metier.modele.Demande;
import metier.modele.Intervenant;
import metier.service.Service;

/**
 *
 * @author dsteferra
 */
public class CloreDemandeAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        
        Service service = new Service();
        
        Integer duree = Integer.parseInt(request.getParameter("duree"));
        var bilan = request.getParameter("bilan");
        System.out.println("duree dans clore action : " + duree + ", bilan : " + bilan);
        
        HttpSession s = request.getSession();
        Long idIntervenant = Long.parseLong(s.getAttribute("intervenantId").toString());
        Intervenant intervenant = service.getIntervenantById(idIntervenant);
        
        service.cloreDemande(intervenant, bilan, duree);
        request.setAttribute("reussit", true);
        
    }
    
}

