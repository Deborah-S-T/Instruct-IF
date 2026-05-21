/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metier.modele;

import javax.persistence.Entity;

@Entity
public class Etudiant extends Intervenant {

    private String universite;

    private String specialite;

    public Etudiant() {
    }

    public Etudiant(String login, String mail, String mdp, String tel, Integer niveau_min, Integer niveau_max, String universite, String specialite) {
        super(login, mail, mdp, tel, niveau_min, niveau_max);
        this.universite = universite;
        this.specialite = specialite;
    }

    public String getUniversite() {
        return universite;
    }

}
