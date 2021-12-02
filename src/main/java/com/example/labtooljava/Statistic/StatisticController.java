package com.example.labtooljava.Statistic;

import com.example.labtooljava.Demo.DemoRepository;
import com.example.labtooljava.Lab.LabRepository;
import com.example.labtooljava.Person.PersonRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class StatisticController {

    private final DemoRepository demoRepo;

    private final LabRepository labRepository;

    private final PersonRepository personRepository;

    private final StatisticRepository statRepository;

    public StatisticController(LabRepository labRepository, DemoRepository demoRepo, PersonRepository personRepository, StatisticRepository statRepository) {
        this.labRepository = labRepository;
        this.demoRepo = demoRepo;
        this.personRepository = personRepository;
        this.statRepository = statRepository;
    }

    @PostMapping("/stats/{role}")
    public List<Statistic> saveStats(@RequestBody Statistic stat, @PathVariable() String role, @RequestHeader HttpHeaders req) {
        if (role.equals("student")) {
            this.statRepository.save(stat);
        } else {
            this.statRepository.updateStats(stat.getDemoStartTime(), stat.getDemoEndTime(), stat.getWaitingTime(), stat.getDemo().getdemoId(), stat.getDate(), new Date());
        }
        return this.statRepository.findAllByDemo(stat.getDemo());
    }

//    @PostMapping("/stats/")
//    public List<Statistic> updateStat(@RequestBody Statistic stat, @RequestHeader HttpHeaders req) {
//        this.statRepository.updateStats(stat.getDemoStartTime(), stat.getDemoEndTime(), stat.getWaitingTime(), stat.getDemo(), stat.getDate());
//        return this.statRepository.findAllByDemo(stat.getDemo());
//    }
}
