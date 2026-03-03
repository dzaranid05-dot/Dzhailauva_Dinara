package com.example.demo.service;

import com.example.demo.entity.Lesson;
import com.example.demo.entity.Student;
import com.example.demo.exception.LessonNotFoundException;
import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.repository.LessonRepository;
import com.example.demo.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private LessonRepository lessonRepo;

    @Transactional
    public Lesson addLesson(Long studentId, Lesson lesson) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));
        lesson.setStudent(student);
        return lessonRepo.save(lesson);
    }

    @Transactional
    public List<Lesson> getLessons(Long studentId) {
        if (!studentRepo.existsById(studentId)) {
            throw new StudentNotFoundException(studentId);
        }
        return lessonRepo.findByStudentId(studentId);
    }

    @Transactional
    public void deleteLesson(Long lessonId) {
        if (!lessonRepo.existsById(lessonId)) {
            throw new LessonNotFoundException(lessonId);
        }
        lessonRepo.deleteById(lessonId);
    }
}