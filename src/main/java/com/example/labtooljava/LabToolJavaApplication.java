package com.example.labtooljava;

import com.example.labtooljava.Login.Login;
import com.example.labtooljava.Login.LoginRepository;
import com.example.labtooljava.Login.LoginService;
import com.example.labtooljava.Person.Person;
import com.example.labtooljava.Person.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

//(scanBasePackages = { "Person" })
@SpringBootApplication(scanBasePackages = {"com.example.labtooljava.Login", "com.example.labtooljava.Person"})
//@ComponentScan(basePackages = { "Person" }))
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/test")
public class LabToolJavaApplication extends WebSecurityConfigurerAdapter {
    @Autowired
    LoginService loginService;
    private static final Logger log = LoggerFactory.getLogger(LabToolJavaApplication.class);

    public static void main(String[] args) {
        System.out.println(log); SpringApplication.run(LabToolJavaApplication.class, args);
    }



//

//    @GetMapping("hello")
//    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
//
//        String result = jdbcTemplate.queryForObject("SELECT first_name FROM Person where last_name = 'rain'", String.class);
//
//        String sql = "SELECT * FROM student";
//
//        return String.format("Hello %s!", result);
//    }

//    @Bean
//    public CommandLineRunner demo(PersonRepository repository) {
//        return (args) -> {
//            //repository.save(new Person("abc12345", "Jamie", "Snow", "jamie.snow.2018@email.com", "student"));
////            repository.save(new Person("qwe98765", "Paul", "Bruce", "paul.bruce.2016@email.com", "demonstrator"));
////            repository.save(new Person("vbg45963", "George", "McCalister", "george.mccalister.2017@email.com", "lecturer"));
////            repository.save(new Person("htf95874", "Rosie", "Bell", "rosie.bell.2018@email.com", "student"));
//            repository.save(new Person("wef15136", "Claire", "Wilson", "claire.wilson.2016@email.com", "demonstrator"));
//
//            for (Person person : repository.findAll()) {
//                log.info(person.toString());
//            }
//
//            // fetch an individual customer by ID
//            Person person = repository.findByDsUsername("abc12345");
//            log.info("Customer found with findById(1L):");
//            log.info("--------------------------------");
//            log.info(person.toString());
//            log.info("");
//
//            // fetch customers by last name
//            log.info("Person found with findByLastName('Bauer'):");
//            log.info("--------------------------------------------");
//            Person person2 = repository.findByLastName("Snow");
//            log.info(person2.toString());
//
//        };
//    }

//    @Bean
//    public CommandLineRunner addLogin(LoginRepository loginRepository) {
//        return (args) -> {
//            loginRepository.save(new Login("abc12345", "admin", "1"));
//        };
//    }

//    @Bean
//    public CommandLineRunner addLogin(LoginRepository loginRepository) {
//        return (args) -> {
//            //loginRepository.save(new Login("abc12345", "admin", "1"));
//            loginService.registerNewUserAccount();
//        };
//    }

//    @Bean
//    public CorsFilter corsFilter() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.setAllowCredentials(true);
//        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
//        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
//                "Accept", "Authorization", "Origin, Accept", "X-Requested-With",
//                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
//        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
//                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
//        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
//        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
//        return new CorsFilter(urlBasedCorsConfigurationSource);
//    }

//    @Bean
//    public ApplicationSecurity applicationSecurity() {
//        return new ApplicationSecurity();
//    }

//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/", "/users", "/login").permitAll().anyRequest()
//                .authenticated()
//                .anyRequest().hasAuthority("READ_PRIVILEGE")
//                .and()
//                .csrf().disable()
//                .formLogin()
//                .loginPage("/")
//                .defaultSuccessUrl("/")
//                .failureUrl("/")
//                .permitAll()
//                .and()
//                .cors().disable();
//
//
//        //http.cors().disable();
//    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        //or any domain that you want to restrict to
        configuration.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization", "Access-Control-Allow-Origin"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST","OPTIONS"));
        //Add the method support as you like
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .logout().and()
                .authorizeRequests()
                .antMatchers("/index.html", "/", "/login").permitAll()
                .anyRequest().authenticated();
                //.and().formLogin().loginPage("/").permitAll();
        http.csrf().disable();
                http.cors().disable();
                http.headers().frameOptions().disable();

           //     .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        // @formatter:on
    }
//    @Order(SecurityProperties.IGNORED_ORDER)
//    protected static class ApplicationSecurity extends WebSecurityConfigurerAdapter {
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http
//                    .authorizeRequests()
//                    .anyRequest().authenticated();
//            http
//                    .formLogin()
//                    .loginPage("/")
//                    .permitAll()
//                    .and()
//                    .logout()
//                    .permitAll().logoutSuccessUrl("/");
//
//
//        }



}
