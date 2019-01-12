package pl.kielce.tu.przedszkole.przedszkole.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.kielce.tu.przedszkole.przedszkole.security.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebConfig extends WebSecurityConfigurerAdapter {

//    private MyUserDetailsService myUserDetailsService;
//
//    @Autowired
//    public WebConfig(MyUserDetailsService myUserDetailsService) {
//        this.myUserDetailsService = myUserDetailsService;
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //super.configure(auth);
//        auth.userDetailsService(myUserDetailsService)
//                .and()
//                .authenticationProvider(authenticationProvider());
//
//    }
//
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(myUserDetailsService);
//        authenticationProvider.setPasswordEncoder(encoder());
//
//        return authenticationProvider;
//    }
//
//    @Bean
//    public PasswordEncoder encoder() {
//
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(11);
//        return new BCryptPasswordEncoder(11);
//
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .cors()
                .and()
                .authorizeRequests()
                .anyRequest()
                .permitAll();

    }



}
