package com.example.labtooljava.Lab;

import com.example.labtooljava.Demo.Demo;
import com.example.labtooljava.Demo.DemoRepository;
import com.example.labtooljava.Person.Person;
import com.example.labtooljava.Person.PersonRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    private final PersonRepository personRepository;

    public LabController(LabRepository labRepository, DemoRepository demoRepo, PersonRepository personRepository) {
        this.labRepository = labRepository;
        this.demoRepo = demoRepo;
        this.personRepository = personRepository;
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
            demoRepo.setDemo("yes", lab.getLabId(),username);
            if(demoRepo.getMaxPos() > 0) {
                pos = demoRepo.getMaxPos() + 1;
                demoRepo.setPos(lab.getLabId(),username, pos);
            } else {
                pos = 1;
            }
            demoRepo.setPos(lab.getLabId(),username, pos);
        }
        return demoRepo.findAllByLab_LabIdAndDemoInOrderByPositionAsc(lab.getLabId(), Arrays.asList("yes", "live"));
    }

//    @PostMapping("/lab/demonstrate/{username}")
//    //@ResponseBody
//    public List<Demo> addStudentDemo(@RequestBody Demo demo, @PathVariable() String username, @RequestHeader HttpHeaders req) {
//        int pos;
//        boolean alreadySet = demoRepo.getDemo(demo.getLab().getLabId(), username);
//        if(!alreadySet) {
//            demoRepo.setDemo("yes", demo.getLab().getLabId(),username);
//            if(demoRepo.getMaxPos() > 0) {
//                pos = demoRepo.getMaxPos() + 1;
//                demoRepo.setPos(demo.getLab().getLabId(),username, pos);
//            } else {
//                pos = 1;
//            }
//            demoRepo.setPos(demo.getLab().getLabId(),username, pos);
//        }
//        return demoRepo.findAllByLab_LabIdAndDemoAndDemoOrderByPositionAsc(demo.getLab().getLabId(), "yes", "live");
//    }

    @PostMapping("/lab/demonstrate/")
    @ResponseBody
    public List<Demo> getQueue(@RequestBody Lab lab, @RequestHeader HttpHeaders req) {
        return demoRepo.findAllByLab_LabIdAndDemoInOrderByPositionAsc(lab.getLabId(), Arrays.asList("yes", "live", "done"));
    }

    @PostMapping("/lab/demonstrate/end/{username}")
    //@ResponseBody
    public List<Demo> removeStudentDemo(@RequestBody Demo demo, @PathVariable() String username, @RequestHeader HttpHeaders req) {
        demoRepo.setDemo(demo.getDemo(), demo.getLab().getLabId(),demo.getPerson().getDsUsername());
        return demoRepo.findAllByLab_LabIdAndDemoInOrderByPositionAsc(demo.getLab().getLabId(), Arrays.asList("yes", "live"));

    }

    @PostMapping("/lab/list/{role}")
    @ResponseBody
    public List<Demo> addStudentLab(@RequestBody Object result, @PathVariable() String role, @RequestHeader HttpHeaders req) {
        System.out.println(result);
       boolean instructor = !role.equals("student");
        String email;
        String username;
        String day;
        String classId;
        int startTime;
        int endTime;
        int size = ((ArrayList) result).size();
        for (int i = 0; i<size; i++) {
            email = ((LinkedHashMap) ((ArrayList) result).get(i)).get("email").toString().toLowerCase();
            day = ((LinkedHashMap) ((ArrayList) result).get(i)).get("day").toString().toLowerCase();
            classId = ((LinkedHashMap) ((ArrayList) result).get(i)).get("class_code").toString().toUpperCase();
            startTime = (int) ((LinkedHashMap) ((ArrayList) result).get(i)).get("start_time");
            endTime = (int) ((LinkedHashMap) ((ArrayList) result).get(i)).get("end_time");
            Lab lab = this.labRepository.findByLabClass_ClassIdAndLabDayAndStartTime(classId, day, startTime);
            Person p = this.personRepository.findByEmail(email);
            Demo d = new Demo(lab, p, "no", 0, instructor);
            this.demoRepo.save(d);
        }
        return this.demoRepo.findAll();
    }

}
