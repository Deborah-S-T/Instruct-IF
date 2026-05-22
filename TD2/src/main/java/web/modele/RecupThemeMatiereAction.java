/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.modele;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import metier.modele.Matiere;
import metier.modele.Theme;
import metier.service.Service;

/**
 *
 * @author dsteferra
 */
public class RecupThemeMatiereAction extends Action {
    @Override
    public void execute(HttpServletRequest request) {
        Service service = new Service();
        
        Long themeId = Long.parseLong(request.getParameter("theme"));
        
        Theme theme = service.getThemeById(themeId);
        Matiere matiere = theme.getMatiere();
        
        System.out.println("Matiere et Theme récupérés : " + theme + matiere);
        request.setAttribute("theme", theme);
        request.setAttribute("matiere", matiere);
    }
}
