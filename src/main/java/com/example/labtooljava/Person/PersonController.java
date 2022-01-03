package com.example.labtooljava.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//@RestController
//@CrossOrigin(origins = "http://localhost:4200")
//public class PersonController {
//    public String getUser(HttpServletRequest request) {
//        Principal principal = request.getUserPrincipal();
//        return principal.getName();
//    }
//}
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PersonController {

    @Autowired
    PersonService personService;

    private final PersonRepository personRepository;
    //private final LoginImpl li = new LoginImpl();

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @PostMapping("/")
    @ResponseBody
//    public boolean validateLogin(HttpServletRequest req) {
    public Person validateLogin(@RequestHeader HttpHeaders req) {
        System.out.println("headers: " + req.getFirst(HttpHeaders.AUTHORIZATION));
        String username = "";
        String password = "";
        final String authorization = req.getFirst(HttpHeaders.AUTHORIZATION);
        if (authorization != null && authorization.toLowerCase().startsWith("basic")) {
            String cred = new String(Base64.getDecoder().decode(authorization.substring(6)));
            username = cred.split(":")[0];
            password = cred.split(":")[1];
        }
        String encryptedPassword;
        if (personRepository.findByDsUsername(username) != null) {
            encryptedPassword = personRepository.findByDsUsername(username).getPassword();
            if (personService.doPasswordsMatch(password, encryptedPassword)) {
                // return personRepository.findByDsUsername(username).getFirstName();
                //return true;
                // role = personRepository.findByDsUsername(username).getRole();
                return personRepository.findByDsUsername(username);
            }
        }
        //return personRepository.findByDsUsername(person).getFirstName();
        //return false;
        return null;
    }

    @GetMapping("/student/{username}")
    public Person getStudent(@PathVariable() String username) {
        return personRepository.findByDsUsername(username);
    }

    @GetMapping("/user/{username}")
    public Person getStudentName(@PathVariable() String username) {
        return personRepository.findByDsUsername(username);
    }

}