/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metier.modele;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Etablissement {

    // plusieurs @Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String code;
    private String nom;
    private Double ips;
    private Double coordonnee_x;
    private Double coordonnee_y;

    public Long getId() {
        return id;
    }

    public Double getCoordonnee_x() {
        return coordonnee_x;
    }

    public void setCoordonnee_x(Double coordonnee_x) {
        this.coordonnee_x = coordonnee_x;
    }

    public Double getCoordonnee_y() {
        return coordonnee_y;
    }

    public void setCoordonnee_y(Double coordonnee_y) {
        this.coordonnee_y = coordonnee_y;
    }

    public Etablissement() {
    }

    public Etablissement(String code) {
        this.code = code;
    }

    public Double getIps() {
        return ips;
    }

    public void setIps(Double ips) {
        this.ips = ips;
    }

    public String getCode() {
        return code;
    }

    public String getNom() {
        return nom;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}
