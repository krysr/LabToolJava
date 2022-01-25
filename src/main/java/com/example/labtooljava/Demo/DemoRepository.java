package com.example.labtooljava.Demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DemoRepository extends JpaRepository<Demo, Integer> {

//    List<Demo> findAll();
    List<Demo> findAllByPerson_EmailAndLab_LabId(String email, int id);
    List<Demo> findAllByInstructorAndPerson_DsUsername(boolean instructor, String username);
    List<Demo> findAllByLab_LabIdAndDemoInOrderByPositionAsc(int id, List<String> demo);
    Demo findDemoByPerson_DsUsernameAndLab_LabId(String username, int labId);

    @Query(value = "select pl.demo from Demo pl where pl.lab.labId = ?1 and pl.person.dsUsername = ?2")
    String getDemo(int lab_id_fk, String person_id_fk);

    @Modifying
    @Transactional
    @Query(value = "update Demo pl set pl.demo = ?1 where pl.lab.labId = ?2 and pl.person.dsUsername = ?3")
    void setDemo(String demo, int lab_id_fk, String person_id_fk);

    @Query(value = "select max(pl.position) from Demo pl")
    int getMaxPos();

    @Query(value = "select max(pl.seat) from Demo pl where pl.lab.labId = ?1")
    int getMaxSeat(int lab_id);

    @Modifying
    @Transactional
    @Query(value = "update Demo pl set pl.position = ?3 where pl.lab.labId = ?1 and pl.person.dsUsername = ?2")
    void setPos(int lab_id_fk, String person_id_fk, int pos);

}
