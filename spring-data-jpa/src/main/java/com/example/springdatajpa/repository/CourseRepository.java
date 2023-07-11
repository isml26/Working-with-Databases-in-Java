package com.example.springdatajpa.repository;

import com.example.springdatajpa.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}