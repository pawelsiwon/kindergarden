package pl.kielce.tu.przedszkole.przedszkole.service.PersonService.PersonListStrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kielce.tu.przedszkole.przedszkole.model.Person;
import pl.kielce.tu.przedszkole.przedszkole.repository.PersonRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PersonListContext {
    private PersonListStrategy selectedStrategy;
    private Map<RoleEnum, PersonListStrategy> personListStrategyMap;
    private final PersonRepository personRepository;

    @Autowired
    public PersonListContext(PersonRepository personRepository) {
        this.personRepository = personRepository;
        this.personListStrategyMap = new HashMap<>();
        personListStrategyMap.put(RoleEnum.ADMIN, new AdminListStrategy());
        personListStrategyMap.put(RoleEnum.TEACHER, new TeacherListStrategy());
        personListStrategyMap.put(RoleEnum.PARENT, new ParentListStrategy());
    }

    public void setStrategy(RoleEnum role) {
        selectedStrategy = personListStrategyMap.get(role);
    }

    public List<Person> getList() {
        return selectedStrategy.getList(personRepository);
    }
}
