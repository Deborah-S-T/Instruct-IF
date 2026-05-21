/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.modele;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import metier.modele.Matiere;
import metier.service.Service;

/**
 *
 * @author dsteferra
 */
public class ListerMatieresAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        Service service = new Service();
        List<Matiere> listeMatieres = service.listerMatieres();
        
        System.out.println("la liste des matieres récupérée : " + listeMatieres);
        request.setAttribute("listeMatieres", listeMatieres);
    }
    
}

