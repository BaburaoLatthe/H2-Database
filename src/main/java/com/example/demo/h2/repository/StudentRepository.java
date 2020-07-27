package com.example.demo.h2.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.h2.model.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> {

}
