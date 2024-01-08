package com.example.Student.controller;

import com.example.Student.entities.FullStudentResponse;
import com.example.Student.entities.Student;
import com.example.Student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void save(@RequestBody Student student){
        studentService.saveStudent(student);
    }

    @GetMapping
    public ResponseEntity<List<Student>> findAll(){
        return ResponseEntity.ok(studentService.findStudents());

    }

    @GetMapping("/with-students/{course-id}")
    public ResponseEntity<FullStudentResponse> findAllCourses(@PathVariable("course-id") Integer courseId){
        return ResponseEntity.ok(studentService.findSchoolsWithStudnts(courseId));

    }



}
