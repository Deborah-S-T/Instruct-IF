/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import metier.modele.Matiere;


public class MatiereDao {
    
    public void create(Matiere mat1){
        JpaUtil.obtenirContextePersistance().persist(mat1);
    } 
    
    public List<Matiere> getListeMatieres(){
          String s = "select m from Matiere m order by m.nom";
          TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s,Matiere.class);
          return query.getResultList();
      }
    
    public Matiere findById(long id){
          String s = "select m from Matiere m where m.id = :unId";
        TypedQuery<Matiere> query = JpaUtil.obtenirContextePersistance().createQuery(s, Matiere.class);
        query.setParameter("unid", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
      }
}
