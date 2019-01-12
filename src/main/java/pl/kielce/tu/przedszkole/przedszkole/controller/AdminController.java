package pl.kielce.tu.przedszkole.przedszkole.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kielce.tu.przedszkole.przedszkole.security.custom.CustomLoginUtility;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {
    private final CustomLoginUtility customLoginUtility;

    @Autowired
    public AdminController(CustomLoginUtility customLoginUtility) {
        this.customLoginUtility = customLoginUtility;
    }



}
