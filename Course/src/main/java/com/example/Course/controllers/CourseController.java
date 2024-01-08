package com.example.Course.controllers;

import com.example.Course.entities.Course;
import com.example.Course.entities.FullCourseResponse;
import com.example.Course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void save(@RequestBody Course course){
        courseService.saveCourse(course);
    }

    @GetMapping
    public ResponseEntity<List<Course>> findAll(){
        return ResponseEntity.ok(courseService.findCourses());

    }

    @GetMapping("/with-students/{course-id}")
    public ResponseEntity<FullCourseResponse>findAllCourse(@PathVariable("course-id") Integer courseId){
        return ResponseEntity.ok(courseService.findCoursesWithStudnts(courseId));

    }


}
