package com.example.labtooljava.LabClass;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LabClassRepository extends JpaRepository<LabClass, Integer> {

    Integer countAllByClassId(String id);
}
