package com.example.Course.Client;

import com.example.Course.entities.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "student-service", url = "${application.config.students-url}")
public interface StudentClient {
    @GetMapping("/course/{course-id}")
    List<Student> findAllStudentsByCourse(@PathVariable("course-id") Integer schoolId);
}
