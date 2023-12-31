package com.example.springdatajpa.repository;

import com.example.springdatajpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
     List<Student> findByFirstName(String firstName);

     List<Student> findByFirstNameContaining(String name);

     List<Student> findByGuardianName(String guardianName);

     Student findByFirstNameAndLastName(String firstName, String lastName);

     // JPQL
     @Query("select s from Student s where s.emailId = ?1")
     Student getStudentByEmail(String EmailId);

     @Query(
             value = "SELECT * FROM student s WHERE email_address = ?1",
             nativeQuery = true
     )
     Student getStudentByEmailNative(String EmailId);

     @Query(
             value = "SELECT * FROM student s WHERE email_address = emailId",
             nativeQuery = true
     )
     Student getStudentByEmailNativeNamedParam(@Param("emailId") String EmailId);

     @Modifying
     @Transactional
     @Query(
             value = "UPDATE student set last_name = ?1 where email_address = ?2",
             nativeQuery = true
     )
     int updateStudentLastNameByEmail(String last_name, String email);
}