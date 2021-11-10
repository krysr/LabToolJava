package com.example.labtooljava.Lab;

import com.example.labtooljava.Demo.Demo;
import com.example.labtooljava.Demo.DemoRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
//@EnableJpaRepositories(basePackages = "com.example.labtooljava.Demo")
//@EntityScan(basePackages = "Demo")
//@ComponentScan(basePackages = {"com.example.labtooljava.Demo"})
public class LabController {

//    @Autowired
//    private PersonLabRepository personLabRepo;

    private final DemoRepository demoRepo;

    private final LabRepository labRepository;

    public LabController(LabRepository labRepository, DemoRepository demoRepo) {
        this.labRepository = labRepository;
        this.demoRepo = demoRepo;
    }

//    @GetMapping("/lab/{email}")
//    public List<Lab> getLab(@PathVariable() String email) {
//        return labRepository.findAllByEmail(email);
//    }

    @GetMapping("/lab/{username}")
    @ResponseBody
    public List<Lab> getLab(@PathVariable() String username, @RequestParam("role") String role) {
        boolean isInstructor = true;
        if(role.equals("student")) {
           isInstructor = false;
        }
        List<Lab> userLabs = new ArrayList<>();
//        List<Demo> userLabs2 = personLabRepo.findAll();
        List<Demo> demos = demoRepo.findAllByInstructorAndPerson_DsUsername(isInstructor, username);
        for(Demo pl: demos){
          userLabs.add(labRepository.findByLabId(pl.getLab().getLabId()));
        }
        return userLabs;
    }

    @PostMapping("/lab/demonstrate/{username}")
    //@ResponseBody
    public List<Demo> addStudentDemo(@RequestBody Lab lab, @PathVariable() String username, @RequestHeader HttpHeaders req) {
        int pos;
        boolean alreadySet = demoRepo.getDemo(lab.getLabId(), username);
        if(!alreadySet) {
            demoRepo.setDemo(true, lab.getLabId(),username);
            if(demoRepo.getMaxPos() > 0) {
                pos = demoRepo.getMaxPos() + 1;
                demoRepo.setPos(lab.getLabId(),username, pos);
            } else {
                pos = 1;
            }
            demoRepo.setPos(lab.getLabId(),username, pos);
        }
        return demoRepo.findAllByLab_LabIdAndDemoOrderByPositionAsc(lab.getLabId(), true);
    }

    @PostMapping("/lab/demonstrate/")
    @ResponseBody
    public List<Demo> getQueue(@RequestBody Lab lab, @RequestHeader HttpHeaders req) {
        return demoRepo.findAllByLab_LabIdAndDemoOrderByPositionAsc(lab.getLabId(), true);
    }

    @PostMapping("/lab/demonstrate/end/{username}")
    //@ResponseBody
    public List<Demo> removeStudentDemo(@RequestBody Lab lab, @PathVariable() String username, @RequestHeader HttpHeaders req) {
        demoRepo.setDemo(false, lab.getLabId(),username);
        return demoRepo.findAllByLab_LabIdAndDemoOrderByPositionAsc(lab.getLabId(), true);

    }

}
