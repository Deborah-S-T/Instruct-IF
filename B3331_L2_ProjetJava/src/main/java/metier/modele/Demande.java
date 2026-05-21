/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metier.modele;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Demande {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    private String lien;
    private LocalDate date;
    private Integer duree;
    private String bilan;
    private Boolean termine;
    @ManyToOne
    private Theme theme;

    @ManyToOne
    private Eleve eleve;

    @ManyToOne
    private Intervenant intervenant;

    public Demande(String description, String lien, LocalDate date, Integer duree, String bilan, Boolean termine, Theme theme, Eleve eleve, Intervenant intervenant) {
        this.description = description;
        this.lien = lien;
        this.date = date;
        this.duree = duree;
        this.bilan = bilan;
        this.termine = termine;
        this.theme = theme;
        this.eleve = eleve;
        this.intervenant = intervenant;

    }

    @Override
    public String toString() {
        return "Demande{" + "id=" + id + ", description=" + description + ", lien=" + lien + ", date=" + date + ", dur\u00e9e=" + duree + ", bilan=" + bilan + ", termine=" + termine + ", theme=" + theme + '}';
    }

    public Demande() {
    }

    public Long getId() {
        return id;
    }

    public Integer getDuree() {
        return duree;
    }

    public Boolean getTermine() {
        return termine;
    }

    public Theme getTheme() {
        return theme;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public Intervenant getIntervenant() {
        return intervenant;
    }

    public String getDescription() {
        return description;
    }

    public String getLien() {
        return lien;
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getduree() {
        return duree;
    }

    public String getBilan() {
        return bilan;
    }

    public Boolean gettermine() {
        return termine;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setduree(Integer duree) {
        this.duree = duree;
    }

    public void setBilan(String bilan) {
        this.bilan = bilan;
    }

    public void setTermine(Boolean termine) {
        this.termine = termine;
    }

}
