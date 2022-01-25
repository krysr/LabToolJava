package com.example.labtooljava;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.example.labtooljava.Demo.Demo;
import com.example.labtooljava.Demo.DemoRepository;
import com.example.labtooljava.Lab.Lab;
import com.example.labtooljava.LabClass.LabClass;
import com.example.labtooljava.Person.Person;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class DemoTest {

    @Mock
    DemoRepository demoRepository;

    //    @Before
//    public void init() {
//        MockitoAnnotations.initMocks(this);
//    }
    @Rule //initMocks
    public MockitoRule rule = MockitoJUnit.rule();

    LabClass class1 = new LabClass("CS407", "test class");
    Lab lab1 = new Lab(1, "Monday", 1200, 1400, class1, "LT1011");
    Person p1 = new Person("abc123", "John", "Doe", "john.doe@email.com", "student", "12345");
    Demo demo1 = new Demo(lab1, p1, "no", 0, false, 25);
    Demo demo2 = new Demo(lab1, p1, "no", 0, false, 25);

    @Test
    public void getAllByEmailAndLabId() {
        List<Demo> demoList = new ArrayList<>();
        demoList.add(demo1);
        demoList.add(demo2);

        when(demoRepository.findAllByPerson_EmailAndLab_LabId(anyString(), anyInt())).thenReturn(demoList);

        List<Demo> testDemos = demoRepository.findAllByPerson_EmailAndLab_LabId("john.doe@email.com", 1);
        assertEquals(testDemos, demoList);
    }

    @Test
    public void getAllByLabIdAndDemo() {
        List<Demo> demoList = new ArrayList<>();
        demoList.add(demo1);
        demoList.add(demo2);

        when(demoRepository.findAllByLab_LabIdAndDemoInOrderByPositionAsc(anyInt(), anyList())).thenReturn(demoList);

        List<Demo> testDemos = demoRepository.findAllByLab_LabIdAndDemoInOrderByPositionAsc(anyInt() ,anyList());
        assertEquals(testDemos, demoList);
    }

    @Test
    public void getAllByRoleAndDsUsername() {
        List<Demo> demoList = new ArrayList<>();
        demoList.add(demo1);
        demoList.add(demo2);

        when(demoRepository.findAllByInstructorAndPerson_DsUsername(anyBoolean(), anyString())).thenReturn(demoList);

        List<Demo> testDemos = demoRepository.findAllByInstructorAndPerson_DsUsername(true, "abc123");
        assertEquals(testDemos, demoList);
    }

    @Test
    public void getDemoByDsUsernameAndLabId() {
        when(demoRepository.findDemoByPerson_DsUsernameAndLab_LabId(anyString(), anyInt())).thenReturn(demo1);

        Demo testDemo = demoRepository.findDemoByPerson_DsUsernameAndLab_LabId(anyString(), anyInt());
        assertEquals(demo1, testDemo);
    }

    @Test
    public void getMaxDemoPosition() {

        when(demoRepository.getMaxPos()).thenReturn(50);
        int pos = demoRepository.getMaxPos();
        assertEquals(demoRepository.getMaxPos(), pos);

    }

    @Test
    public void getMaxSeat() {

        when(demoRepository.getMaxSeat(anyInt())).thenReturn(35);
        int maxSeat = demoRepository.getMaxSeat(anyInt());
        assertEquals(demoRepository.getMaxSeat(anyInt()), maxSeat);

    }
}
