package com.example.Course.service;

import com.example.Course.Client.StudentClient;
import com.example.Course.entities.Course;
import com.example.Course.entities.FullCourseResponse;
import com.example.Course.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final StudentClient studentClient;

    public void saveCourse(Course course){
        courseRepository.save(course);
    }

    public List<Course> findCourses(){
        return courseRepository.findAll();
    }

    public FullCourseResponse findCoursesWithStudnts(Integer courseId) {
        var course = courseRepository.findById(courseId)
                .orElse(
                        Course.builder()
                         .name("Noy_Found")
                         .email("Not-Found")
                         .build()
                 );
        var students = studentClient.findAllStudentsByCourse(courseId); // find all the students from the student micro-services
       return FullCourseResponse .builder()
               .name(course.getName())
               .email(course.getEmail())
               .students(students)
               .build();
    }


}
