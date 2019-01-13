package pl.kielce.tu.przedszkole.przedszkole.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kielce.tu.przedszkole.przedszkole.model.Child;
import pl.kielce.tu.przedszkole.przedszkole.model.New;
import pl.kielce.tu.przedszkole.przedszkole.model.Person;
import pl.kielce.tu.przedszkole.przedszkole.repository.PersonRepository;
import pl.kielce.tu.przedszkole.przedszkole.service.NewsService.NewsService;
import pl.kielce.tu.przedszkole.przedszkole.service.NewsService.NewsServiceImpl;
import pl.kielce.tu.przedszkole.przedszkole.service.NewsService.NewsServiceParentProxy;
import pl.kielce.tu.przedszkole.przedszkole.service.NewsService.NewsServiceTeacherProxy;
import pl.kielce.tu.przedszkole.przedszkole.service.PersonService.PersonService;
import pl.kielce.tu.przedszkole.przedszkole.service.PersonService.PersonServiceImpl;
import pl.kielce.tu.przedszkole.przedszkole.service.PersonService.PersonServiceParentProxy;
import pl.kielce.tu.przedszkole.przedszkole.service.PersonService.PersonServiceTeacherProxy;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MainAppService {

    private final PersonRepository personRepository;
    private final PersonServiceImpl personServiceImpl;
    private final PersonServiceTeacherProxy personServiceTeacherProxy;
    private final PersonServiceParentProxy personServiceParentProxy;
    private final NewsServiceImpl newsServiceImpl;
    private final NewsServiceTeacherProxy newsServiceTeacherProxy;
    private final NewsServiceParentProxy newsServiceParentProxy;

    @Autowired
    public MainAppService(PersonRepository personRepository,
                          PersonServiceImpl personServiceImpl,
                          PersonServiceTeacherProxy personServiceTeacherProxy,
                          PersonServiceParentProxy personServiceParentProxy,
                          NewsServiceImpl newsServiceImpl,
                          NewsServiceTeacherProxy newsServiceTeacherProxy,
                          NewsServiceParentProxy newsServiceParentProxy) {

        this.personRepository = personRepository;
        this.personServiceImpl = personServiceImpl;
        this.personServiceTeacherProxy = personServiceTeacherProxy;
        this.personServiceParentProxy = personServiceParentProxy;
        this.newsServiceImpl = newsServiceImpl;
        this.newsServiceTeacherProxy = newsServiceTeacherProxy;
        this.newsServiceParentProxy = newsServiceParentProxy;
    }

    private Person resolvePersonByLogin(String username) {
        Optional<Person> person = personRepository.findByLogin(username);
        return person.orElse(null);
    }

    private PersonService resolvePersonInterface(Person person) {
        if (person.getRole().equalsIgnoreCase("ADMIN")) return personServiceImpl;
        else if(person.getRole().equalsIgnoreCase("TEACHER")) return personServiceTeacherProxy;
        else return personServiceParentProxy;
    }

    private NewsService resolveNewsInterface(Person person) {
        if(person.getRole().equalsIgnoreCase("ADMIN")) return newsServiceImpl;
        else if(person.getRole().equalsIgnoreCase("TEACHER")) return newsServiceTeacherProxy;
        else if(person.getRole().equalsIgnoreCase("PARENT")) return newsServiceParentProxy;
        else return null;
    }

    @Transactional
    public void addPerson(String issuingPersonUsername, Person personToAdd) throws Exception {
        Person issuingPerson = resolvePersonByLogin(issuingPersonUsername);
        if (issuingPerson == null) {
            throw new Exception("Issuing user not found");
        }
        resolvePersonInterface(issuingPerson).addPerson(issuingPersonUsername, personToAdd);
    }

    @Transactional
    public void editPerson(String issuingPersonUsername, Person personToEdit) throws Exception {
        Person issuingPerson = resolvePersonByLogin(issuingPersonUsername);
        if (issuingPerson == null) {
            throw new Exception("Issuing user not found");
        }
        resolvePersonInterface(issuingPerson).editPerson(issuingPersonUsername, personToEdit);
    }

    @Transactional
    public void deletePerson(String issuingPersonUsername, Long personId) throws Exception {
        Person issuingPerson = resolvePersonByLogin(issuingPersonUsername);
        if (issuingPerson == null) {
            throw new Exception("Issuing user not found");
        }
        resolvePersonInterface(issuingPerson).deletePerson(issuingPersonUsername, personId);
    }

    @Transactional
    public void addChild(String issuingPersonUsername, Child child) throws Exception {
        Person issuingPerson = resolvePersonByLogin(issuingPersonUsername);

        resolvePersonInterface(issuingPerson).addChild(issuingPersonUsername, child);
    }

    @Transactional
    public void editChild(String issuingPersonUsername, Child child) throws Exception {
        Person issuingPerson = resolvePersonByLogin(issuingPersonUsername);
        resolvePersonInterface(issuingPerson).editChild(issuingPersonUsername, child);
    }

    @Transactional
    public void deleteChild(String issuingPersonUsername, Long childId) throws Exception {
        Person issuingPerson = resolvePersonByLogin(issuingPersonUsername);
        resolvePersonInterface(issuingPerson).deleteChild(issuingPersonUsername, childId);
    }

    public List<Person> getAdmins(String issuingPersonUsername) throws Exception {
        Person issuingPerson = resolvePersonByLogin(issuingPersonUsername);
        if (issuingPerson == null) {
            throw new Exception("Issuing user not found");
        }
        return resolvePersonInterface(issuingPerson).getAdmins();
    }

    public List<Person> getTeachers(String issuingPersonUsername) throws Exception {
        Person issuingPerson = resolvePersonByLogin(issuingPersonUsername);
        if (issuingPerson == null) {
            throw new Exception("Issuing user not found");
        }
        return resolvePersonInterface(issuingPerson).getTeachers();
    }

    public List<Person> getParents(String issuingPersonUsername) throws Exception {
        Person issuingPerson = resolvePersonByLogin(issuingPersonUsername);
        if (issuingPerson == null) {
            throw new Exception("Issuing user not found");
        }
        return resolvePersonInterface(issuingPerson).getParents();
    }

    public List<Child> getChildren(String issuingPersonUsername) throws Exception {
        Person issuingPerson = resolvePersonByLogin(issuingPersonUsername);
        return resolvePersonInterface(issuingPerson).getChildren();
    }

    public Person getPersonById(String issuingPersonUsername, Long personId) {
        return resolvePersonInterface(resolvePersonByLogin(issuingPersonUsername)).getPersonById(personId);
    }

    public Child getChildById(String issuingPersonUsername, Long childId) {
        return resolvePersonInterface(resolvePersonByLogin(issuingPersonUsername)).getChildById(childId);
    }

    @Transactional
    public void addNews(String issuingPersonUsername, New news) throws Exception {
        Objects.requireNonNull(resolveNewsInterface(resolvePersonByLogin(issuingPersonUsername))).addNews(issuingPersonUsername, news);
    }

    @Transactional
    public void editNews(String issuingPersonUsername, New news) throws Exception {
        Objects.requireNonNull(resolveNewsInterface(resolvePersonByLogin(issuingPersonUsername))).editNews(issuingPersonUsername, news);
    }

    @Transactional
    public void deleteNews(String issuingPersonUsername, Long newsId) throws Exception {
        Objects.requireNonNull(resolveNewsInterface(resolvePersonByLogin(issuingPersonUsername))).deleteNews(issuingPersonUsername, newsId);
    }

    public List<New> getNewsList(String issuingPersonUsername) {
        return Objects.requireNonNull(resolveNewsInterface(resolvePersonByLogin(issuingPersonUsername))).getNewsList();
    }

    public New getNewsById(String issuingPersonUsername, Long newsId) {
        return Objects.requireNonNull(resolveNewsInterface(resolvePersonByLogin(issuingPersonUsername))).getNewsById(newsId);
    }
}
