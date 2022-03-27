package com.example.labtooljava.LabClass;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LabClassController {

    private final LabClassRepository labClassRepository;

    public LabClassController(LabClassRepository labClassRepository) {
        this.labClassRepository = labClassRepository;
    }

    /** Adds new class **/
    @PostMapping("/class/add")
    public void addNewClass(@RequestBody LabClass labclass) {
        if(this.labClassRepository.countAllByClassId(labclass.getClassId()) == 0) {
            this.labClassRepository.save(labclass);
        }
    }
}
