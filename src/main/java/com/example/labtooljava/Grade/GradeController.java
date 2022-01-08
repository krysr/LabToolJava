package com.example.labtooljava.Grade;

import com.example.labtooljava.Demo.DemoRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class GradeController {

    private final DemoRepository demoRepo;

    private final GradeRepository gradeRepo;

    public GradeController(DemoRepository demoRepo, GradeRepository gradeRepo) {
        this.demoRepo = demoRepo;
        this.gradeRepo = gradeRepo;
    }

    @PostMapping("/grade/student/")
    public Grade setGrade(@RequestBody Grade grade, @RequestHeader HttpHeaders req) {

        this.gradeRepo.save(grade);
        this.demoRepo.setDemo("done", grade.getDemo().getLab().getLabId(), grade.getDemo().getPerson().getDsUsername());
        this.demoRepo.setPos(grade.getDemo().getLab().getLabId(), grade.getDemo().getPerson().getDsUsername(), 0);
        return this.gradeRepo.findAllByDemo_DemoId(grade.getDemo().getdemoId()).get(0);
    }

    @GetMapping("/grade/student/{username}/{labid}")
    public Grade getGrade(@PathVariable() String username, @PathVariable() int labid, @RequestHeader HttpHeaders req) {
        return this.gradeRepo.findByDemo_Person_DsUsernameAndDemo_Lab_LabId(username, labid);
    }
}
