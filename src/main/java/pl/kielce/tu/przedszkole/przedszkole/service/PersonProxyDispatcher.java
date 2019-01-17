package pl.kielce.tu.przedszkole.przedszkole.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kielce.tu.przedszkole.przedszkole.model.Child;
import pl.kielce.tu.przedszkole.przedszkole.model.Person;
import pl.kielce.tu.przedszkole.przedszkole.repository.PersonRepository;
import pl.kielce.tu.przedszkole.przedszkole.service.PersonService.PersonService;
import pl.kielce.tu.przedszkole.przedszkole.service.PersonService.PersonServiceImpl;
import pl.kielce.tu.przedszkole.przedszkole.service.PersonService.PersonServiceParentProxy;
import pl.kielce.tu.przedszkole.przedszkole.service.PersonService.PersonServiceTeacherProxy;
import pl.kielce.tu.przedszkole.przedszkole.utils.PersonResolverUtils;

import java.util.List;

@Service
public class PersonProxyDispatcher {
    private final PersonServiceImpl personServiceImpl;
    private final PersonServiceTeacherProxy personServiceTeacherProxy;
    private final PersonServiceParentProxy personServiceParentProxy;
    private final PersonResolverUtils personResolverUtils;

    @Autowired
    public PersonProxyDispatcher(PersonServiceImpl personServiceImpl,
                                 PersonServiceTeacherProxy personServiceTeacherProxy,
                                 PersonServiceParentProxy personServiceParentProxy,
                                 PersonResolverUtils personResolverUtils) {
        this.personServiceImpl = personServiceImpl;
        this.personServiceTeacherProxy = personServiceTeacherProxy;
        this.personServiceParentProxy = personServiceParentProxy;
        this.personResolverUtils = personResolverUtils;
    }

    private PersonService resolvePersonInterface(Person person) {
        if (person.getRole().equalsIgnoreCase("ADMIN")) return personServiceImpl;
        else if(person.getRole().equalsIgnoreCase("TEACHER")) return personServiceTeacherProxy;
        else return personServiceParentProxy;
    }

    @Transactional
    public void addPerson(String issuingPersonUsername, Person personToAdd) throws Exception {
        Person issuingPerson = personResolverUtils.resolvePersonByLogin(issuingPersonUsername);
        if (issuingPerson == null) {
            throw new Exception("Issuing user not found");
        }
        resolvePersonInterface(issuingPerson).addPerson(issuingPersonUsername, personToAdd);
    }

    @Transactional
    public void editPerson(String issuingPersonUsername, Person personToEdit) throws Exception {
        Person issuingPerson = personResolverUtils.resolvePersonByLogin(issuingPersonUsername);
        if (issuingPerson == null) {
            throw new Exception("Issuing user not found");
        }
        resolvePersonInterface(issuingPerson).editPerson(issuingPersonUsername, personToEdit);
    }

    @Transactional
    public void deletePerson(String issuingPersonUsername, Long personId) throws Exception {
        Person issuingPerson = personResolverUtils.resolvePersonByLogin(issuingPersonUsername);
        if (issuingPerson == null) {
            throw new Exception("Issuing user not found");
        }
        resolvePersonInterface(issuingPerson).deletePerson(issuingPersonUsername, personId);
    }

    public List<Person> getAdmins(String issuingPersonUsername) throws Exception {
        Person issuingPerson = personResolverUtils.resolvePersonByLogin(issuingPersonUsername);
        if (issuingPerson == null) {
            throw new Exception("Issuing user not found");
        }
        return resolvePersonInterface(issuingPerson).getAdmins();
    }

    public List<Person> getTeachers(String issuingPersonUsername) throws Exception {
        Person issuingPerson = personResolverUtils.resolvePersonByLogin(issuingPersonUsername);
        if (issuingPerson == null) {
            throw new Exception("Issuing user not found");
        }
        return resolvePersonInterface(issuingPerson).getTeachers();
    }

    public List<Person> getParents(String issuingPersonUsername) throws Exception {
        Person issuingPerson = personResolverUtils.resolvePersonByLogin(issuingPersonUsername);
        if (issuingPerson == null) {
            throw new Exception("Issuing user not found");
        }
        return resolvePersonInterface(issuingPerson).getParents();
    }

    public Person getPersonById(String issuingPersonUsername, Long personId) {
        return resolvePersonInterface(personResolverUtils.resolvePersonByLogin(issuingPersonUsername)).getPersonById(personId);
    }
}
