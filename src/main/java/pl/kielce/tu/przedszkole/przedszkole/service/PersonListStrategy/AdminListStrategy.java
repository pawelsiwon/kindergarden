package pl.kielce.tu.przedszkole.przedszkole.service.PersonListStrategy;

import pl.kielce.tu.przedszkole.przedszkole.model.Person;
import pl.kielce.tu.przedszkole.przedszkole.repository.PersonRepository;

import java.util.List;
import java.util.stream.Collectors;

public class AdminListStrategy implements PersonListStrategy {
    @Override
    public List<Person> getList(PersonRepository personRepository) {
        return personRepository.findAll()
                .stream()
                .filter(person -> person.getRole().equals("ADMIN"))
                .collect(Collectors.toList());
    }
}
