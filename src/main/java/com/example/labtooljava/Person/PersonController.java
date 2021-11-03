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

//        @GetMapping("/student")
//    public List<Person> getStudents(@RequestHeader HttpHeaders req) {
//        List<Person> students;
//        List<Person> demonstrators;
//        students = personRepository.findAllByRole("student");
//        demonstrators = personRepository.findAllByRole("demonstrator");
//        return Stream.concat(students.stream(), demonstrators.stream()).collect(Collectors.toList());
//    }

//
//    @GetMapping("/token")
//    @ResponseBody
//    //@CrossOrigin(origins = "*",  allowedHeaders = { "x-auth-token", "x-requested-with", "x-xsrf-token" })
//    public Map<String, String> token(HttpSession session) {
//        return Collections.singletonMap("token", session.getId());
//    }
}
//String decodedString = new String(base64.decode(encodedString.getBytes()));