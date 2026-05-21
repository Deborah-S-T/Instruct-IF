/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.modele;

import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import metier.modele.Matiere;
import metier.modele.Theme;
import metier.service.Service;

/**
 *
 * @author dsteferra
 */
public class ListerThemesAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        Service service = new Service();
        List<Theme> listeThemesTempo = service.listerThemes();
        String matiere = request.getParameter("matiere");
        System.out.println("la matiere associée au thème est : " + matiere);
        
        List<Theme> listeThemes = new ArrayList<>();
        for (Theme t : listeThemesTempo) {
            if (matiere.equals("")) {
                listeThemes.add(t);
            }
            else if (t.getMatiere().getId().toString().equals(matiere))
            {
                listeThemes.add(t);
            }
        }
        
        System.out.println("la liste des thèmes récupérés : " + listeThemes);
        request.setAttribute("listeThemes", listeThemes);
    }
    
}

