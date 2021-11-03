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
    //List<Lab> findAllByP
}
