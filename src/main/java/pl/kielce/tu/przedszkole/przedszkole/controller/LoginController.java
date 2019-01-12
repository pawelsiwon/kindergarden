package pl.kielce.tu.przedszkole.przedszkole.controller;

import org.springframework.beans.factory.annotation.Autowired;
import pl.kielce.tu.przedszkole.przedszkole.dto.LoginData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kielce.tu.przedszkole.przedszkole.dto.Message;
import pl.kielce.tu.przedszkole.przedszkole.security.custom.CustomLoginUtility;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class LoginController {

    private static Logger logger = Logger.getLogger(LoginController.class.getName());

    private final CustomLoginUtility customLoginUtility;

    @Autowired
    public LoginController(CustomLoginUtility customLoginUtility) {
        this.customLoginUtility = customLoginUtility;
    }

    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    ResponseEntity<?> loginProcedure(@RequestBody LoginData login, HttpServletRequest httpServletRequest) {
        Message message = new Message();
        //logger.info("Otrzymany login i haslo to: "+login.getLogin()+":"+login.getPassword());
        if(customLoginUtility.authenticationCorrect(login)) {
            message.setStatus(200);
            message.setMessage("Pomyslnie zalogowano.");
            return ResponseEntity.ok(message);
        }
        else{
            message.setStatus(401);
            message.setMessage("Invalid credentials.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
        }
    }

    @RequestMapping(value="/test", method=RequestMethod.GET)
    ResponseEntity<?> test() {
        return ResponseEntity.ok("reee");
    }
}
