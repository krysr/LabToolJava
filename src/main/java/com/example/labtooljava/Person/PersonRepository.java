package com.example.labtooljava.Person;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    Person findByLastName(String lastName);

    Person findByDsUsername(String dsUsername);

}
