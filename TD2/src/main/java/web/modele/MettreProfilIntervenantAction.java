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
public class MettreProfilIntervenantAction extends Action{
    @Override
    public void execute(HttpServletRequest request) {
        Service service = new Service();
        
        HttpSession s = request.getSession();
        System.out.println("intervenantid : " + s.getAttribute("intervenantId"));
        Long idIntervenant = Long.parseLong(s.getAttribute("intervenantId").toString());
        Intervenant intervenant = service.getIntervenantById(idIntervenant);
        List<Demande> demandes = service.historiqueDemandeIntervenant(intervenant);

        System.out.println("Eleve récupéré de la session : " + intervenant + " liste demandes : " + demandes);
        request.setAttribute("intervenant", intervenant);
        request.setAttribute("demandes", demandes);    
    }
}
