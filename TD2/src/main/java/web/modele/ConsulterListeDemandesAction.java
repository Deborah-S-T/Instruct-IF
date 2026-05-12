package web.modele;

import jakarta.servlet.http.HttpServletRequest;
import metier.service.Service;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author dsteferra
 */


public class ConsulterListeDemandesAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        Service service = new Service();
        //public List<Demande> historiqueDemandeEleve(Eleve e1) {
        //var listeDemandes = service.historiqueDemandeEleve();
        
        //request.setAttribute("histoDemandes", histoDemandes);
    }
    
    
    
}
