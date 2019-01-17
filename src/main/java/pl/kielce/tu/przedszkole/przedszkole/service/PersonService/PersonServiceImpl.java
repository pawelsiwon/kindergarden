package pl.kielce.tu.przedszkole.przedszkole.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kielce.tu.przedszkole.przedszkole.model.Person;
import pl.kielce.tu.przedszkole.przedszkole.repository.NewsRepository;
import pl.kielce.tu.przedszkole.przedszkole.repository.PersonRepository;
import pl.kielce.tu.przedszkole.przedszkole.service.PersonService.PersonListStrategy.PersonListContext;
import pl.kielce.tu.przedszkole.przedszkole.service.PersonService.PersonListStrategy.RoleEnum;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonListContext personListContext;
    private final NewsRepository newsRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository,
                             PersonListContext personListContext,
                             NewsRepository newsRepository) {
        this.personRepository = personRepository;
        this.personListContext = personListContext;
        this.newsRepository = newsRepository;
    }


    @Override
    public void addPerson(String issuerUsername, Person person) throws Exception {
        Optional<Person> personTmp = personRepository.findByLogin(person.getLogin());
        if (personTmp.isPresent()) {
            throw new Exception("Person with that login already exists!");
        }

        personRepository.save(person);
    }

    @Override
    public void editPerson(String issuerUsername, Person person) throws Exception {
        Optional<Person> personToEdit = personRepository.findById(person.getId());
        if (!personToEdit.isPresent()) {
            throw new Exception("Edited person does not exist!");
        }
        Person editedPerson = personToEdit.get();
        editedPerson.setName(person.getName());
        editedPerson.setSurname(person.getSurname());
        editedPerson.setEmail(person.getEmail());
        editedPerson.setRole(person.getRole());
        editedPerson.setCity(person.getCity());
        editedPerson.setBirthdate(editedPerson.getBirthdate());
        editedPerson.setPesel(person.getPesel());
        editedPerson.setPhonenumber(person.getPhonenumber());
        editedPerson.setStreet(person.getStreet());
        editedPerson.setStreetAddress(person.getStreetAddress());
        editedPerson.setPostale(person.getPostale());
        personRepository.save(editedPerson);
    }

    @Override
    public void deletePerson(String issuerUsername, Long personId) throws Exception {
        Optional<Person> person = personRepository.findById(personId);
        if (!person.isPresent()) {
            throw new Exception("Person to delete not found");
        }

        personRepository.delete(person.get());
    }

    @Override
    public List<Person> getAdmins() {
        personListContext.setStrategy(RoleEnum.ADMIN);
        return personListContext.getList();
    }

    @Override
    public List<Person> getTeachers() {
        personListContext.setStrategy(RoleEnum.TEACHER);
        return personListContext.getList();
    }

    @Override
    public List<Person> getParents() {
        personListContext.setStrategy(RoleEnum.PARENT);
        return personListContext.getList();
    }

    @Override
    public Person getPersonById(Long personId) {
        Optional<Person> personById = personRepository.findById(personId);

        return personById.orElse(null);
    }
}
