package pl.kielce.tu.przedszkole.przedszkole.service.PersonService.PersonListStrategy;

import pl.kielce.tu.przedszkole.przedszkole.model.Person;
import pl.kielce.tu.przedszkole.przedszkole.repository.PersonRepository;

import java.util.List;

public interface PersonListStrategy {
    List<Person> getList(PersonRepository personRepository);
}
