package com.example.labtooljava.Lab;

import com.example.labtooljava.Demo.Demo;
import com.example.labtooljava.Demo.DemoRepository;
import com.example.labtooljava.Person.Person;
import com.example.labtooljava.Person.PersonRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class LabController {

    private final DemoRepository demoRepo;

    private final LabRepository labRepository;

    private final PersonRepository personRepository;

    public LabController(LabRepository labRepository, DemoRepository demoRepo, PersonRepository personRepository) {
        this.labRepository = labRepository;
        this.demoRepo = demoRepo;
        this.personRepository = personRepository;
    }

    @GetMapping("/lab/{username}")
    @ResponseBody
    public List<Lab> getLab(@PathVariable() String username, @RequestParam("role") String role) {
        boolean isInstructor = true;
        if(role.equals("student")) {
           isInstructor = false;
        }
        List<Lab> userLabs = new ArrayList<>();
        List<Demo> demos = demoRepo.findAllByInstructorAndPerson_DsUsername(isInstructor, username);
        for(Demo pl: demos){
          userLabs.add(labRepository.findByLabId(pl.getLab().getLabId()));
        }
        return userLabs;
    }

    @PostMapping("/lab/demonstrate/{username}")
    public List<Demo> addStudentDemo(@RequestBody Lab lab, @PathVariable() String username, @RequestHeader HttpHeaders req) {
        int pos;
        String alreadySet = demoRepo.getDemo(lab.getLabId(), username);
        if(!alreadySet.equals("yes")) {
            demoRepo.setDemo("yes", lab.getLabId(),username);
            if(demoRepo.getMaxPos() > 0) {
                pos = demoRepo.getMaxPos() + 1;
                demoRepo.setPos(lab.getLabId(),username, pos);
            } else {demoRepo.findAllByLab_LabIdAndDemoInOrderByPositionAsc(lab.getLabId(), Arrays.asList("yes", "live"));
                pos = 1;
            }
            demoRepo.setPos(lab.getLabId(),username, pos);
        }
        return demoRepo.findAllByLab_LabIdAndDemoInOrderByPositionAsc(lab.getLabId(), Arrays.asList("yes", "live"));
    }

    @PostMapping("/lab/demonstrate/")
    @ResponseBody
    public List<Demo> getQueue(@RequestBody Lab lab, @RequestHeader HttpHeaders req) {
        return demoRepo.findAllByLab_LabIdAndDemoInOrderByPositionAsc(lab.getLabId(), Arrays.asList("yes", "live", "done"));
    }

    @PostMapping("/lab/demonstrate/end/{username}")
    public List<Demo> removeStudentDemo(@RequestBody Demo demo, @PathVariable() String username, @RequestHeader HttpHeaders req) {
        demoRepo.setDemo(demo.getDemo(), demo.getLab().getLabId(),demo.getPerson().getDsUsername());
        return demoRepo.findAllByLab_LabIdAndDemoInOrderByPositionAsc(demo.getLab().getLabId(), Arrays.asList("yes", "live"));
    }

    @PostMapping("/lab/list/{labId}")
    public void addStudentLab(@RequestBody() String result[], @PathVariable() int labId, @RequestHeader HttpHeaders req) {
        String email;
        int size = result.length;
        for (int i = 0; i<size; i++) {
            email = result[i].toLowerCase();
            Person p = this.personRepository.findByEmail(email);
            if (p != null && demoRepo.findAllByPerson_EmailAndLab_LabId(email, labId) == null) {
                Demo d = new Demo(labRepository.findByLabId(labId), p, "no", 0, false);
                this.demoRepo.save(d);
            }
        }
    }

    @PostMapping("/lab/assign/{email}/{type}")
    public void assignToLab(@RequestBody Lab lab, @PathVariable() String email, @PathVariable() String type, @RequestHeader HttpHeaders req) {
        boolean isInstructor = type.equals("demonstrator");
        Demo d = new Demo(lab, this.personRepository.findByEmail(email), "no", 0, isInstructor);
        if (this.demoRepo.findAllByPerson_EmailAndLab_LabId(email, lab.getLabId()).size() == 0) {
            this.demoRepo.save(d);
        }
    }

    @PostMapping("/lab/add/{username}")
    public void addNewLab(@RequestBody Lab lab, @PathVariable() String username, @RequestHeader HttpHeaders req) {
        if(this.labRepository.findByLabClass_ClassIdAndLabDayAndStartTime(lab.getLabClass().getClassId(), lab.getlabDay(), lab.getstartTime()) == null) {
            this.labRepository.save(lab);
            Demo d = new Demo(lab, this.personRepository.findByDsUsername(username), "no", 0, true);
            this.demoRepo.save(d);
        }
    }
}
