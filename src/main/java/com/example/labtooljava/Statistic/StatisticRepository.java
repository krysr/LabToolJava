package com.example.labtooljava.Statistic;

import com.example.labtooljava.Demo.Demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface StatisticRepository extends JpaRepository<Statistic, Integer> {

    List<Statistic> findAllByDemo(Demo demo);

    @Modifying
    @Transactional
    @Query(value = "update Statistic st set st.demoStartTime = ?1, st.demoEndTime = ?2 where st.demo.demoId = ?3 and st.date >= ?4 and st.date <= ?5")
    void updateStats(Date startTime, Date endTime, int demoId, Date date, Date today);
}
