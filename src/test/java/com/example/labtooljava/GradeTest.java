package com.example.labtooljava;

import com.example.labtooljava.Demo.Demo;
import com.example.labtooljava.Demo.DemoRepository;
import com.example.labtooljava.Grade.Grade;
import com.example.labtooljava.Grade.GradeController;
import com.example.labtooljava.Grade.GradeRepository;
import com.example.labtooljava.Lab.Lab;
import com.example.labtooljava.LabClass.LabClass;
import com.example.labtooljava.Person.Person;
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
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GradeTest {

    @Autowired
    MockMvc mvc;

    @Mock
    GradeRepository gradeRepository;

    @Mock
    DemoRepository demoRepository;

    @InjectMocks
    GradeController gradeController;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(gradeController).build();
    }

    LabClass class1 = new LabClass("CS407", "test class");
    Lab lab1 = new Lab(1, "Monday", 1200, 1400, class1, "LT1011");
    Person p1 = new Person("abc123", "John", "Doe", "john.doe@email.com", "student", "12345");

    Demo demo1 = new Demo(lab1, p1, "no", 0, false, 25);
    Grade grade1 = new Grade(1, "very well", 80, new Date(), demo1);
    Grade grade2 = new Grade(2, "improvement", 90, new Date(), demo1);

    @Test
    public void createGrade() {
        Grade gradeTest = new Grade();
        gradeTest.setGrade(60);
        gradeTest.setGrade_id(1);
        gradeTest.setGradeDate(new Date());
        gradeTest.setDemo(demo1);
        gradeTest.setgradeComment("Not too bad");

        assertEquals(60, gradeTest.getGrade(), 1);
        assertEquals(1, gradeTest.getGrade_id());
        assertEquals("Not too bad", gradeTest.getgradeComment());
    }

    @Test
    public void getGradeByDemoId() {

        List<Grade> userGrades = new ArrayList<>();
        userGrades.add(grade1);
        userGrades.add(grade2);

        when(gradeRepository.save(any(Grade.class))).thenReturn(grade2);
        when(gradeRepository.save(any(Grade.class))).thenReturn(grade1);
        when(gradeRepository.findAllByDemo_DemoId(1)).thenReturn(userGrades);

        List<Grade> testGrades = gradeRepository.findAllByDemo_DemoId(1);
        assertEquals(userGrades, testGrades);
        verify(gradeRepository, times(1)).findAllByDemo_DemoId(1);
    }

    @Test
    public void getGradeGetReq() throws Exception {
        List<Grade> userGrades = new ArrayList<>();
        userGrades.add(grade1);
        userGrades.add(grade2);

        when(demoRepository.findDemoByPerson_DsUsernameAndLab_LabId(anyString(), anyInt())).thenReturn(demo1);
        when(gradeRepository.findAllByDemo_DemoId(demoRepository.findDemoByPerson_DsUsernameAndLab_LabId(anyString(), anyInt()).getdemoId())).thenReturn(userGrades);
        mvc.perform(get("/grade/student/{username}/{labid}", "abc123", 1))
                .andExpect(status().isOk());
    }

    @Test
    public void setGradePostReq() throws Exception {
        Demo demoTest = new Demo(lab1, p1, "live", 0, false, 25);

        List<Grade> gradeList = new ArrayList<>();
        Grade testGrade = new Grade(3, "improvement", 90, new Date(), demoTest);
        Grade testGrade2 = new Grade(4, "ok", 50, new Date(), demoTest);
        gradeList.add(testGrade);
        gradeList.add(testGrade2);
        when(gradeRepository.findAllByDemo_DemoId(anyInt())).thenReturn(gradeList);

        ObjectMapper om = new ObjectMapper();
        om.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = om.writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(testGrade);

        mvc.perform(post("/grade/student/")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(json))
                .andExpect(status().isOk());

        List<Grade> userGrades = gradeRepository.findAllByDemo_DemoId(anyInt());
        assertEquals(userGrades, gradeList);
    }
}
