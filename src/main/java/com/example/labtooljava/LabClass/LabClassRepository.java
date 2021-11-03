package com.example.labtooljava.LabClass;

import com.example.labtooljava.Lab.Lab;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabClassRepository extends JpaRepository<LabClass, Integer> {

    LabClass findByClassId(String classId);

}
