package pl.kielce.tu.przedszkole.przedszkole.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kielce.tu.przedszkole.przedszkole.model.Child;
import pl.kielce.tu.przedszkole.przedszkole.model.Person;

import javax.naming.OperationNotSupportedException;
import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServiceTeacherProxy implements PersonService {

    private final PersonServiceImpl personServiceImpl;
    private final Logger logger = Logger.getLogger(PersonServiceTeacherProxy.class.getName());

    @Autowired
    public PersonServiceTeacherProxy(PersonServiceImpl personServiceImpl) {
        this.personServiceImpl = personServiceImpl;
    }

    @Override
    public void addPerson(String issuerUsername, Person person) throws Exception {
        logger.warning("User " + issuerUsername + " attempted to add someone through proxy");
        throw new OperationNotSupportedException("Not possible through proxy");
    }

    @Override
    public void editPerson(String issuerUsername, Person person) throws Exception {
        logger.warning("User " + issuerUsername + " attempted to edit someone through proxy");
        throw new OperationNotSupportedException("Not possible through proxy");
    }

    @Override
    public void deletePerson(String issuerUsername, Long personId) throws Exception {
        logger.warning("User " + issuerUsername + " attempted to delete someone through proxy");
        throw new OperationNotSupportedException("Not possible through proxy");
    }

    @Override
    public void addChild(String issuerUsername, Child child) throws Exception {
        logger.warning("User " + issuerUsername + " attempted to add child through proxy");
        throw new OperationNotSupportedException("Not possible through proxy");
    }

    @Override
    public void editChild(String issuerUsername, Child child) throws Exception {
        logger.warning("User " + issuerUsername + " attempted to edit child through proxy");
        throw new OperationNotSupportedException("Not possible through proxy");
    }

    @Override
    public void deleteChild(String issuerUsername, Long childId) throws Exception {
        logger.warning("User " + issuerUsername + " attempted to delete child through proxy");
        throw new OperationNotSupportedException("Not possible through proxy");
    }

    @Override
    public List<Person> getAdmins() {
        return personServiceImpl.getAdmins();
    }

    @Override
    public List<Person> getTeachers() {
        return personServiceImpl.getTeachers();
    }

    @Override
    public List<Person> getParents() {
        return personServiceImpl.getParents();
    }

    @Override
    public List<Child> getChildren() {
        return personServiceImpl.getChildren();
    }

    @Override
    public Person getPersonById(Long personId) {
        return personServiceImpl.getPersonById(personId);
    }

    @Override
    public Child getChildById(Long childId) {
        return personServiceImpl.getChildById(childId);
    }
}
