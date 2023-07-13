package com.example.springdatajpa.repository;

import com.example.springdatajpa.entity.Course;
import com.example.springdatajpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher(){

        Course courseAutomata = Course.builder()
                .title("Automata")
                .credit(5)
                .build();

        Course courseIntoToSWE = Course.builder()
                .title("courseIntoToSWE")
                .credit(7)
                .build();

        Teacher teacher = Teacher.builder()
                .firstName("Teacher1")
                .lastName("Teacher1Last")
//                .courses(List.of(courseAutomata,courseIntoToSWE))
                .build();
        teacherRepository.save(teacher);
    }


}