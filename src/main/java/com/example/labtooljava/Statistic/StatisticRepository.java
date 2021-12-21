package com.example.labtooljava.Statistic;

import com.example.labtooljava.Demo.Demo;
import com.example.labtooljava.Lab.Lab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface StatisticRepository extends JpaRepository<Statistic, Integer> {

    List<Statistic> findAllByDemo(Demo demo);

//    List<Statistic> findAll();

    @Modifying
    @Transactional
    @Query(value = "update Statistic st set st.demoStartTime = ?1, st.demoEndTime = ?2, st.waitingTime = ?3 where st.demo.demoId = ?4 and st.date >= ?5 and st.date <= ?6")
    void updateStats(Date startTime, Date endTime, long waitingTime, int demoId, Date date, Date today);
}
