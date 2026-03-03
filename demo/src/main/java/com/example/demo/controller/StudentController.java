package com.example.demo.controller;

import com.example.demo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.Student;
import java.util.List;

@RestController
@RequestMapping("/students")

public class StudentController {

    @Autowired
    private StudentService service;

    @PostMapping
    public Student createStudent(@RequestBody @Valid Student student){
        return service.saveStudent(student);
    }

    @GetMapping
    public List<Student> getAllStudents(){
        return service.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id){
        return service.getStudent(id);
    }


    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id,@RequestBody @Valid Student student){
        return service.updateStudent(id, student);
    }


    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        service.deleteStudent(id);

    }


}
