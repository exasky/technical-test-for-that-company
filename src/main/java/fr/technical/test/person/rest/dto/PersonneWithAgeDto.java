package fr.technical.test.person.rest.dto;

import fr.technical.test.person.model.Personne;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class PersonneWithAgeDto {
    @Id
    @GeneratedValue
    private Long id;
    private String nom;
    private String prenom;
    private Date dateDeNaissance;
    private Long age;

    public static PersonneWithAgeDto toDto(Personne personne) {
        var personneWithAgeDto = new PersonneWithAgeDto();

        personneWithAgeDto.id = personne.getId();
        personneWithAgeDto.nom = personne.getNom();
        personneWithAgeDto.prenom = personne.getPrenom();
        personneWithAgeDto.dateDeNaissance = personne.getDateDeNaissance();

        var nowTime = LocalDate.now();
        var birthDateTime = personne.getDateDeNaissance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        personneWithAgeDto.age = ChronoUnit.YEARS.between(birthDateTime, nowTime);

        return personneWithAgeDto;
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

    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    public Long getAge() {
        return age;
    }
}
