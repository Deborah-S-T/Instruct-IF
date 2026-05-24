/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import metier.modele.Demande;


public class DemandeDao {
    public void create(Demande dem){
        JpaUtil.obtenirContextePersistance().persist(dem);
      }  
    
    public void update(Demande dem){
        JpaUtil.obtenirContextePersistance().merge(dem);
    }
    
    public Demande findById(long id){
        String s = "select d from Demande d where d.id = :unId";
        TypedQuery<Demande> query = JpaUtil.obtenirContextePersistance().createQuery(s, Demande.class);
        query.setParameter("unId", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
