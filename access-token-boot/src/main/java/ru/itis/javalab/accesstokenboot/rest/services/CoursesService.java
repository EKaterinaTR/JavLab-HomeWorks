package ru.itis.javalab.accesstokenboot.rest.services;


import ru.itis.javalab.accesstokenboot.rest.dto.CourseDto;
import ru.itis.javalab.accesstokenboot.rest.dto.TeacherDto;
import ru.itis.javalab.accesstokenboot.rest.models.Course;

import java.util.List;

/**
 * 24.03.2021
 * 04. REST API
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface CoursesService {
    List<Course> getAllCourses();

    Course addCourse(CourseDto course);

    Course addTeacherIntoCourse(Long courseId, TeacherDto teacher);
}
