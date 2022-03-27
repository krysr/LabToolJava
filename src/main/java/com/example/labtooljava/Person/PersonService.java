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

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    /** Checking passwords macth with source help from Jonas, https://stackoverflow.com/questions/26811885/getting-same-hashed-value-while-using-bcryptpasswordencoder **/
    public Boolean passwordMatch(String pw,String encodedPassword) {
        return passwordEncoder.matches(pw, encodedPassword);
    }

}
