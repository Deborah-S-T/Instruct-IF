/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.modele;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import metier.modele.Demande;
import metier.modele.Intervenant;
import metier.service.Service;

/**
 *
 * @author dsteferra
 */
public class InfoInterventionAction extends Action {
    @Override
    public void execute(HttpServletRequest request) {
        Service service = new Service();
        HttpSession s = request.getSession();
        
        System.out.println("intervenantId : " + s.getAttribute("intervenantId"));
        Long idIntervenant = Long.parseLong(s.getAttribute("intervenantId").toString());
        Intervenant intervenant = service.getIntervenantById(idIntervenant);
        
        Boolean dispo = intervenant.getDisponible();
        Demande demande = (Demande) null;
        
        if (!dispo) {
            List<Demande> demandes = intervenant.getHistoDemande();
            for (Demande d : demandes) {
                System.out.println("ièmé demande : " + d);
                if (!(d.getTermine())) {
                    if (d.getIntervenant().getId().equals(idIntervenant)) {
                        demande = d;
                    }
                }
            }
        }
        
        
        
        System.out.println("L'intervenant est dispo : " + dispo + " sa demande est : " + demande);
        request.setAttribute("dispo", dispo);
        request.setAttribute("demande", demande);
    }
}
