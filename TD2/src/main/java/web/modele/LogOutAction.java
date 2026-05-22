/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.modele;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import metier.service.Service;

/**
 *
 * @author dsteferra
 */
public class LogOutAction extends Action {
    @Override
    public void execute(HttpServletRequest request) {
        Service service = new Service();
        HttpSession s = request.getSession();
        
        System.out.println("avant eleveId = " + s.getAttribute("eleveId"));
        System.out.println("avant intervenantId = " + s.getAttribute("intervenantId"));
        
        s.invalidate();
        
        System.out.println("apres s = " + s);
        request.setAttribute("reussit", true);
    }
}
