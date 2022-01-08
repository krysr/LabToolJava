package com.example.labtooljava.Person;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    Person findByDsUsername(String dsUsername);
    Person findByEmail(String email);
}
