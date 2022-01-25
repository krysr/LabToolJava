package com.example.labtooljava;

import com.example.labtooljava.Grade.Grade;
import com.example.labtooljava.Person.Person;
import com.example.labtooljava.Person.PersonController;
import com.example.labtooljava.Person.PersonRepository;
import com.example.labtooljava.Person.PersonService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PersonTest {


    @Autowired
    MockMvc mvc;

    @Mock
    PersonRepository personRepository;

    @Mock
    PersonService personService;

    @InjectMocks
    PersonController personController;

    @Rule //initMocks
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(personController).build();
    }

    Person p1 = new Person("abc123", "John", "Doe", "john.doe@email.com", "student", "12345");
    Person p2 = new Person("qwe567", "Rosie", "Jane", "rosie.jane@email.com", "demonstrator", "67890");
    Person p3 = new Person("jhg019", "George", "McCallister", "george.mccallister@email.com", "lecturer", "abcde");
    Person p4 = new Person("vbg45963", "George", "McCallister", "george.mccallister@email.com", "lecturer", "lecturer1");


    @Test
    public void getAllUsers() {
        List<Person> personList = new ArrayList<>();

        personList.add(p1);
        personList.add(p2);
        personList.add(p3);

        when(personRepository.findAll()).thenReturn(personList);

        List<Person> userList = personRepository.findAll();

        assertEquals(3, userList.size());
        verify(personRepository, times(1)).findAll();
    }

    @Test
    public void getUserByEmail() {
        when(personRepository.findByEmail("rosie.jane@email.com")).thenReturn(p2);
        Person testUser = personRepository.findByEmail("rosie.jane@email.com");
        assertEquals(p2, testUser);
        verify(personRepository, times(1)).findByEmail("rosie.jane@email.com");
    }

    @Test
    public void getUserByDsUsername() {
        when(personRepository.findByDsUsername("abc123")).thenReturn(p1);
        Person testUser = personRepository.findByDsUsername("abc123");
        assertEquals(p1, testUser);
        verify(personRepository, times(1)).findByDsUsername("abc123");
    }

    @Test
    public void loginTest() throws Exception {
        String auth = "Basic dmJnNDU5NjM6bGVjdHVyZXIx";
        when(personService.doPasswordsMatch(anyString(), anyString())).thenReturn(Boolean.TRUE);
        when(personRepository.findByDsUsername(anyString())).thenReturn(p4);

        mvc.perform(post("/")
                        .header("Authorization", auth))
                .andExpect(status().isOk());
    }
}
