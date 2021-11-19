package com.example.labtooljava;

import com.example.labtooljava.Grade.GradeRepository;
import com.example.labtooljava.Lab.LabRepository;
import com.example.labtooljava.Person.PersonService;
import com.example.labtooljava.Demo.DemoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;

//(scanBasePackages = { "Person" })
@SpringBootApplication(scanBasePackages = {"com.example.labtooljava.Lab", "com.example.labtooljava.Person", "com.example.labtooljava.Demo", "com.example.labtooljava.LabClass", "com.example.labtooljava.Grade"})
//@ComponentScan(basePackages = { "Person" }))
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/test")
public class LabToolJavaApplication extends WebSecurityConfigurerAdapter {

    @Autowired
    PersonService personService;

    @Autowired
    LabRepository labRepository;

    @Autowired
    DemoRepository demoRepository;

    @Autowired
    GradeRepository gradeRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    //
//    @Autowired
//    PersonLabRepository personLabRepository;

    private static final Logger log = LoggerFactory.getLogger(LabToolJavaApplication.class);

    public static void main(String[] args) {
//        LabToolJavaApplication lb = new LabToolJavaApplication();
//        lb.encryptPw();
        System.out.println(log); SpringApplication.run(LabToolJavaApplication.class, args);
    }

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

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:4200");
            }
        };
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .logout().and()
                .authorizeRequests()
//                .antMatchers("/index.html", "/student", "/login", "/", "/student/*",
//                        "/lab", "/lab/*", "/lab/demonstrate/*", "/lab/demonstrate/end/*", "/grade", "/grade/student",
//               "/grade/student/*", "grade/student/*", "grade/student/{username}/*", "grade/student/{username}/{labid}" ).permitAll()
                .antMatchers("/**" ).permitAll()
                .anyRequest().authenticated();
                //.and().formLogin().loginPage("/").permitAll();
        http.csrf().disable();
                http.cors().disable();
                http.headers().frameOptions().disable();

           //     .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        // @formatter:on
    }

//    @Bean
//    public PasswordEncoder encoder() {
//        return new BCryptPasswordEncoder();
//    }

//    public void encryptPw() {
////        System.out.println(passwordEncoder.encode("student4"));
//        System.out.println(new BCryptPasswordEncoder().encode("student7"));
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // @formatter:off
//        http
//                .httpBasic().and()
//                .logout().and()
//                .authorizeRequests()
//                .antMatchers("/index.html", "/", "/home", "/login").permitAll()
//                .anyRequest().authenticated().and()
//                .csrf()
//                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
//        // @formatter:on
//    }

//    @Bean
//    HeaderHttpSessionStrategy sessionStrategy() {
//        return new HeaderHttpSessionStrategy();
//    }
}

