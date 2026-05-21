/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.modele;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import metier.modele.Matiere;
import metier.modele.Theme;
import metier.service.Service;

/**
 *
 * @author dsteferra
 */
public class UpdateMatiereDemandeeAction extends Action {
    @Override
    public void execute(HttpServletRequest request) {
        Service service = new Service();
        String themeId = request.getParameter("theme");
        Long id = Long.valueOf(themeId);
        Theme theme = service.getThemeById(id);
        
        System.out.println("la matiere récupérée : " + theme.getMatiere());
        request.setAttribute("matiere", theme.getMatiere());
    }
    
}
