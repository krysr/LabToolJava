package com.example.labtooljava.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PersonController {

    @Autowired
    PersonService personService;

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @PostMapping("/")
    @ResponseBody
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
                return personRepository.findByDsUsername(username);
            }
        }
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