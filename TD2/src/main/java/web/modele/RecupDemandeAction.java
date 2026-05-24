/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.modele;

import jakarta.servlet.http.HttpServletRequest;
import metier.modele.Demande;
import metier.service.Service;

/**
 *
 * @author dsteferra
 */
public class RecupDemandeAction extends Action {
    @Override
    public void execute(HttpServletRequest request) {
        Service service = new Service();
        
        Long demandeId = Long.parseLong(request.getParameter("demandeId"));
        
        Demande demande = service.getDemandeById(demandeId);
        
        System.out.println("Demande récupéré : " + demande);
        request.setAttribute("demande", demande);
    }
}
