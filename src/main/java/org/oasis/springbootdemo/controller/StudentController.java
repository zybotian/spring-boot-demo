package org.oasis.springbootdemo.controller;

import org.oasis.springbootdemo.model.Student;
import org.oasis.springbootdemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tianbo
 * @date 2019-02-22
 */
@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/get")
    public Student getById(@RequestParam("id") Integer id) {
        return studentService.getById(id);
    }

    @GetMapping("/list")
    public List<Student> list() {
        return studentService.list();
    }

    @PostMapping("/add")
    public Object add(@RequestBody Student student) {
        return studentService.save(student);
    }

    @PostMapping("/delete")
    public Object delete(@RequestParam("id") Integer id) {
        return studentService.delete(id);
    }

    @PostMapping("update")
    public Object update(@RequestParam("id") Integer id, @RequestParam("name") String name) {
        return studentService.update(id, name);
    }

    @GetMapping("/selectMap")
    public Object selectIdName() {
        return studentService.selectIdName();
    }
}
