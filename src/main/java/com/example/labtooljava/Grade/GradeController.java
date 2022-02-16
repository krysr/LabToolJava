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

    @PostMapping("/grade/student/")
    public void setGrade(@RequestBody Grade grade) {
        this.gradeRepo.save(grade);
        this.demoRepo.setDemo("done", grade.getDemo().getLab().getLabId(), grade.getDemo().getPerson().getDsUsername());
        this.demoRepo.setPos(grade.getDemo().getLab().getLabId(), grade.getDemo().getPerson().getDsUsername(), 0);

    }

    @GetMapping("/grade/student/{username}/{labid}")
    public List<Grade> getGrade(@PathVariable() String username, @PathVariable() int labid) {
        return this.gradeRepo.findAllByDemo_DemoId(this.demoRepo.findDemoByPerson_DsUsernameAndLab_LabId(username, labid).getdemoId());
    }
}
