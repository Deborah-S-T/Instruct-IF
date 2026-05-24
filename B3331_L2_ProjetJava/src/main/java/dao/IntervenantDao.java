/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import metier.modele.Demande;
import metier.modele.Intervenant;

public class IntervenantDao {
    
    public void create(Intervenant et1){
        JpaUtil.obtenirContextePersistance().persist(et1);
    } 
    
    public Intervenant findIntervenant(int niveauEleve){
        String s = "SELECT i FROM Intervenant i WHERE i.niveau_min >= :niveau AND i.niveau_max <= :niveau AND i.disponible = true GROUP BY i ORDER BY i.nb_intervention DESC";
        TypedQuery<Intervenant> query = JpaUtil.obtenirContextePersistance().createQuery(s, Intervenant.class);
        query.setParameter("niveau", niveauEleve);
        query.setMaxResults(1);
        //return query.getSingleResult();
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public Intervenant findByLogin(String login){
        String s = "select i from Intervenant i where i.login = :login";
        TypedQuery<Intervenant> query = JpaUtil.obtenirContextePersistance().createQuery(s, Intervenant.class);
        query.setParameter("login", login);
        
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public void update(Intervenant it){
        JpaUtil.obtenirContextePersistance().merge(it);
    }
    
    public Demande recupererDemandeEnCours(Intervenant it)
    {
        String s = "select d from Intervenant i JOIN i.listeDemande d where d.termine = false AND i.id = :id";
        TypedQuery<Demande> query = JpaUtil.obtenirContextePersistance().createQuery(s, Demande.class);
        query.setParameter("id", it.getId());
        query.setMaxResults(1);
        return query.getSingleResult();
    }
    
    public List<Demande> findHistoDemandeByID(Intervenant it){ //Méthode complémentaire pour récupérer la liste des demandes d'un intervenant
        Long intId = it.getId();
        String s = "SELECT d FROM Intervenant it JOIN it.listeDemande d WHERE it.id = :id AND d.termine = true";
        TypedQuery<Demande> query = JpaUtil.obtenirContextePersistance().createQuery(s, Demande.class);
        query.setParameter("id", intId);
        return query.getResultList();
    }
    
    public List<Demande> findNbEleveByInt(Intervenant it){
        Long intId = it.getId();
        String s = "SELECT DISTINCT d.eleve FROM Intervenant it JOIN it.listeDemande d WHERE it.id = :id";
        TypedQuery<Demande> query = JpaUtil.obtenirContextePersistance().createQuery(s, Demande.class);
        query.setParameter("id", intId);
        return query.getResultList();
    }
    
    public Intervenant findById(long id){
        String s = "select i from Intervenant i where i.id = :unId";
        TypedQuery<Intervenant> query = JpaUtil.obtenirContextePersistance().createQuery(s, Intervenant.class);
        query.setParameter("unId", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
