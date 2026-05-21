/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metier.modele;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Eleve {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private String prenom;
    @Column(unique = true, nullable = false)
    private String mail;
    @Column(nullable = false)
    private String motDePasse;

    @Column(nullable = false)
    private LocalDate dateNaissance;
    @Column(nullable = false)
    private Integer classe;

    @ManyToOne
    private Etablissement etablissement;

    @OneToMany(mappedBy = "eleve")
    private List<Demande> histoDemandes;

    public Eleve(String nom, String prenom, String mail, String motDePasse, LocalDate dateNaissance, Integer classe) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.motDePasse = motDePasse;
        this.dateNaissance = dateNaissance;
        this.classe = classe;
        this.etablissement = null;
    }

    public Eleve() {
    }

    public boolean addDemande(Demande e) {
        return histoDemandes.add(e);
    }

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public List<Demande> getHistoDemande() {
        return histoDemandes;
    }

    public void setHistoDemandes(List<Demande> histoDemandes) {
        this.histoDemandes = histoDemandes;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMail() {
        return mail;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public Integer getClasse() {
        return classe;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setClasse(Integer classe) {
        this.classe = classe;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }

    @Override
    public String toString() {
        return "Eleve{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", motDePasse=" + motDePasse + ", dateNaissance=" + dateNaissance + ", classe=" + classe + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Eleve other = (Eleve) obj;
        return Objects.equals(this.id, other.id);
    }

}
