package pl.kielce.tu.przedszkole.przedszkole.service;

import pl.kielce.tu.przedszkole.przedszkole.model.Person;

import java.util.List;

public interface PersonService {
    void addPerson(String issuerUsername, Person person) throws Exception;

    void editPerson(String issuerUsername, Person person) throws Exception;

    void deletePerson(String issuerUsername, Long personId) throws Exception;

    List<Person> getAdmins();

    List<Person> getTeachers();

    List<Person> getParents();
}
