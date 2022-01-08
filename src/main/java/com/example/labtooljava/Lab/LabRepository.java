package com.example.labtooljava.Lab;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LabRepository extends JpaRepository<Lab, Integer> {

    Lab findByLabId(int labId);
    Lab findByLabClass_ClassIdAndLabDayAndStartTime(String classId, String day, int startTime);
}
