//package com.example.labtooljava.Login;
//
//import com.example.labtooljava.Person.Person;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//import javax.servlet.http.HttpServletRequest;
//
//@RestController
//@CrossOrigin(origins = "http://localhost:4200")
//public class LoginController {
//
//    @Autowired
//    LoginService loginService;
//
//    private final LoginRepository loginRepository;
//    //private final LoginImpl li = new LoginImpl();
//
//    public LoginController(LoginRepository loginRepository) {
//        this.loginRepository = loginRepository;
//    }
//
////    @GetMapping("/login")
////    @ResponseBody
////    public String getLogin(@RequestParam String dsusername) {
////        String incorrect = "wrong it";
////        if (loginRepository.findByDsUsername(dsusername) != null) {
////            return loginRepository.findByDsUsername(dsusername).getDsUsername();
////        }
////        return incorrect;
////    }
////
////    @CrossOrigin(origins = "http://localhost:4200")
//    @PostMapping("/irrelevant")
//    @ResponseBody
//    //@ResponseStatus(HttpStatus.ACCEPTED)
//    //@CrossOrigin(origins = "http://localhost:4200")
//    public boolean getLogin(@RequestBody Login login, @RequestHeader HttpHeaders headers) {
//        String encryptedPassword;
//        if (loginRepository.findByDsUsername(login.getDsUsername()) != null) {
//            encryptedPassword = loginRepository.findByDsUsername(login.getDsUsername()).getPassword();
//            //rawPassword = loginRepository.findPasswordByDsUsername(login.getDsUsername());
//            if(loginService.doPasswordsMatch(login.getPassword(), encryptedPassword)) {
//                System.out.println("headers: "+ headers);
//                return true;
//            }
//            //return loginRepository.findByDsUsername(login.getDsUsername());
//        }
//        return false;
//    }
//
//
//
//
////    @CrossOrigin(origins = "http://localhost:4200")
////    @PostMapping("/")
////    @ResponseBody
////    @ResponseStatus(HttpStatus.OK)
////    public Login getLogin(@RequestBody Login login) {
////        String incorrect = "wrong it";
////        if (loginRepository.findByDsUsername(login.getDsUsername()) != null) {
////            //return loginRepository.findByDsUsername(dsusername).getDsUsername();
////            return loginRepository.findByDsUsername(login.getDsUsername());
////        }
////        return null;
////    }
//
////    @Bean
////    public WebMvcConfigurer corsConfigurer() {
////        return new WebMvcConfigurerAdapter() {
////            @Override
////            public void addCorsMappings(CorsRegistry registry) {
////                registry.addMapping("/")
////                        .allowedOrigins("http://localhost:4200")
////                        .allowedHeaders("Access-Control-Allow-Origin")
////                        .allowCredentials(true).maxAge(3600);
////            }
////        };
////    }
//
//
//
//
////    @CrossOrigin(origins = "http://localhost:4200")
////    @PostMapping("/")
////    @ResponseBody
////    //@ResponseStatus(HttpStatus.OK)
////    public String getLogin(@RequestBody Login login) {
////        String password;
////        if (loginRepository.findByDsUsername(login.getDsUsername()) != null) {
////            //return loginRepository.findByDsUsername(dsusername).getDsUsername();
////            password = loginRepository.findByDsUsername(login.getDsUsername()).getPassword();
////            if(loginService.doPasswordsMatch(login.getPassword(), password)) {
////                return loginRepository.findByDsUsername(login.getDsUsername()).getDsUsername();
////            }
////        }
////        return null;
////    }
//
////    @PostMapping("/login")
////    public void checkLogin(@RequestBody String dsUsername) {
////
////    }
//
//
//}
