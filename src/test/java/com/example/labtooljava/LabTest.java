package com.example.labtooljava;

import com.example.labtooljava.Demo.Demo;
import com.example.labtooljava.Demo.DemoRepository;
import com.example.labtooljava.Lab.Lab;
import com.example.labtooljava.Lab.LabController;
import com.example.labtooljava.Lab.LabRepository;
import com.example.labtooljava.LabClass.LabClass;
import com.example.labtooljava.Person.Person;
import com.example.labtooljava.Person.PersonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
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
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LabTest {
    @Autowired
    MockMvc mvc;

    @Mock
    LabRepository labRepository;

    @Mock
    DemoRepository demoRepository;

    @Mock
    PersonRepository personRepository;

    @InjectMocks
    LabController labController;

    @Rule //initMocks
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(labController).build();
    }

    LabClass class1 = new LabClass("CS407", "test class");
    Lab lab1 = new Lab(1, "Monday", 1200, 1400, class1, "LT1011");
    Person p1 = new Person("abc123", "John", "Doe", "john.doe@email.com", "student", "12345");

    Demo demo1 = new Demo(lab1, p1, "no", 0, false, 25);
    Demo demo2 = new Demo(lab1, p1, "no", 0, false, 25);


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

    @Test
    public void addStudentDemoPostReq() throws Exception {
        List<Demo> demoTest = new ArrayList<>();
        demoTest.add(demo1);
        demoTest.add(demo2);
        when(demoRepository.getDemo(anyInt(), anyString())).thenReturn("no");
        when(demoRepository.getMaxPos()).thenReturn(3);

       // String[] emails = {"test1@emaio.com", "test2@email.com"};
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(lab1);

        when(demoRepository.findAllByLab_LabIdAndDemoInOrderByPositionAsc(anyInt(), anyList())).thenReturn(demoTest);
        mvc.perform(post("/lab/demonstrate/{username}", "testID")
                .contentType(APPLICATION_JSON_UTF8)
                        .content(String.valueOf(requestJson)))
                .andExpect(status().isOk());

    }

    @Test
    public void getQueueReq() throws Exception {
        List<Demo> demoTest = new ArrayList<>();
        demoTest.add(demo1);
        demoTest.add(demo2);
        when(demoRepository.findAllByLab_LabIdAndDemoInOrderByPositionAsc(anyInt(), anyList())).thenReturn(demoTest);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(lab1);
        mvc.perform(post("/lab/demonstrate/")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(String.valueOf(requestJson)))
                .andExpect(status().isOk());
    }

    @Test
    public void RemoveStudentPostReq() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(demo1);
        mvc.perform(post("/lab/demonstrate/end/{username}", "testID")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(String.valueOf(requestJson)))
                .andExpect(status().isOk());
    }

    @Test
    public void addStudentLabTest() throws Exception {
        String emailTest[] = {"email1@test"};
        when(personRepository.findByEmail(anyString())).thenReturn(p1);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(emailTest);
        mvc.perform(post("/lab/list/{labId}", 2)
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(String.valueOf(requestJson)))
                .andExpect(status().isOk());
    }

    @Test
    public void assignToLabTest() throws Exception {
        when(personRepository.findByEmail(anyString())).thenReturn(p1);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(lab1);
        mvc.perform(post("/lab/assign/{email}/{type}", "email@test", "demonstrator")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(String.valueOf(requestJson)))
                .andExpect(status().isOk());
    }

    @Test
    public void addNewLabTest() throws Exception {
        when(personRepository.findByEmail(anyString())).thenReturn(p1);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(lab1);
        mvc.perform(post("/lab/add/{username}", "testID")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(String.valueOf(requestJson)))
                .andExpect(status().isOk());
    }


}
