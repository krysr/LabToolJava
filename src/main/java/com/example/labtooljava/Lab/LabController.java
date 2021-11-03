package com.example.labtooljava.Lab;

import com.example.labtooljava.Person.Person;
import com.example.labtooljava.Person.PersonRepository;
import com.example.labtooljava.PersonLab.PersonLab;
import com.example.labtooljava.PersonLab.PersonLabRepository;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
//@EnableJpaRepositories(basePackages = "com.example.labtooljava.PersonLab")
//@EntityScan(basePackages = "PersonLab")
//@ComponentScan(basePackages = {"com.example.labtooljava.PersonLab"})
public class LabController {

//    @Autowired
//    private PersonLabRepository personLabRepo;

    private final PersonLabRepository personLabRepo;

    private final LabRepository labRepository;

    public LabController(LabRepository labRepository, PersonLabRepository personLabRepo) {
        this.labRepository = labRepository;
        this.personLabRepo = personLabRepo;
    }

//    @GetMapping("/lab/{email}")
//    public List<Lab> getLab(@PathVariable() String email) {
//        return labRepository.findAllByEmail(email);
//    }

    @GetMapping("/lab/{username}")
    @ResponseBody
    public List<Lab> getLab(@PathVariable() String username) {
        List<Lab> userLabs = new ArrayList<>();
//        List<PersonLab> userLabs2 = personLabRepo.findAll();
        List<PersonLab> personLabs = personLabRepo.findAllByPerson_DsUsername(username);
        for(PersonLab pl: personLabs){
          userLabs.add(labRepository.findByLabId(pl.getLab().getLabId()));
        }
        return userLabs;
    }

    @PostMapping("/lab/demonstrate/{username}")
    @ResponseBody
    public List<PersonLab> addStudentDemo(@RequestBody Lab lab, @PathVariable() String username, @RequestHeader HttpHeaders req) {
        int pos;
        boolean alreadySet = personLabRepo.getDemo(lab.getLabId(), username);
        if(!alreadySet) {
            personLabRepo.setDemo(lab.getLabId(),username);
            if(personLabRepo.getMaxPos() > 0) {
                pos = personLabRepo.getMaxPos() + 1;
                personLabRepo.setPos(lab.getLabId(),username, pos);
            } else {
                pos = 1;
            }
            personLabRepo.setPos(lab.getLabId(),username, pos);
        }
        return personLabRepo.findAllByLab_LabIdAndDemoOrderByPositionAsc(lab.getLabId(), true);
    }
}
