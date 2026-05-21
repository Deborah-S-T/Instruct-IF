/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import metier.modele.Demande;


public class DemandeDao {
    public void create(Demande dem){
        JpaUtil.obtenirContextePersistance().persist(dem);
      }  
    
    public void update(Demande dem){
        JpaUtil.obtenirContextePersistance().merge(dem);
    }
}
