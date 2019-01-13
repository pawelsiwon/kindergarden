package pl.kielce.tu.przedszkole.przedszkole.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kielce.tu.przedszkole.przedszkole.dto.ChildActionDto;
import pl.kielce.tu.przedszkole.przedszkole.dto.LoginData;
import pl.kielce.tu.przedszkole.przedszkole.dto.Message;
import pl.kielce.tu.przedszkole.przedszkole.security.custom.CustomLoginUtility;
import pl.kielce.tu.przedszkole.przedszkole.service.MainAppService;

import javax.naming.AuthenticationException;

@RestController
@CrossOrigin
@RequestMapping("/child")
public class ChildController {
    private final CustomLoginUtility customLoginUtility;
    private final MainAppService mainAppService;

    @Autowired
    public ChildController(CustomLoginUtility customLoginUtility, MainAppService mainAppService) {
        this.customLoginUtility = customLoginUtility;
        this.mainAppService = mainAppService;
    }

    @CrossOrigin
    @RequestMapping(value="/add", method = RequestMethod.POST)
    ResponseEntity<?> addChild(@RequestBody ChildActionDto childActionDto) {
        Message message;
        try {
            customLoginUtility.validateAuthentication(childActionDto.getLoginData());
            mainAppService.addChild(childActionDto.getLoginData().getLogin(), childActionDto.getChild());
            message = new Message(200, "Child added.");
            return ResponseEntity.ok(message);
        } catch(AuthenticationException e){
            message = new Message(401, "Invalid auth data");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @CrossOrigin
    @RequestMapping(value="/edit", method = RequestMethod.POST)
    ResponseEntity<?> editChild(@RequestBody ChildActionDto childActionDto) {
        Message message;
        try {
            customLoginUtility.validateAuthentication(childActionDto.getLoginData());
            mainAppService.editChild(childActionDto.getLoginData().getLogin(), childActionDto.getChild());
            message = new Message(200, "Child edited.");
            return ResponseEntity.ok(message);
        } catch(AuthenticationException e){
            message = new Message(401, "Invalid auth data");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    ResponseEntity<?> deletePerson(@RequestBody ChildActionDto childActionDto) {
        Message message;
        try {
            customLoginUtility.validateAuthentication(childActionDto.getLoginData());
            mainAppService.deleteChild(childActionDto.getLoginData().getLogin(), childActionDto.getChildId());
            message = new Message(200, "Child deleted.");
            return ResponseEntity.ok(message);
        } catch(AuthenticationException e) {
            message = new Message(401, "Invalid auth data");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/list", method=RequestMethod.POST)
    ResponseEntity<?> getChildren(@RequestBody LoginData loginData) {
        Message message;
        try {
            customLoginUtility.validateAuthentication(loginData);
            return ResponseEntity.ok(mainAppService.getChildren(loginData.getLogin()));
        } catch(AuthenticationException e) {
            message = new Message(401, "Invalid auth data");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    ResponseEntity<?> getChildById(@RequestBody ChildActionDto childActionDto) {
        Message message;
        try {
            customLoginUtility.validateAuthentication(childActionDto.getLoginData());
            return ResponseEntity.ok(mainAppService.getChildById(childActionDto.getLoginData().getLogin()
                    ,childActionDto.getChildId()));
        } catch(AuthenticationException e) {
            message = new Message(401, "Invalid auth data");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
        }
    }
}
