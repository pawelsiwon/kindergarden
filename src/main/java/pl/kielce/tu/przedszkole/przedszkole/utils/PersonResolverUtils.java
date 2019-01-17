package pl.kielce.tu.przedszkole.przedszkole.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kielce.tu.przedszkole.przedszkole.model.Person;
import pl.kielce.tu.przedszkole.przedszkole.repository.PersonRepository;

import java.util.Optional;

@Service
public class PersonResolverUtils {
    private final PersonRepository personRepository;

    @Autowired
    public PersonResolverUtils(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person resolvePersonByLogin(String username) {
        Optional<Person> person = personRepository.findByLogin(username);
        return person.orElse(null);
    }
}
