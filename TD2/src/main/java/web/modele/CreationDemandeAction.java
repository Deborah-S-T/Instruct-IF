/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.modele;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
        
        System.out.println("Demande crée : " + demandeCree);
        request.setAttribute("reussit", demandeCree);
    }
    
}
