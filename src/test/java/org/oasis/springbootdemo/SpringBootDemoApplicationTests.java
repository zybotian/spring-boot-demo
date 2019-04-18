package org.oasis.springbootdemo;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.*;
import org.junit.runner.RunWith;
import org.oasis.springbootdemo.dao.StudentMapper;
import org.oasis.springbootdemo.model.StudentVO;
import org.oasis.springbootdemo.model.generated.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDemoApplicationTests {

    @Autowired
    StudentMapper studentMapper;

    @Before
    public void setUp() throws Exception {
        int insert = studentMapper.insert(generate());
        Assert.assertEquals(1, insert);
        insert = studentMapper.insert(generate());
        Assert.assertEquals(1, insert);
        insert = studentMapper.insert(generate());
        Assert.assertEquals(1, insert);
        insert = studentMapper.insert(generate());
        Assert.assertEquals(1, insert);
    }

    @After
    public void tearDown() throws Exception {
        int deleted = studentMapper.deleteByExample(null);
        Assert.assertTrue(deleted > 0);
    }

    @Test
    public void test() {
        List<Student> students = studentMapper.selectByExample(null);
        Assert.assertNotNull(students);
    }


    @Test
    public void testIdName() throws Exception {
        List<StudentVO> studentVOS = studentMapper.selectIdName();
        Assert.assertTrue(studentVOS.size() > 0);
    }

    @Test
    public void testIdNameMap() throws Exception {
        List<Map<Integer, String>> maps = studentMapper.selectIdNameMap();
        Assert.assertTrue(maps.size() > 0);
    }

    private Student generate() {
        Student student = new Student();
        student.setName("name_" + RandomStringUtils.randomAlphabetic(6));
        student.setGrade(1);
        student.setMajor("grade_" + RandomStringUtils.randomAlphanumeric(6));
        student.setSno("sno_" + RandomStringUtils.randomAlphanumeric(6));
        student.setCreateTime(System.currentTimeMillis());
        student.setUpdateTime(System.currentTimeMillis());
        return student;
    }

}
