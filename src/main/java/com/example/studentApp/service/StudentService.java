package com.example.studentApp.service;

import com.example.studentApp.model.Student;

import java.util.List;

public interface StudentService {

    Student createStudent(Student student);

    List<Student> getAllStudents();

    Student findById(Long id);

    Student updateStudent(Student student, Long id);

    void deleteStudentById(Long id);

}
