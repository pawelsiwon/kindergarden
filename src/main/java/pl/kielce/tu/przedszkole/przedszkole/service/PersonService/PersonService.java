package pl.kielce.tu.przedszkole.przedszkole.service.PersonService;

import pl.kielce.tu.przedszkole.przedszkole.model.Child;
import pl.kielce.tu.przedszkole.przedszkole.model.New;
import pl.kielce.tu.przedszkole.przedszkole.model.Person;

import java.util.List;

public interface PersonService {
    void addPerson(String issuerUsername, Person person) throws Exception;

    void editPerson(String issuerUsername, Person person) throws Exception;

    void deletePerson(String issuerUsername, Long personId) throws Exception;

    void addChild(String issuerUsername, Child child) throws Exception;

    void editChild(String issuerUsername, Child child) throws Exception;

    void deleteChild(String issuerUsername, Long childId) throws Exception;

    List<Person> getAdmins();

    List<Person> getTeachers();

    List<Person> getParents();

    List<Child> getChildren();

    Person getPersonById(Long personId);

    Child getChildById(Long childId);
}
