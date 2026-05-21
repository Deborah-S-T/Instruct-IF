/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metier.modele;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Intervenant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    @Column(unique = true, nullable = false)
    protected String login;
    @Column(unique = true, nullable = false)
    protected String mail;
    @Column(nullable = false)
    protected String mdp;
    @Column(unique = true, nullable = false)
    protected String tel;
    @Column(nullable = false)
    protected Integer niveau_max;
    @Column(nullable = false)
    protected Integer niveau_min;

    protected Integer nb_intervention;

    protected Boolean disponible;

    @OneToMany(mappedBy = "intervenant")
    protected List<Demande> listeDemande;

    public Boolean getdisponible() {
        return disponible;
    }

    public boolean addDemande(Demande e) {
        return listeDemande.add(e);
    }

    public void setdisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public List<Demande> getHistoDemande() {
        return listeDemande;
    }

    public void setListeDemande(List<Demande> listeDemande) {
        this.listeDemande = listeDemande;
    }

    public Intervenant(String login, String mail, String mdp, String tel, Integer niveau_max, Integer niveau_min) {
        this.login = login;
        this.mail = mail;
        this.mdp = mdp;
        this.tel = tel;
        this.niveau_max = niveau_max;
        this.niveau_min = niveau_min;
        this.disponible = true;
        this.nb_intervention = 0;
    }

    public Long getId() {
        return id;
    }

    public Integer getNb_intervention() {
        return nb_intervention;
    }

    public void setNb_intervention(Integer nb_intervention) {
        this.nb_intervention = nb_intervention;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public Intervenant() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getNiveau_max() {
        return niveau_max;
    }

    @Override
    public String toString() {
        return "Intervenant{" + ", login=" + login + ", mail=" + mail + ", mdp=" + mdp + ", tel=" + tel + ", niveau_max=" + niveau_max + ", niveau_min=" + niveau_min + '}';
    }

    public void setNiveau_max(Integer niveau_max) {
        this.niveau_max = niveau_max;
    }

    public Integer getNiveau_min() {
        return niveau_min;
    }

    public void setNiveau_min(Integer niveau_min) {
        this.niveau_min = niveau_min;
    }

}
