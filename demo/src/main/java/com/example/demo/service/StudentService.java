package com.example.demo.service;

import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Student;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepo;

    @Transactional
    public Student saveStudent(Student student) {
        return studentRepo.save(student);
    }

    @Transactional
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    @Transactional
    public Student getStudent(Long id) {
        return studentRepo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id)); // ← было неправильно
    }

    @Transactional
    public Student updateStudent(Long id, Student updated) {
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id)); // ← добавил исключение
        student.setName(updated.getName());
        student.setLastname(updated.getLastname());
        student.setEmail(updated.getEmail());
        student.setAge(updated.getAge());
        return studentRepo.save(student);
    }

    @Transactional
    public void deleteStudent(Long id) {
        if (!studentRepo.existsById(id)) {
            throw new StudentNotFoundException(id);
        }
        studentRepo.deleteById(id);
    }
}