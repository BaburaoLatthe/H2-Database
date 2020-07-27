package com.example.demo.h2.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.h2.model.Student;
import com.example.demo.h2.service.StudentService;

@RestController
@RequestMapping(value = "/student")
public class StudentController {

	@Autowired
	StudentService studentService;
	
	@GetMapping(value = "/")
	private String fetchStudent() {
		return "Yes I'm available";
	}

//creating a get mapping that retrieves all the students detail from the database   
	@GetMapping("/fetch")
	private List<Student> getAllStudent() {
		return studentService.getAllStudent();
	}

//creating a get mapping that retrieves the detail of a specific student  
	@GetMapping("/{id}")
	private Student getStudent(@PathVariable("id") int id) {
		return studentService.getStudentById(id);
	}

//creating a delete mapping that deletes a specific student  
	@DeleteMapping("/delete/{id}")
	private void deleteStudent(@PathVariable("id") int id) {
		studentService.delete(id);
	}

//creating post mapping that post the student detail in the database  
	@PostMapping("/add")
	private int saveStudent(@RequestBody Student student) {
		Optional<Integer> findFirst = studentService.getAllStudent().stream()
																	.map(Student::getId)
																	.sorted(Collections.reverseOrder())
																	.findFirst();
		if(findFirst.isPresent())
			student.setId(findFirst.get()+1);
		else
			student.setId(1);
		
		studentService.saveOrUpdate(student);
		return student.getId();
	}
}