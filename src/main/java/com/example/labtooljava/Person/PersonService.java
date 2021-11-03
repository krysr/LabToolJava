package com.example.labtooljava.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Autowired
//    private PersonRepository personRepository;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    public Boolean doPasswordsMatch(String rawPassword,String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

}
