package pl.kielce.tu.przedszkole.przedszkole.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kielce.tu.przedszkole.przedszkole.model.Child;
import pl.kielce.tu.przedszkole.przedszkole.model.Person;
import pl.kielce.tu.przedszkole.przedszkole.repository.PersonRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class MainAppService {

    private final PersonRepository personRepository;
    private final PersonServiceImpl personServiceImpl;
    private final PersonServiceTeacherProxy personServiceTeacherProxy;
    private final PersonServiceParentProxy personServiceParentProxy;
    private Predicate<Person> isAdmin = (person -> person.getRole().equals("ADMIN"));

    @Autowired
    public MainAppService(PersonRepository personRepository,
                          PersonServiceImpl personServiceImpl,
                          PersonServiceTeacherProxy personServiceTeacherProxy,
                          PersonServiceParentProxy personServiceParentProxy) {

        this.personRepository = personRepository;
        this.personServiceImpl = personServiceImpl;
        this.personServiceTeacherProxy = personServiceTeacherProxy;
        this.personServiceParentProxy = personServiceParentProxy;
    }

    private Person resolvePersonByLogin(String username) {
        Optional<Person> person = personRepository.findByLogin(username);
        return person.orElse(null);
    }

    private PersonService resolveInterface(Person person) {
        if (person.getRole().equals("ADMIN")) return personServiceImpl;
        else if(person.getRole().equals("TEACHER")) return personServiceTeacherProxy;
        else return personServiceParentProxy;
    }

    @Transactional
    public void addPerson(String issuingPersonUsername, Person personToAdd) throws Exception {
        Person issuingPerson = resolvePersonByLogin(issuingPersonUsername);
        if (issuingPerson == null) {
            throw new Exception("Issuing user not found");
        }
        resolveInterface(issuingPerson).addPerson(issuingPersonUsername, personToAdd);
    }

    @Transactional
    public void editPerson(String issuingPersonUsername, Person personToEdit) throws Exception {
        Person issuingPerson = resolvePersonByLogin(issuingPersonUsername);
        if (issuingPerson == null) {
            throw new Exception("Issuing user not found");
        }
        resolveInterface(issuingPerson).editPerson(issuingPersonUsername, personToEdit);
    }

    @Transactional
    public void deletePerson(String issuingPersonUsername, Long personId) throws Exception {
        Person issuingPerson = resolvePersonByLogin(issuingPersonUsername);
        if (issuingPerson == null) {
            throw new Exception("Issuing user not found");
        }
        resolveInterface(issuingPerson).deletePerson(issuingPersonUsername, personId);
    }

    @Transactional
    public void addChild(String issuingPersonUsername, Child child) throws Exception {
        Person issuingPerson = resolvePersonByLogin(issuingPersonUsername);

        resolveInterface(issuingPerson).addChild(issuingPersonUsername, child);
    }

    @Transactional
    public void editChild(String issuingPersonUsername, Child child) throws Exception {
        Person issuingPerson = resolvePersonByLogin(issuingPersonUsername);
        resolveInterface(issuingPerson).editChild(issuingPersonUsername, child);
    }

    @Transactional
    public void deleteChild(String issuingPersonUsername, Long childId) throws Exception {
        Person issuingPerson = resolvePersonByLogin(issuingPersonUsername);
        resolveInterface(issuingPerson).deleteChild(issuingPersonUsername, childId);
    }

    public List<Person> getAdmins(String issuingPersonUsername) throws Exception {
        Person issuingPerson = resolvePersonByLogin(issuingPersonUsername);
        if (issuingPerson == null) {
            throw new Exception("Issuing user not found");
        }
        return resolveInterface(issuingPerson).getAdmins();
    }

    public List<Person> getTeachers(String issuingPersonUsername) throws Exception {
        Person issuingPerson = resolvePersonByLogin(issuingPersonUsername);
        if (issuingPerson == null) {
            throw new Exception("Issuing user not found");
        }
        return resolveInterface(issuingPerson).getTeachers();
    }

    public List<Person> getParents(String issuingPersonUsername) throws Exception {
        Person issuingPerson = resolvePersonByLogin(issuingPersonUsername);
        if (issuingPerson == null) {
            throw new Exception("Issuing user not found");
        }
        return resolveInterface(issuingPerson).getParents();
    }

    public List<Child> getChildren(String issuingPersonUsername) throws Exception {
        Person issuingPerson = resolvePersonByLogin(issuingPersonUsername);
        return resolveInterface(issuingPerson).getChildren();
    }

    public Person getPersonById(String issuingPersonUsername, Long personId) {
        return resolveInterface(resolvePersonByLogin(issuingPersonUsername)).getPersonById(personId);
    }

    public Child getChildById(String issuingPersonUsername, Long childId) {
        return resolveInterface(resolvePersonByLogin(issuingPersonUsername)).getChildById(childId);
    }
}
