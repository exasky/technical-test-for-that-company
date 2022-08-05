package fr.technical.test.person.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "Personne")
public class Personne {
    @Id
    @GeneratedValue
    private Long id;
    private String nom;
    private String prenom;
    private Date dateDeNaissance;

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Personne setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public String getPrenom() {
        return prenom;
    }

    public Personne setPrenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    public Personne setDateDeNaissance(Date dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
        return this;
    }
}
