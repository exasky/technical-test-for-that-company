package fr.technical.test.person.service;

import fr.technical.test.person.model.Personne;
import fr.technical.test.person.repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class PersonneService {
    private final PersonneRepository personneRepository;

    @Autowired
    public PersonneService(PersonneRepository personneRepository) {
        this.personneRepository = personneRepository;
    }

    public List<Personne> getAllSorted() {
        List<Personne> all = this.personneRepository.findAll();
        all.sort(Comparator.comparing(Personne::getNom).thenComparing(Personne::getPrenom));
        return all;
    }

    public Personne create(String firstName, String lastName, Date birthDate) {
        var nowTime = LocalDate.now();
        var birthDateTime = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        long yearsBetween = ChronoUnit.YEARS.between(birthDateTime, nowTime);
        boolean isOlderThan150Years = yearsBetween > 150;
        if (isOlderThan150Years) {
            throw new RuntimeException("The person you're trying to create is older than 150 years");
        }

        return this.personneRepository.save(new Personne().setNom(lastName).setPrenom(firstName).setDateDeNaissance(birthDate));
    }
}
