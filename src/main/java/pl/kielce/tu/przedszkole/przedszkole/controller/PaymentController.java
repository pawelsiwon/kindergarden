package pl.kielce.tu.przedszkole.przedszkole.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kielce.tu.przedszkole.przedszkole.dto.LoginData;
import pl.kielce.tu.przedszkole.przedszkole.dto.Message;
import pl.kielce.tu.przedszkole.przedszkole.dto.PaymentActionDto;
import pl.kielce.tu.przedszkole.przedszkole.security.custom.CustomLoginUtility;
import pl.kielce.tu.przedszkole.przedszkole.service.PaymentProxyDispatcher;

import javax.naming.AuthenticationException;

@RestController
@CrossOrigin
@RequestMapping("/payment")
public class PaymentController {

    private final CustomLoginUtility customLoginUtility;
    private final PaymentProxyDispatcher paymentProxyDispatcher;


    public PaymentController(CustomLoginUtility customLoginUtility,
                             PaymentProxyDispatcher paymentProxyDispatcher) {
        this.customLoginUtility = customLoginUtility;
        this.paymentProxyDispatcher = paymentProxyDispatcher;
    }

    @CrossOrigin
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    ResponseEntity<?> addPayment(@RequestBody PaymentActionDto paymentActionDto) {
        Message message;
        try {
            customLoginUtility.validateAuthentication(paymentActionDto.getLoginData());
            paymentProxyDispatcher.addPayment(paymentActionDto);
            message = new Message(200, "Added payment");
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

    @CrossOrigin
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    ResponseEntity<?> editPayment(@RequestBody PaymentActionDto paymentActionDto) {
        Message message;
        try {
            customLoginUtility.validateAuthentication(paymentActionDto.getLoginData());
            paymentProxyDispatcher.editPayment(paymentActionDto);
            message = new Message(200, "Edited payment");
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

    @CrossOrigin
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    ResponseEntity<?> deletePayment(@RequestBody PaymentActionDto paymentActionDto) {
        Message message;
        try {
            customLoginUtility.validateAuthentication(paymentActionDto.getLoginData());
            paymentProxyDispatcher.deletePayment(paymentActionDto);
            message = new Message(200, "Deleted payment");
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

    @CrossOrigin
    @RequestMapping(value = "/list/parent_and_child", method = RequestMethod.POST)
    ResponseEntity<?> getPaymentByParentAndChild(@RequestBody PaymentActionDto paymentActionDto) {
        Message message;
        try {
            customLoginUtility.validateAuthentication(paymentActionDto.getLoginData());
            return ResponseEntity.ok(paymentProxyDispatcher.getPaymentByParentAndChildIds(paymentActionDto));
        }catch(AuthenticationException e) {
            message = new Message(401, "Invalid auth data");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
        }
        catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/details", method = RequestMethod.POST)
    ResponseEntity<?> getPaymentById(@RequestBody PaymentActionDto paymentActionDto) {
        Message message;
        try {
            customLoginUtility.validateAuthentication(paymentActionDto.getLoginData());
            return ResponseEntity.ok(paymentProxyDispatcher.getPaymentById(paymentActionDto));

        }catch(AuthenticationException e) {
            message = new Message(401, "Invalid auth data");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
        }
        catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/all", method = RequestMethod.POST)
    ResponseEntity<?> getAllPayments(@RequestBody LoginData loginData) {
        Message message;
        try {
            customLoginUtility.validateAuthentication(loginData);
            return ResponseEntity.ok(paymentProxyDispatcher.getAllPayments(loginData));
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
