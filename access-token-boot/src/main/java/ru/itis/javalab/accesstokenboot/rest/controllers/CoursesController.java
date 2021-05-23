package ru.itis.javalab.accesstokenboot.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.itis.javalab.accesstokenboot.rest.dto.CourseDto;
import ru.itis.javalab.accesstokenboot.rest.dto.TeacherDto;
import ru.itis.javalab.accesstokenboot.rest.models.Course;
import ru.itis.javalab.accesstokenboot.rest.services.CoursesService;


import java.util.List;


@RestController
public class CoursesController {

    @Autowired
    private CoursesService coursesService;

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(coursesService.getAllCourses());
    }

    @PostMapping("/courses")
    public ResponseEntity<Course> addCourse(@RequestBody CourseDto course) {
        return ResponseEntity.ok(coursesService.addCourse(course));
    }

    @PostMapping("/courses/{course-id}/teachers")
    public ResponseEntity<Course> addTeacherIntoCourse(@PathVariable("course-id") Long courseId,
                                                       @RequestBody TeacherDto teacher) {
        return ResponseEntity.ok(coursesService.addTeacherIntoCourse(courseId, teacher));
    }
}
