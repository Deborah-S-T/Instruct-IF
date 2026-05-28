/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.modele;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import metier.modele.Demande;
import metier.modele.Eleve;
import metier.modele.Matiere;
import metier.modele.Theme;
import metier.service.Service;

/**
 *
 * @author dsteferra
 */
public class CreationDemandeAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        Service service = new Service();
        
        Long themeId = Long.parseLong(request.getParameter("theme"));
        String description = request.getParameter("description");
        System.out.println("themeid : " + themeId + " description : " + description);
        
        Theme theme = service.getThemeById(themeId);
        Matiere matiere = theme.getMatiere();
        
        HttpSession s = request.getSession();
        System.out.println("eleveid : " + s.getAttribute("eleveId"));
        Long idEleve = Long.parseLong(s.getAttribute("eleveId").toString());
        Eleve eleve = service.getEleveById(idEleve);
        
        String lien = "lien visio";
        
        boolean demandeCree = service.creationDemande(description, theme, eleve, lien);
        
        List<Demande> demandes = service.historiqueDemandeEleve(eleve);
        System.out.println("demandes : " + demandes);
        String idDemande = "null";
        String lienDemande = "null";
        if (demandeCree) {
            for (Demande d : demandes) {
            //for (Demande d : eleve.getHistoDemande()) {
                //System.out.println("ièmé demande : " + d);
                if (!(d.getTermine())) {
                    if (d.getDescription().equals(description) && d.getTheme().getId().equals(theme.getId()) && d.getLien().equals(lien)) {
                        idDemande = d.getId().toString();
                        lienDemande = d.getLien();
                    }
                }
            }
        }
        
        System.out.println("Demande crée : " + demandeCree + " id : " + idDemande);
        request.setAttribute("reussit", demandeCree);
        request.setAttribute("idDemande", idDemande);
        request.setAttribute("lienDemande", lienDemande);
    }
    
}
