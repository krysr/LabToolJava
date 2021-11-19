package com.example.labtooljava.Lab;

import com.example.labtooljava.Lab.Lab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface LabRepository extends JpaRepository<Lab, Integer> {

//    List<Lab> findAllByLabClass(String labClass);
//
//    List<Lab> findallByLabId(int labId);

    Lab findByLabId(int labId);

//    Lab findByLabDayAndAndStartTimeAndLabClass_ClassId(String day, int startTime, String classId);

    Lab findByLabClass_ClassIdAndLabDayAndStartTime(String classId, String day, int startTime);
    //List<Lab> findAllByP


}
