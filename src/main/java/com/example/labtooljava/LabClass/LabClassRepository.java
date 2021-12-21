package com.example.labtooljava.LabClass;

import com.example.labtooljava.Lab.Lab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LabClassRepository extends JpaRepository<LabClass, Integer> {

    LabClass findByClassId(String classId);

    Integer countAllByClassId(String id);
}
