//package com.example.labtooljava.Login;
//
////import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
////import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class LoginService {
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private LoginRepository loginRepository;
//
//    @Bean
//    public PasswordEncoder encoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    public Login registerNewUserAccount() {
//
//        final Login login = new Login();
//
//        login.setDsUsername("wef15136");
//        login.setPassword(passwordEncoder.encode("demonstrator2"));
//        return loginRepository.save(login);
//    }
//
//    public Boolean doPasswordsMatch(String rawPassword,String encodedPassword) {
//        return passwordEncoder.matches(rawPassword, encodedPassword);
//    }
//
//}
