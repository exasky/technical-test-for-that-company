package fr.technical.test.person;

import fr.technical.test.person.model.Personne;
import fr.technical.test.person.service.PersonneService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@SpringBootTest
@ActiveProfiles("test")
public class PersonneServiceTest {
    @Autowired
    PersonneService personneService;

    @Test
    public void testCreatePerson() {
        Date birthDate = new Date();
        Personne personne = personneService.create("prenom", "nom", birthDate);

        assertThat(personne).isNotNull();
        assertThat(personne.getNom()).isEqualTo("nom");
        assertThat(personne.getPrenom()).isEqualTo("prenom");
        assertThat(personne.getDateDeNaissance()).isEqualTo(birthDate);
    }

    @Test()
    public void testCreatePersonMoreThan150Years() {
        var of = LocalDateTime.of(1800, 1, 1, 0, 0, 0);
        Date birthDate = Date.from(of.toInstant(ZoneOffset.UTC));

        assertThrows(RuntimeException.class, () -> personneService.create("prenom", "nom", birthDate));
    }
}
