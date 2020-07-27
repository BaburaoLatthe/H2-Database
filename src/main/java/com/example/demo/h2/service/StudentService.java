package com.example.demo.h2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.h2.model.Student;
import com.example.demo.h2.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository studentRepository;

//Getting all student records  
	public List<Student> getAllStudent() {
		List<Student> students = new ArrayList<Student>();
		studentRepository.findAll().forEach(student -> students.add(student));
		return students;
	}

//Getting a specific record  
	public Student getStudentById(int id) {
		return studentRepository.findById(id).get();
	}

	public void saveOrUpdate(Student student) {
		studentRepository.save(student);
	}

//Deleting a specific record  
	public void delete(int id) {
		studentRepository.deleteById(id);
	}
}
