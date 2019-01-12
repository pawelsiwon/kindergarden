package pl.kielce.tu.przedszkole.przedszkole.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.kielce.tu.przedszkole.przedszkole.model.Person;
import pl.kielce.tu.przedszkole.przedszkole.repository.PersonRepository;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final PersonRepository personRepository;

    @Autowired
    public MyUserDetailsService(PersonRepository personRepository) {

        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Person> personOptional = personRepository.findByLogin(s);

        if(!personOptional.isPresent()) {
            throw new UsernameNotFoundException("User not found.");
        }
        else {
            return new UserDetailsWrapper(personOptional.get());
        }
    }
}
