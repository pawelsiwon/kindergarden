package pl.kielce.tu.przedszkole.przedszkole.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kielce.tu.przedszkole.przedszkole.model.Person;
import pl.kielce.tu.przedszkole.przedszkole.repository.PersonRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private final PersonRepository personRepository;
    private Predicate<Person> isAdmin = (person -> person.getRole().equals("ADMIN"));
    private Predicate<Person> isTeacher = (person -> person.getRole().equals("TEACHER"));
    private Predicate<Person> isChild = (person -> person.getRole().equals("CHILD"));

    @Autowired
    public AdminService(PersonRepository personRepository) {

        this.personRepository = personRepository;
    }

    Person resolvePersonByLogin(String username) {
        Optional<Person> person = personRepository.findByLogin(username);
        return person.orElse(null);
    }

    @Transactional
    public void addPerson(String issuingPersonUsername, Person personToAdd) throws Exception {
        Optional<Person> personTmp = personRepository.findByLogin(personToAdd.getLogin());
        if(personTmp.isPresent()) {
            throw new Exception("Person with that login already exists!");
        }
        Person issuingPerson = resolvePersonByLogin(issuingPersonUsername);
        if(issuingPerson == null) {
            throw new Exception("Issuing user not found");
        }
        if(!isAdmin.test(issuingPerson)) {
            throw new Exception("Lack of privileges to add users");
        }

        personRepository.save(personToAdd);
    }

    private List<Person> getTeachersList() {
        return personRepository.findAll()
                .stream()
                .filter(isTeacher)
                .collect(Collectors.toList());
    }
}
