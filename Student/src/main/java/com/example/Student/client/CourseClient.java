package com.example.Student.client;


import com.example.Student.entities.Course;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "course-service", url = "${application.config.courses-url}")
public interface CourseClient {
    @GetMapping("/students/{student-id}")
    List<Course> findAllStudentsByCourse(@PathVariable("student-id") Integer courseId);
}
