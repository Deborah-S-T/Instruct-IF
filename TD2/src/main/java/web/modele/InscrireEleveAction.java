/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.modele;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import metier.modele.Eleve;
import metier.service.Service;

/**
 *
 * @author dsteferra
 */
public class InscrireEleveAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        
        Service service = new Service();
        
        var nom = request.getParameter("nom");
        var prenom = request.getParameter("prenom");
        var mail = request.getParameter("mail");
        var motDePasse = request.getParameter("password");
        LocalDate dateNaissance = LocalDate.parse(request.getParameter("dateNaissance"));
        var classe = request.getParameter("classe");
        var codeEtablissement = request.getParameter("codeEtablissement"); // 0691664J
        
        Eleve eleve = new Eleve(nom, prenom, mail, motDePasse, dateNaissance, Integer.valueOf(classe));
        
        Boolean eleveInscrit = service.inscrireEleve(eleve, codeEtablissement);
        request.setAttribute("eleveInscrit", eleveInscrit);
        
    }
}