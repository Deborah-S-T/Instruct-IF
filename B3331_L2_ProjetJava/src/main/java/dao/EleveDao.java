package dao;

import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import metier.modele.Demande;
import metier.modele.Eleve;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class EleveDao {
    public void create(Eleve e1){
         JpaUtil.obtenirContextePersistance().persist(e1);
    }  
      
    public Eleve findByMail(String mail){
        String s = "select e from Eleve e where e.mail = :mail";
        TypedQuery<Eleve> query = JpaUtil.obtenirContextePersistance().createQuery(s, Eleve.class);
        query.setParameter("mail", mail);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
     
    public void update(Eleve e1){
          JpaUtil.obtenirContextePersistance().merge(e1);
      }
    
    public List<Demande> findHistoDemandeByID(Eleve e1){ //Méthode complémentaire pour récupérer la liste des demandes d'un élève
        Long eleveID = e1.getId();
        String s = "SELECT distinct d FROM Eleve e JOIN e.histoDemandes d WHERE e.id = :id";
        TypedQuery<Demande> query = JpaUtil.obtenirContextePersistance().createQuery(s, Demande.class);
        query.setParameter("id", eleveID);
        return query.getResultList();
    }
    
    public Eleve findById(long id){
        String s = "select e from Eleve e where e.id = :unId";
        TypedQuery<Eleve> query = JpaUtil.obtenirContextePersistance().createQuery(s, Eleve.class);
        query.setParameter("unId", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    

    
      /*
      public void delete(Eleve e1){
          JpaUtil.obtenirContextePersistance().remove(e1);
      }
      
      
      
      public void findByID(Long id){
          JpaUtil.obtenirContextePersistance().find(Eleve.class,id);
      }
      
      public List<Eleve> findAll(){
          String s = "select c from Eleve c order by c.nom";
          TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s,Eleve.class);
          return query.getResultList();
      }
     */
      
}
