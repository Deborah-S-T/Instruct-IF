package dao;

import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import metier.modele.Theme;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


public class ThemeDao {
    
    public void create(Theme te1){
        JpaUtil.obtenirContextePersistance().persist(te1);
    } 
    
    public List<Theme> getListeThemes(){
          String s = "select t from Theme t order by t.nom";
          TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s,Theme.class);
          return query.getResultList();
      }
    
    public Theme getThemeSelected(String nom){
    String s = "select t from Theme t where t.nom = :nom";
    TypedQuery<Theme> query = JpaUtil.obtenirContextePersistance().createQuery(s, Theme.class);
    query.setParameter("nom", nom);
    try {
        return query.getSingleResult();
    } catch (NoResultException e) {
        return null;
    }
    }
}
