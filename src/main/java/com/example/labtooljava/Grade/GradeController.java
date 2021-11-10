package com.example.labtooljava.Grade;

import com.example.labtooljava.Demo.Demo;
import com.example.labtooljava.Demo.DemoRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200/grade")
public class GradeController {

    private final DemoRepository demoRepo;

    private final GradeRepository gradeRepo;

    public GradeController(DemoRepository demoRepo, GradeRepository gradeRepo) {
        this.demoRepo = demoRepo;
        this.gradeRepo = gradeRepo;
    }

    @PostMapping("/")
    public void setGrade(@RequestBody Grade grade, @RequestHeader HttpHeaders req) {

        //Demo demo = new Demo();
        Demo demo = this.demoRepo.findDemoByDemoId(grade.getDemo().getdemoId());
        Grade newGrade = new Grade();
        newGrade.setGrade(grade.getGrade());
        newGrade.setDemo(demo);
        newGrade.setAssessmentName(grade.getAssessmentName());
        newGrade.setgradeComment(grade.getgradeComment());

        this.gradeRepo.save(newGrade);




    }
}
