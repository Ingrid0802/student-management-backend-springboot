package com.example.studentApp.controller;

import com.example.studentApp.model.Student;
import com.example.studentApp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1")
@CrossOrigin("http://localhost:3000")

public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    // create student
    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student){
        return new ResponseEntity<>(studentService.createStudent(student), HttpStatus.CREATED);
    }

    // get all students
    @GetMapping("/students")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    // get student by id
    @GetMapping("/student/{id}")
        Student getStudentById(@PathVariable Long id){
            return studentService.findById(id);
        }


    // update student by id
    @PutMapping("/student/{id}")
        ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable Long id){
        return new ResponseEntity<>(studentService.updateStudent(student, id), HttpStatus.OK);
    }

    // delete student
    @DeleteMapping("/student/{id}")
    ResponseEntity<String> deletePost(@PathVariable Long id){
        studentService.deleteStudentById(id);
        return new ResponseEntity<>("Student deleted successfully", HttpStatus.OK);
    }
}
