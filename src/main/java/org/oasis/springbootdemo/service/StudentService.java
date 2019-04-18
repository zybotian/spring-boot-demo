package org.oasis.springbootdemo.service;

import org.oasis.springbootdemo.dao.StudentDao;
import org.oasis.springbootdemo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author tianbo
 * @date 2019-02-22
 */
@Service
public class StudentService {
    @Autowired
    StudentDao studentDao;


    public Student getById(Integer id) {
        return studentDao.findById(id);
    }

    public List<Student> list() {
        return studentDao.list();
    }

    public Object save(Student student) {
        if (studentDao.insert(student) > 0) {
            return student;
        }
        return "add failed";
    }

    public Object delete(Integer id) {
        if (studentDao.delete(id) > 0) {
            return id;
        }
        return "delete failed";
    }

    public Object update(Integer id, String name) {
        if (studentDao.update(id, name) > 0) {
            return id;
        }
        return "update failed";
    }

    public List<Map<Integer, String>> selectIdName() {
        return studentDao.selectIdName();
    }
}
