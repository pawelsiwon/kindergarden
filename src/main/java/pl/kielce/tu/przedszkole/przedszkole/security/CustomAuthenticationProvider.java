package pl.kielce.tu.przedszkole.przedszkole.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.kielce.tu.przedszkole.przedszkole.repository.PersonRepository;

import java.util.logging.Logger;

//@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final Logger logger = Logger.getLogger(CustomAuthenticationProvider.class.getName());
    private final MyUserDetailsService myUserDetailsService;
    private final PersonRepository personRepository;

    public CustomAuthenticationProvider(MyUserDetailsService myUserDetailsService, PersonRepository personRepository) {
        this.myUserDetailsService = myUserDetailsService;
        this.personRepository = personRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(11);
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(authentication.getName());
        logger.info("user: ");

        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
