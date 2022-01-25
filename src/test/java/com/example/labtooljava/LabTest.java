package com.example.labtooljava;

import com.example.labtooljava.Demo.Demo;
import com.example.labtooljava.Demo.DemoRepository;
import com.example.labtooljava.Grade.GradeController;
import com.example.labtooljava.Grade.GradeRepository;
import com.example.labtooljava.Lab.Lab;
import com.example.labtooljava.Lab.LabController;
import com.example.labtooljava.Lab.LabRepository;
import com.example.labtooljava.LabClass.LabClass;
import com.example.labtooljava.Person.Person;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LabTest {
    @Autowired
    MockMvc mvc;

    @Mock
    LabRepository labRepository;

    @Mock
    DemoRepository demoRepository;

    @InjectMocks
    LabController labController;

    @Rule //initMocks
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(labController).build();
    }

    @Test
    public void getLabByLabId() {
        LabClass class1 = new LabClass("CS407", "test class");
        Lab lab1 = new Lab(1, "Monday", 1200, 1400, class1, "LT1011");
        when(labRepository.findByLabId(anyInt())).thenReturn(lab1);
        Lab labTest = labRepository.findByLabId(1);
        assertEquals(lab1, labTest);
    }

    @Test
    public void getLabByClassIdAndLabDayAndStartTime() {
        LabClass class1 = new LabClass("CS407", "test class");
        Lab lab1 = new Lab(1, "Monday", 1200, 1400, class1, "LT1011");
        when(labRepository.findByLabClass_ClassIdAndLabDayAndStartTime(anyString(), anyString(),anyInt())).thenReturn(lab1);
        Lab labTest = labRepository.findByLabClass_ClassIdAndLabDayAndStartTime("CS407", "Monday", 1200);
        assertEquals(lab1, labTest);
    }

    @Test
    public void getLabGetReq() throws Exception {
        List<Demo> demoTest = new ArrayList<>();
        LabClass class1 = new LabClass("CS407", "test class");
        Lab lab1 = new Lab(1, "Monday", 1200, 1400, class1, "LT1011");
        Person p1 = new Person("abc123", "John", "Doe", "john.doe@email.com", "student", "12345");

        Demo demo1 = new Demo(lab1, p1, "no", 0, false, 25);
        Demo demo2 = new Demo(lab1, p1, "no", 0, false, 25);

        demoTest.add(demo1);
        demoTest.add(demo2);
        when(demoRepository.findAllByInstructorAndPerson_DsUsername(anyBoolean(), anyString())).thenReturn(demoTest);
        when(labRepository.findByLabId(anyInt())).thenReturn(lab1);
        mvc.perform(get("/lab/{username}", "abc123")
                        .param("role", "student"))
                .andExpect(status().isOk());
    }

}
