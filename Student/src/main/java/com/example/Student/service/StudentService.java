package com.example.Student.service;

import com.example.Student.client.CourseClient;
import com.example.Student.entities.FullStudentResponse;
import com.example.Student.entities.Student;
import com.example.Student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final CourseClient courseClient;

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    public List<Student> findStudents() {
        return studentRepository.findAll();
    }

    public List<Student> findAllStudentsBySchool(Integer courseId) {
        return studentRepository.findAllStudentsById(courseId);
    }

    public FullStudentResponse findCoursesWithStudnts(Integer studentId) {
        var student = studentRepository.findById(studentId)
                .orElse(
                        Student.builder()
                                .firstName("Noy_Found")
                                .lastName("Not-Found")
                                .email("Not-Found")
                                .build()
                );
        var courses = courseClient.findAllStudentsByCourse(studentId); // find all the courses from the student micro-services
        return FullStudentResponse.builder()
                .name(student.getFirstName())
                .email(student.getEmail())
                .courses(courses)
                .build();

    }
}
