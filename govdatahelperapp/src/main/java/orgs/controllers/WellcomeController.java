package orgs.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Bismark on 15.10.2016.
 */

@RestController
public class WellcomeController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
