package ru.itis.javalab.accesstokenboot.rest.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.accesstokenboot.rest.dto.TeacherDto;
import ru.itis.javalab.accesstokenboot.rest.services.TeachersService;


import java.util.ArrayList;
import java.util.List;

/**
 * 24.03.2021
 * 04. REST API
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@RestController
public class TeachersController {

    @Autowired
    private TeachersService teachersService;

    @GetMapping("/teachers")
    public ResponseEntity<List<TeacherDto>> getTeachers(@RequestHeader(value="A-TOKEN", required = false) String token,
                                                        @RequestHeader("R-TOKEN") String refreshToken) {
        return ResponseEntity.ok(teachersService.getAllTeachers());
    }

//    @GetMapping("/teachers")
//    public ResponseEntity<List<TeacherDto>> getTeachers(@RequestHeader("R-TOKEN") String refreshToken) {
//        return ResponseEntity.ok(teachersService.getAllTeachers());
//    }


    @ApiOperation(value = "Добавление педагога")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно добавлено", response = TeacherDto.class)})
    @PostMapping("/teachers")
    public ResponseEntity<TeacherDto> addTeacher(@RequestBody TeacherDto teacher) {
        return ResponseEntity.ok(teachersService.addTeacher(teacher));
    }

    @PutMapping("/teachers/{teacher-id}")
    public ResponseEntity<TeacherDto> updateTeacher(@PathVariable("teacher-id") Long teacherId, @RequestBody TeacherDto teacher) {
        return ResponseEntity.ok(teachersService.updateTeacher(teacherId, teacher));
    }

    @DeleteMapping("/teachers/{teacher-id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable("teacher-id") Long teacherId) {
        teachersService.deleteTeacher(teacherId);
        return ResponseEntity.ok().build();
    }
}
