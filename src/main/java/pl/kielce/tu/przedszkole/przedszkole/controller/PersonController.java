package pl.kielce.tu.przedszkole.przedszkole.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kielce.tu.przedszkole.przedszkole.dto.LoginData;
import pl.kielce.tu.przedszkole.przedszkole.dto.Message;
import pl.kielce.tu.przedszkole.przedszkole.dto.PersonActionDto;
import pl.kielce.tu.przedszkole.przedszkole.security.custom.CustomLoginUtility;
import pl.kielce.tu.przedszkole.przedszkole.service.MainAppService;
import pl.kielce.tu.przedszkole.przedszkole.service.PersonProxyDispatcher;

import javax.naming.AuthenticationException;

@RestController
@CrossOrigin
@RequestMapping("/person")
public class PersonController {
    private final CustomLoginUtility customLoginUtility;
    private final MainAppService mainAppService;
    private final PersonProxyDispatcher personProxyDispatcher;
    @Autowired
    public PersonController(CustomLoginUtility customLoginUtility,
                            MainAppService mainAppService,
                            PersonProxyDispatcher personProxyDispatcher) {
        this.customLoginUtility = customLoginUtility;
        this.mainAppService = mainAppService;
        this.personProxyDispatcher = personProxyDispatcher;
    }

    @CrossOrigin
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    ResponseEntity<?> addPerson(@RequestBody PersonActionDto personActionDto) {
        Message message;
        try {
            customLoginUtility.validateAuthentication(personActionDto.getLoginData());
            personProxyDispatcher.addPerson(personActionDto.getLoginData().getLogin(), personActionDto.getPerson());
            message = new Message(200, "Person added.");
            return ResponseEntity.ok(message);
        } catch(AuthenticationException e) {
            message = new Message(401, "Invalid auth data");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
        }
        catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    ResponseEntity<?> editPerson(@RequestBody PersonActionDto personActionDto) {
        Message message;
        try {
            customLoginUtility.validateAuthentication(personActionDto.getLoginData());
            personProxyDispatcher.editPerson(personActionDto.getLoginData().getLogin(), personActionDto.getPerson());
            message = new Message(200, "Person edited.");
            return ResponseEntity.ok(message);
        } catch(AuthenticationException e) {
            message = new Message(401, "Invalid auth data");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
        }
        catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    ResponseEntity<?> deletePerson(@RequestBody PersonActionDto personActionDto) {
        Message message;
        try {
            customLoginUtility.validateAuthentication(personActionDto.getLoginData());
            personProxyDispatcher.deletePerson(personActionDto.getLoginData().getLogin(), personActionDto.getPersonId());
            message = new Message(200, "Person deleted.");
            return ResponseEntity.ok(message);
        } catch(AuthenticationException e) {
            message = new Message(401, "Invalid auth data");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
        }
        catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/list/admins", method = RequestMethod.POST)
    ResponseEntity<?> getAdminsList(@RequestBody LoginData loginData) {
        Message message;
        try {
            customLoginUtility.validateAuthentication(loginData);

            return ResponseEntity.ok(personProxyDispatcher.getAdmins(loginData.getLogin()));
        } catch(AuthenticationException e) {
            message = new Message(401, "Invalid auth data");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
        }
        catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/list/teachers", method = RequestMethod.POST)
    ResponseEntity<?> getTeachersList(@RequestBody LoginData loginData) {
        Message message;
        try {
            customLoginUtility.validateAuthentication(loginData);

            return ResponseEntity.ok(personProxyDispatcher.getTeachers(loginData.getLogin()));
        } catch(AuthenticationException e) {
            message = new Message(401, "Invalid auth data");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
        }
        catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/list/parents", method = RequestMethod.POST)
    ResponseEntity<?> getParentsList(@RequestBody LoginData loginData) {
        Message message;
        try {
            customLoginUtility.validateAuthentication(loginData);

            return ResponseEntity.ok(personProxyDispatcher.getParents(loginData.getLogin()));
        } catch(AuthenticationException e) {
            message = new Message(401, "Invalid auth data");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
        }
        catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    ResponseEntity<?> getPersonById(@RequestBody PersonActionDto personActionDto) {
        Message message;
        try {
            customLoginUtility.validateAuthentication(personActionDto.getLoginData());
            return ResponseEntity.ok(personProxyDispatcher.getPersonById(personActionDto.getLoginData().getLogin()
                                                                            ,personActionDto.getPersonId()));
        } catch(AuthenticationException e) {
            message = new Message(401, "Invalid auth data");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
        }
    }
}
