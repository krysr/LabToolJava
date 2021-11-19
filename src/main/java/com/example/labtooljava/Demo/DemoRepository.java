package com.example.labtooljava.Demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DemoRepository extends JpaRepository<Demo, Integer> {
     //List<Lab> findAllBy
        //List<Demo> findAll();

    List<Demo> findAll();

    List<Demo> findAllByPerson_DsUsername(String username);

    List<Demo> findAllByInstructorAndPerson_DsUsername(boolean instructor, String username);

//    List<Demo> findAllByDemo(boolean demo);

    List<Demo> findAllByLab_LabIdAndDemoOrderByPositionAsc(int id, String demo);

    List<Demo> findAllByLab_LabIdAndDemoAndDemoOrderByPositionAsc(int id, String demo, String demo1);

    Demo findDemoByDemoId(int demoId);

//    @Modifying
//    @Transactional
//    @Query(value = "update Demo pl set pl.demo = true where pl.lab.labId =: lab_id_fk and pl.person.dsUsername =: person_id_fk")
//    void setDemo(@Param("lab_id_fk") int lab_id_fk, @Param("person_id_fk") String person_id_fk);

//    @Query("from Demo lp inner join fetch lp.lab_id_fk where lp.person_id_fk = :person_id_fk")
//    List<Demo> findByReviewId(@Param("person_id_fk") String person_id_fk);

    @Query(value = "select pl.demo from Demo pl where pl.lab.labId = ?1 and pl.person.dsUsername = ?2")
    boolean getDemo(int lab_id_fk, String person_id_fk);

    @Modifying
    @Transactional
    @Query(value = "update Demo pl set pl.demo = ?1 where pl.lab.labId = ?2 and pl.person.dsUsername = ?3")
    void setDemo(String demo, int lab_id_fk, String person_id_fk);

    @Query(value = "select max(pl.position) from Demo pl")
    int getMaxPos();


    @Modifying
    @Transactional
    @Query(value = "update Demo pl set pl.position = ?3 where pl.lab.labId = ?1 and pl.person.dsUsername = ?2")
    void setPos(int lab_id_fk, String person_id_fk, int pos);

}
