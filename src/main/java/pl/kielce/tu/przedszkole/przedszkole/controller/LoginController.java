package pl.kielce.tu.przedszkole.przedszkole.controller;

import pl.kielce.tu.przedszkole.przedszkole.dto.LoginData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class LoginController {

    private static Logger logger = Logger.getLogger(LoginController.class.getName());

    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    ResponseEntity<?> loginProcedure(@RequestBody LoginData login, HttpServletRequest httpServletRequest) {

        logger.info("Otrzymany login i haslo to: "+login.getLogin()+":"+login.getPassword());
        if(login.getLogin().equals("admin") && login.getPassword().equals("admin")) {

            return addCorsHeaders(ResponseEntity.ok("Zalogowalo Cie... chyba."));
        }
        else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("fail.");
    }
   /*@RequestMapping(value="/login/{login}/{password}", method = RequestMethod.GET)
   ResponseEntity<?> loginProcedure(@PathVariable(name = "login") String login,@PathVariable(name = "password") String password, HttpServletRequest httpServletRequest) {

       if(login.equals("admin") && password.equals("admin")) {
           return ResponseEntity.ok("Zalogowalo Cie... chyba.");
       }
       else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("fail.");
   }
    */

    ResponseEntity<?> addCorsHeaders(ResponseEntity rsp) {
        rsp.getHeaders().add("Access-Control-Allow-Origin", "*");
        rsp.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, PATCH, HEAD");
        rsp.getHeaders().add("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
        rsp.getHeaders().add("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, Access-Control-Allow-Credentials");
        rsp.getHeaders().add("Access-Control-Allow-Credentials", "true");

        return rsp;
    }

    @RequestMapping(value="/test", method=RequestMethod.GET)
    ResponseEntity<?> test() {
        return ResponseEntity.ok("reee");
    }
}
