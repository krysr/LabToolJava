package com.example.labtooljava.Statistic;

import com.example.labtooljava.Demo.Demo;
import com.example.labtooljava.Lab.Lab;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatisticRepository extends JpaRepository<Statistic, Integer> {

    List<Statistic> findAllByDemo(Demo demo);
}
