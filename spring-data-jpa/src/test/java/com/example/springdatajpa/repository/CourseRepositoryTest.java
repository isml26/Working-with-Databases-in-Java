package com.example.springdatajpa.repository;

import com.example.springdatajpa.entity.Course;
import com.example.springdatajpa.entity.Student;
import com.example.springdatajpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printCourses(){
        List<Course> courses = courseRepository.findAll();
        System.out.println("courses = " + courses);
    }

    @Test
    public void saveCourseWithTeacher(){
        Teacher teacher = Teacher.builder()
                .firstName("Jack")
                .lastName("Wrestler")
                .build();

        Course course = Course.builder()
                .title("Python")
                .credit(5)
                .teacher(teacher)
                .build();
        courseRepository.save(course);
    }

    @Test
    public void findAllPagination(){
        PageRequest firstPageWithThreeRecords = PageRequest.of(0,2);

        List<Course> courses = courseRepository.findAll(firstPageWithThreeRecords).getContent();
        long totalPages = courseRepository.findAll(firstPageWithThreeRecords).getTotalPages();
        long totalElements = courseRepository.findAll(firstPageWithThreeRecords).getTotalElements();

        System.out.println("courses = " + courses);
        System.out.println("totalPages = " + totalPages);
        System.out.println("totalElements = " + totalElements);

    }


    @Test
    public void findAllSorting(){
        PageRequest sortByTitle = PageRequest.of(0,2,Sort.by("title").descending());
        PageRequest sortByCreditDesc = PageRequest.of(0,2,Sort.by("credit").descending());
        PageRequest sortByTitleAndCreditDesc = PageRequest.of(0,2,Sort.by("credit").descending()
                .and(Sort.by("title"))
        );

        List<Course> coursesSortByTitle = courseRepository.findAll(sortByTitle).getContent();
        List<Course> coursesSortByCreditDesc = courseRepository.findAll(sortByCreditDesc).getContent();
        List<Course> coursesSortByTitleAndCreditDesc = courseRepository.findAll(sortByTitleAndCreditDesc).getContent();

        System.out.println("coursesSortByTitle = " + coursesSortByTitle);
        System.out.println("coursesSortByCreditDesc = " + coursesSortByCreditDesc);
        System.out.println("coursesSortByTitleAndCreditDesc = " + coursesSortByTitleAndCreditDesc);

    }

    @Test
    public void printfindByTitleContaining() {
        PageRequest firstPageTenRecords =
                PageRequest.of(0,6);

        List<Course> courses =
                courseRepository.findByTitleContaining(
                        "D",
                        firstPageTenRecords).getContent();

        System.out.println("courses = " + courses);
    }

    @Test
    public void saveCourseWithStudentAndTeacher(){

        Teacher teacher = Teacher.builder()
                .firstName("Arthur")
                .lastName("Morgan")
                .build();

        Student student = Student.builder()
                .firstName("John")
                .lastName("Wick")
                .emailId("save_my_dog@hotmail.com")
                .build();

        Course course = Course.builder()
                .title("ML")
                .credit(5)
                .teacher(teacher)
                .build();
        course.addStudents(student);

        courseRepository.save(course);
    }

}