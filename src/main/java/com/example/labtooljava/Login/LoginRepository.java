package com.example.labtooljava.Login;

import com.example.labtooljava.Person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoginRepository extends JpaRepository<Login, Integer> {

    Login findByDsUsername(String username);

    //Login getLoginByDsUsername(Login login);

//    @Query("SELECT l.password FROM Login l WHERE l.dsUsername = :username")
//    String findPasswordByDsUsername(@Param("ds_username") String ds_username);
}
