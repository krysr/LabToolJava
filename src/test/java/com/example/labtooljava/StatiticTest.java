package com.example.labtooljava;

import com.example.labtooljava.Demo.Demo;
import com.example.labtooljava.Lab.Lab;
import com.example.labtooljava.LabClass.LabClass;
import com.example.labtooljava.Person.Person;
import com.example.labtooljava.Statistic.Statistic;
import com.example.labtooljava.Statistic.StatisticController;
import com.example.labtooljava.Statistic.StatisticRepository;
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
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StatiticTest {

    @Autowired
    MockMvc mvc;

    @Mock
    StatisticRepository statisticRepository;

    @InjectMocks
    StatisticController statisticController;

    @Rule //initMocks
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(statisticController).build();
    }

    LabClass class1 = new LabClass("CS407", "test class");
    Lab lab1 = new Lab(1, "Monday", 1200, 1400, class1, "LT1011");
    Person p1 = new Person("abc123", "John", "Doe", "john.doe@email.com", "student", "12345");

    Demo demo1 = new Demo(lab1, p1, "no", 0, false, 25);

    List<Statistic> userStats = new ArrayList<>();
    Statistic s1 = new Statistic(demo1, new Date(), 1000, new Date(), new Date(), new Date());
    Statistic s2 = new Statistic(demo1, new Date(), 1000, new Date(), new Date(), new Date());


    @Test
    public void getAllStatsByDemo() throws InterruptedException {
        LabClass class1 = new LabClass("CS407", "test class");
        Lab lab1 = new Lab(1, "Monday", 1200, 1400, class1, "LT1011");
        Person p1 = new Person("abc123", "John", "Doe", "john.doe@email.com", "student", "12345");

        Demo demo1 = new Demo(lab1, p1, "no", 0, false, 25);

        List<Statistic> userStats = new ArrayList<>();
        Statistic s1 = new Statistic(demo1, new Date(), 1000, new Date(), new Date(), new Date());
        Statistic s2 = new Statistic(demo1, new Date(), 1000, new Date(), new Date(), new Date());

        userStats.add(s1);
        userStats.add(s2);
        when(statisticRepository.findAllByDemo(demo1)).thenReturn(userStats);

        List<Statistic> statsTest = statisticRepository.findAllByDemo(demo1);
        assertEquals(2, statsTest.size());
        assertEquals(statsTest, userStats);
    }

    @Test
    public void saveStats() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(s1);

        mvc.perform(post("/stats/{role}", "student")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andExpect(status().isOk());
    }

    @Test
    public void getStats() throws Exception {
        mvc.perform(get("/stats/"))
                .andExpect(status().isOk());
    }

}
