package pl.kielce.tu.przedszkole.przedszkole.security.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kielce.tu.przedszkole.przedszkole.dto.LoginData;
import pl.kielce.tu.przedszkole.przedszkole.model.Person;
import pl.kielce.tu.przedszkole.przedszkole.repository.PersonRepository;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class CustomLoginUtility {

    private final PersonRepository personRepository;
    private final Logger logger = Logger.getLogger(CustomLoginUtility.class.getName());

    @Autowired
    public CustomLoginUtility(PersonRepository personRepository) {
        this.personRepository = personRepository;

    }

    public boolean authenticationCorrect(LoginData loginData) {
        String login = loginData.getLogin();
        String password = loginData.getPassword();

        Optional<Person> optionalPerson = personRepository.findByLogin(login);
        //logger.info("Odczytane dane: "+login+":"+password);

        if(optionalPerson.isPresent()) {
            Person checkedPerson = optionalPerson.get();
            if(checkedPerson.getLogin().equals(login) && checkedPerson.getPassword().equals(password)) {
                return true;
            }
        }
        logger.severe("Did not find that user! Something's wrong.");
        return false;
    }
}
