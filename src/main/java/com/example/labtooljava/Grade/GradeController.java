package com.example.labtooljava.Grade;

import com.example.labtooljava.Demo.DemoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class GradeController {

    private final DemoRepository demoRepo;
    private final GradeRepository gradeRepo;

    public GradeController(DemoRepository demoRepo, GradeRepository gradeRepo) {
        this.demoRepo = demoRepo;
        this.gradeRepo = gradeRepo;
    }

    /** Persist student's grade to database **/
    @PostMapping("/grade/student/")
    public void setGrade(@RequestBody Grade grade) {
        this.gradeRepo.save(grade);
        this.demoRepo.setDemo("done", grade.getDemo().getLab().getLabId(), grade.getDemo().getPerson().getDsUsername());
        this.demoRepo.setPos(grade.getDemo().getLab().getLabId(), grade.getDemo().getPerson().getDsUsername(), 0);

    }
    /** Returns a list of a student's grades for a lab **/
    @GetMapping("/grade/student/{username}/{labid}")
    public List<Grade> getGrade(@PathVariable() String username, @PathVariable() int labid) {
        return this.gradeRepo.findAllByDemo_DemoId(this.demoRepo.findDemoByPerson_DsUsernameAndLab_LabId(username, labid).getdemoId());
    }

    /** Returns all grades from all students from all labs **/
    @GetMapping("/grade/gradeslist")
    public List<Grade> getAllGrades() {
        return this.gradeRepo.findAll();
    }
}
