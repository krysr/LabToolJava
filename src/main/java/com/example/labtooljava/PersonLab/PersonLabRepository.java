package com.example.labtooljava.PersonLab;

import com.example.labtooljava.Lab.Lab;
import com.example.labtooljava.Person.Person;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PersonLabRepository extends JpaRepository<PersonLab, Integer> {
     //List<Lab> findAllBy
        //List<PersonLab> findAll();

//    List<PersonLab> findAll();

    List<PersonLab> findAllByPerson_DsUsername(String username);

//    List<PersonLab> findAllByDemo(boolean demo);

    List<PersonLab> findAllByLab_LabIdAndDemoOrderByPositionAsc(int id, boolean demo);

//    @Modifying
//    @Transactional
//    @Query(value = "update PersonLab pl set pl.demo = true where pl.lab.labId =: lab_id_fk and pl.person.dsUsername =: person_id_fk")
//    void setDemo(@Param("lab_id_fk") int lab_id_fk, @Param("person_id_fk") String person_id_fk);

//    @Query("from PersonLab lp inner join fetch lp.lab_id_fk where lp.person_id_fk = :person_id_fk")
//    List<PersonLab> findByReviewId(@Param("person_id_fk") String person_id_fk);

    @Query(value = "select pl.demo from PersonLab pl where pl.lab.labId = ?1 and pl.person.dsUsername = ?2")
    boolean getDemo(int lab_id_fk, String person_id_fk);

    @Modifying
    @Transactional
    @Query(value = "update PersonLab pl set pl.demo = true where pl.lab.labId = ?1 and pl.person.dsUsername = ?2")
    void setDemo(int lab_id_fk, String person_id_fk);

    @Query(value = "select max(pl.position) from PersonLab pl")
    int getMaxPos();


    @Modifying
    @Transactional
    @Query(value = "update PersonLab pl set pl.position = ?3 where pl.lab.labId = ?1 and pl.person.dsUsername = ?2")
    void setPos(int lab_id_fk, String person_id_fk, int pos);

}
