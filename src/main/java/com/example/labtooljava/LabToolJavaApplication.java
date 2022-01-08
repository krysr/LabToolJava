package com.example.labtooljava;

import com.example.labtooljava.Grade.GradeRepository;
import com.example.labtooljava.Lab.LabRepository;
import com.example.labtooljava.LabClass.LabClassRepository;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;

@SpringBootApplication(scanBasePackages = {"com.example.labtooljava.Lab", "com.example.labtooljava.Person", "com.example.labtooljava.Demo", "com.example.labtooljava.LabClass", "com.example.labtooljava.Grade", "com.example.labtooljava.Statistic"})
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api")
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
    LabClassRepository labClassRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    private static final Logger log = LoggerFactory.getLogger(LabToolJavaApplication.class);

    public static void main(String[] args) {
        System.out.println(log);
        SpringApplication.run(LabToolJavaApplication.class, args);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization", "Access-Control-Allow-Origin"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST","OPTIONS", "HEAD"));
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
        http
                .logout().and()
                .authorizeRequests()
                .antMatchers("/**" ).permitAll()
                .anyRequest().authenticated();
        http.csrf().disable();
                http.cors().disable();
                http.headers().frameOptions().disable();
    }
}

