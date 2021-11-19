package com.example.labtooljava.Grade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer> {

    List<Grade> findAllByDemo_DemoId(int demoId);

    Grade findByDemo_Person_DsUsernameAndDemo_Lab_LabId(String username, int labId);

}
