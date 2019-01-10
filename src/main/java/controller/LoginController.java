package controller;

import dto.LoginData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    ResponseEntity<?> loginProcedure(LoginData login, HttpServletRequest httpServletRequest) {
        if(login.getLogin().equals("admin") && login.getPassword().equals("admin")) {
            return ResponseEntity.ok("Zalogowalo Cie... chyba.");
        }
        else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("fail.");
    }
}
