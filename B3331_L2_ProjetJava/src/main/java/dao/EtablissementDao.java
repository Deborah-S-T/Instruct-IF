package dao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import metier.modele.Etablissement;


public class EtablissementDao {

    public void create(Etablissement et1) {
        JpaUtil.obtenirContextePersistance().persist(et1);
    }

    public Etablissement findByCode(String code) {
        String s = "select et from Etablissement et where et.code = :code";
        TypedQuery<Etablissement> query = JpaUtil.obtenirContextePersistance().createQuery(s, Etablissement.class);
        query.setParameter("code", code);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Long nbEleveEtablissement(Etablissement et1) {
        String s = "SELECT count(e) FROM Eleve e WHERE e.etablissement = :et ";
        TypedQuery<Long> query = JpaUtil.obtenirContextePersistance().createQuery(s, Long.class);
        query.setParameter("et", et1);
        return query.getSingleResult();
    }
    
    public List<Etablissement> findAll(){
          String s = "select et from Etablissement et order by et.nom";
          TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s,Etablissement.class);
          return query.getResultList();
      }
}
