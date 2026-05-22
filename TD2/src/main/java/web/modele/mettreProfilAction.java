/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.modele;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import metier.modele.Eleve;
import metier.service.Service;

/**
 *
 * @author dsteferra
 */
public class mettreProfilAction extends Action {
    @Override
    public void execute(HttpServletRequest request) {
        Service service = new Service();
        HttpSession s = request.getSession();
        System.out.println(s.getAttribute("eleveId"));
        Long idEleve = Long.parseLong(s.getAttribute("eleveId").toString());
        Eleve eleve = service.getEleveById(idEleve);
        
        System.out.println("Eleve récupéré de la session : " + eleve);
        request.setAttribute("eleve", eleve);
    }
}
