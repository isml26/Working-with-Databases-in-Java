package com.example.springdatajpa.repository;

import com.example.springdatajpa.entity.Guardian;
import com.example.springdatajpa.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
//@DataJpaTest // use if you want to flush the data
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent() {
        Student student = Student.builder()
                .emailId("ismail@gmail.com")
                .firstName("ismail")
                .lastName("Güven")
//                .guardianName("guardian1")
//                .guardianEmail("guardian1@gmail.com")
//                .guardianMobile("9999999999")
                .build();

        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian(){
        Guardian guardian = new Guardian("wicks_guardian","wicks_guardian@gmail.com","1234");
        Student student = Student.builder()
                .emailId("john@gmail.com")
                .firstName("john")
                .lastName("wick")
                .guardian(guardian)
                .build();
        studentRepository.save(student);
    }

    @Test
    public void printAllStudents(){
        List<Student> studentList = studentRepository.findAll();
        System.out.println("studentList = " + studentList);
    }

    @Test
    public void printStudentByFirstName(){
        List<Student> studentList = studentRepository.findByFirstName("ismail");
        System.out.println("studentList = " + studentList);
    }
    @Test
    public void printStudentByFirstNameContaining(){
        List<Student> studentList = studentRepository.findByFirstNameContaining("sma");
        System.out.println("studentList = " + studentList);
    }

    @Test
    public void printStudentBasedOnGuardianName(){
        List<Student> students =
                studentRepository.findByGuardianName("guardian1");
        System.out.println("students = " + students);
    }

    @Test
    public void printStudentByFirstNameAndLastName(){
        Student student = studentRepository.findByFirstNameAndLastName("ismail","Güven");
        System.out.println("student = " + student);
    }

    @Test
    public void printStudentByEmailAddress(){
        Student student = studentRepository.getStudentByEmail("ismail@gmail.com");
        System.out.println("student = " + student);
    }
    @Test
    public void printStudentByEmailAddressNative(){
        Student student = studentRepository.getStudentByEmailNative("ismail@gmail.com");
        System.out.println("student = " + student);
    }

    @Test
    public void printStudentByEmailAddressNativeWithParam(){
        Student student = studentRepository.getStudentByEmailNative("ismail@gmail.com");
        System.out.println("student = " + student);
    }

}