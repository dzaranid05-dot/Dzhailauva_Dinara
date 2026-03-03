package com.example.demo.controller;

import com.example.demo.entity.Lesson;
import com.example.demo.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students/{studentId}/lessons")

public class LessonController {

    @Autowired
    private LessonService lessonService;

    @PostMapping
    public Lesson addLesson(@PathVariable Long studentId, @RequestBody Lesson lesson) {
        return lessonService.addLesson(studentId, lesson);
    }

    @GetMapping
    public List<Lesson> getLessons(@PathVariable Long studentId) {
        return lessonService.getLessons(studentId);
    }

    @DeleteMapping("/{lessonId}")
    public void deleteLesson(@PathVariable Long lessonId) {
        lessonService.deleteLesson(lessonId);
    }
}