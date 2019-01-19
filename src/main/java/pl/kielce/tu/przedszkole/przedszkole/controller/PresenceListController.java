package pl.kielce.tu.przedszkole.przedszkole.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kielce.tu.przedszkole.przedszkole.dto.Message;
import pl.kielce.tu.przedszkole.przedszkole.dto.PresenceListActionDto;
import pl.kielce.tu.przedszkole.przedszkole.security.custom.CustomLoginUtility;
import pl.kielce.tu.przedszkole.przedszkole.service.PresenceListProxyDispatcher;

import javax.naming.AuthenticationException;

@CrossOrigin
@RestController
@RequestMapping("/presence")
public class PresenceListController {

    private final CustomLoginUtility customLoginUtility;
    private final PresenceListProxyDispatcher presenceListProxyDispatcher;

    @Autowired
    public PresenceListController(CustomLoginUtility customLoginUtility, PresenceListProxyDispatcher presenceListProxyDispatcher) {
        this.customLoginUtility = customLoginUtility;
        this.presenceListProxyDispatcher = presenceListProxyDispatcher;
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    ResponseEntity<?> addEntry(@RequestBody PresenceListActionDto presenceListActionDto) {
        Message message;
        try {
           customLoginUtility.validateAuthentication(presenceListActionDto.getLoginData());
           presenceListProxyDispatcher.addEntry(presenceListActionDto.getLoginData().getLogin(), presenceListActionDto.getPresenceListEntry());
           message = new Message(200, "Presence entry added.");
           return ResponseEntity.ok(message);
        }catch(AuthenticationException e) {
            message = new Message(401, "Invalid auth data");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
        }
        catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value="/add_list", method = RequestMethod.POST)
    ResponseEntity<?> addEntries(@RequestBody PresenceListActionDto presenceListActionDto) {
        Message message;
        try {
            customLoginUtility.validateAuthentication(presenceListActionDto.getLoginData());
            presenceListProxyDispatcher.addEntries(presenceListActionDto.getLoginData().getLogin(), presenceListActionDto.getPresenceListEntries());
            message = new Message(200, "Presence entries added.");
            return ResponseEntity.ok(message);
        }catch(AuthenticationException e) {
            message = new Message(401, "Invalid auth data");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
        }
        catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value="/edit", method = RequestMethod.POST)
    ResponseEntity<?> editEntry(@RequestBody PresenceListActionDto presenceListActionDto) {
        Message message;
        try {
            customLoginUtility.validateAuthentication(presenceListActionDto.getLoginData());
            presenceListProxyDispatcher.editEntry(presenceListActionDto.getLoginData().getLogin(), presenceListActionDto.getPresenceListEntry());
            message = new Message(200, "Presence entry edited.");
            return ResponseEntity.ok(message);
        }catch(AuthenticationException e) {
            message = new Message(401, "Invalid auth data");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
        }
        catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value="/delete", method = RequestMethod.POST)
    ResponseEntity<?> deleteEntry(@RequestBody PresenceListActionDto presenceListActionDto) {
        Message message;
        try {
            customLoginUtility.validateAuthentication(presenceListActionDto.getLoginData());
            presenceListProxyDispatcher.deleteEntry(presenceListActionDto.getLoginData().getLogin(), presenceListActionDto.getPresenceListEntryId());
            message = new Message(200, "Presence entry added.");
            return ResponseEntity.ok(message);
        }catch(AuthenticationException e) {
            message = new Message(401, "Invalid auth data");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
        }
        catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value="/list/id", method = RequestMethod.POST)
    ResponseEntity<?> getEntryById(@RequestBody PresenceListActionDto presenceListActionDto) {
        Message message;
        try {
            customLoginUtility.validateAuthentication(presenceListActionDto.getLoginData());
            return ResponseEntity.ok(presenceListProxyDispatcher.getPresenceEntryById(presenceListActionDto.getLoginData().getLogin(), presenceListActionDto.getPresenceListEntryId()));

        }catch(AuthenticationException e) {
            message = new Message(401, "Invalid auth data");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
        }
        catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value="/list", method = RequestMethod.POST)
    ResponseEntity<?> getEntriesByClassAndDate(@RequestBody PresenceListActionDto presenceListActionDto) {
        Message message;
        try {
            customLoginUtility.validateAuthentication(presenceListActionDto.getLoginData());
            return ResponseEntity.ok(presenceListProxyDispatcher.getPresenceEntryByClassAndDate(presenceListActionDto.getLoginData().getLogin(), presenceListActionDto.getClassId(), presenceListActionDto.getDate()));

        }catch(AuthenticationException e) {
            message = new Message(401, "Invalid auth data");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
        }
        catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
