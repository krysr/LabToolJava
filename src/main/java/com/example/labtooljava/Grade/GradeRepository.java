package com.example.labtooljava.Grade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer> {

    Grade findByAssessmentName(String name);

    @Query(value = "insert into Grade g  (g.demo_id, g.assessment_name, g.grade, g.grade_comment) values(?1, ?2, ?2, ?4)")
    void setGrade(int demo_id, String assessment_name, float grade, String comment);


}
