/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metier.modele;

import javax.persistence.Entity;

@Entity
public class Autre extends Intervenant {

    private String activite;

    public Autre() {
    }

    public Autre(String login, String mail, String mdp, String tel, Integer niveau_min, Integer niveau_max, String activite) {
        super(login, mail, mdp, tel, niveau_min, niveau_max);
        this.activite = activite;
    }

    @Override
    public String toString() {
        return "Autre{" + "activite=" + activite + '}';
    }

    public String getActivite() {
        return activite;
    }

    public void setActivite(String activite) {
        this.activite = activite;
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
