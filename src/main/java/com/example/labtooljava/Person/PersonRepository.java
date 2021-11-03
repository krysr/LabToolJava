package com.example.labtooljava.Person;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    Person findByLastName(String lastName);

    Person findByDsUsername(String dsUsername);

    Person findByEmail(String email);

    List<Person> findAllByRole(String role);

}
