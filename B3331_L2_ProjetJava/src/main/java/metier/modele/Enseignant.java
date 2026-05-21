/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metier.modele;

import javax.persistence.Entity;

@Entity
public class Enseignant extends Intervenant {

    private Etablissement etablissement;

    public Enseignant() {
    }

    public Enseignant(String login, String mail, String mdp, String tel, Integer niveau_min, Integer niveau_max, Etablissement etablissement) {
        super(login, mail, mdp, tel, niveau_min, niveau_max);
        this.etablissement = etablissement;
    }

    public Etablissement getEtablissement() {
        return etablissement;
    }

}
