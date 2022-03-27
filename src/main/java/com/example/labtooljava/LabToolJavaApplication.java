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
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication(scanBasePackages = {"com.example.labtooljava.Lab",
        "com.example.labtooljava.Person", "com.example.labtooljava.Demo",
        "com.example.labtooljava.LabClass", "com.example.labtooljava.Grade",
        "com.example.labtooljava.Statistic"})
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

