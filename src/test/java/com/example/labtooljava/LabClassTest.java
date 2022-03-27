package com.example.labtooljava;

import com.example.labtooljava.LabClass.LabClass;
import com.example.labtooljava.LabClass.LabClassController;
import com.example.labtooljava.LabClass.LabClassRepository;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LabClassTest {

    @Autowired
    MockMvc mvc;

    @Mock
    LabClassRepository labClassRepository;

    @InjectMocks
    LabClassController labClassController;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(labClassController).build();
    }

    @Test
    public void getCount() {

        when(labClassRepository.countAllByClassId(anyString())).thenReturn(1);

        int count = labClassRepository.countAllByClassId("1");
        assertEquals(1, count);
    }

    @Test
    public void addClass() throws Exception {
        LabClass class1 = new LabClass("CS407", "test class");
        ObjectMapper om = new ObjectMapper();

        when(labClassRepository.countAllByClassId(anyString())).thenReturn(0);

        om.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = om.writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(class1);
        mvc.perform(post("/class/add")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(json))
                .andExpect(status().isOk());
    }
}
