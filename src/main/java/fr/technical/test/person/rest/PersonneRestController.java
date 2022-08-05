package fr.technical.test.person.rest;

import fr.technical.test.configuration.TechnicalTestConfiguration;
import fr.technical.test.person.model.Personne;
import fr.technical.test.person.rest.dto.PersonneCreateDto;
import fr.technical.test.person.rest.dto.PersonneWithAgeDto;
import fr.technical.test.person.service.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(TechnicalTestConfiguration.REST_URL + "/personne")
public class PersonneRestController {

    private final PersonneService personneService;

    @Autowired
    public PersonneRestController(PersonneService personneService) {
        this.personneService = personneService;
    }

    @GetMapping
    public List<PersonneWithAgeDto> getAllSorted() {
        return this.personneService.getAllSorted().stream().map(PersonneWithAgeDto::toDto).collect(Collectors.toList());
    }

    @PostMapping
    public Personne create(@RequestBody PersonneCreateDto dto) {
        return this.personneService.create(dto.getPrenom(), dto.getNom(), dto.getDateDeNaissance());
    }
}
