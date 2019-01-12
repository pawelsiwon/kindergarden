package pl.kielce.tu.przedszkole.przedszkole.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kielce.tu.przedszkole.przedszkole.repository.PersonRepository;

@Service
public class AdminService {

    private final PersonRepository personRepository;

    @Autowired
    public AdminService(PersonRepository personRepository) {

        this.personRepository = personRepository;
    }
}
