package pl.kielce.tu.przedszkole.przedszkole.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kielce.tu.przedszkole.przedszkole.dto.LoginData;
import pl.kielce.tu.przedszkole.przedszkole.dto.Message;
import pl.kielce.tu.przedszkole.przedszkole.dto.NewsActionDto;
import pl.kielce.tu.przedszkole.przedszkole.security.custom.CustomLoginUtility;
import pl.kielce.tu.przedszkole.przedszkole.service.MainAppService;

import javax.naming.AuthenticationException;

@RestController
@CrossOrigin
@RequestMapping("/news")
public class NewsController {
    private final CustomLoginUtility customLoginUtility;
    private final MainAppService mainAppService;

    @Autowired
    public NewsController(CustomLoginUtility customLoginUtility, MainAppService mainAppService) {
        this.customLoginUtility = customLoginUtility;
        this.mainAppService = mainAppService;
    }

    @CrossOrigin
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    ResponseEntity<?> addNews(@RequestBody NewsActionDto newsActionDto) {
        Message message;
        try {
            customLoginUtility.validateAuthentication(newsActionDto.getLoginData());
            mainAppService.addNews(newsActionDto.getLoginData().getLogin(), newsActionDto.getNews());
            message = new Message(200, "News added.");
            return ResponseEntity.ok(message);
        } catch(AuthenticationException e) {
            message = new Message(401, "Invalid credentials.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
        } catch(Exception e) {
            e.printStackTrace();
            message = new Message(500, "Something went terribly wrong.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    ResponseEntity<?> editNews(@RequestBody NewsActionDto newsActionDto) {
        Message message;
        try {
            customLoginUtility.validateAuthentication(newsActionDto.getLoginData());
            mainAppService.editNews(newsActionDto.getLoginData().getLogin(), newsActionDto.getNews());
            message = new Message(200, "News edited.");
            return ResponseEntity.ok(message);
        } catch(AuthenticationException e) {
            message = new Message(401, "Invalid credentials.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
        } catch(Exception e) {
            e.printStackTrace();
            message = new Message(500, "Something went terribly wrong.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    ResponseEntity<?> deleteNews(@RequestBody NewsActionDto newsActionDto) {
        Message message;
        try {
            customLoginUtility.validateAuthentication(newsActionDto.getLoginData());
            mainAppService.deleteNews(newsActionDto.getLoginData().getLogin(), newsActionDto.getNewsId());
            message = new Message(200, "News deleted.");
            return ResponseEntity.ok(message);
        } catch(AuthenticationException e) {
            message = new Message(401, "Invalid credentials.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
        } catch(Exception e) {
            e.printStackTrace();
            message = new Message(500, "Something went terribly wrong.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    ResponseEntity<?> getNewsList(@RequestBody LoginData loginData) {
        Message message;
        try {
            customLoginUtility.validateAuthentication(loginData);
            return ResponseEntity.ok(mainAppService.getNewsList(loginData.getLogin()));
        } catch (AuthenticationException e) {
            message = new Message(401, "Invalid credentials.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/details", method = RequestMethod.POST)
    ResponseEntity<?> getNewsById(@RequestBody NewsActionDto newsActionDto) {
        Message message;
        try {
            customLoginUtility.validateAuthentication(newsActionDto.getLoginData());
            return ResponseEntity.ok(mainAppService.getNewsById(newsActionDto.getLoginData().getLogin(),newsActionDto.getNewsId()));
        } catch (AuthenticationException e) {
            message = new Message(401, "Invalid credentials.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
        }
    }
}
