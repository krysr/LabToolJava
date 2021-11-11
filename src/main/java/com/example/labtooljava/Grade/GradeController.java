package com.example.labtooljava.Grade;

import com.example.labtooljava.Demo.Demo;
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
        return this.gradeRepo.findAllByDemo_DemoId(grade.getDemo().getdemoId()).get(0);

    }
}
