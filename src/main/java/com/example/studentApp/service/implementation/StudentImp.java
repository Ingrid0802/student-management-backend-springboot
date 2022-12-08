package com.example.studentApp.service.implementation;

import com.example.studentApp.exceptions.StudentNotFoundException;
import com.example.studentApp.model.Student;
import com.example.studentApp.repository.StudentRepository;
import com.example.studentApp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentImp implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentImp(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }


    @Override
    public Student createStudent(Student student) {
        String firstName = capitalizeFirstLetter(student.getFirstName());
        String lastName = capitalizeFirstLetter(student.getLastName());
        student.setFirstName(firstName);
        student.setLastName(lastName);
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));

    }

    @Override
    public Student updateStudent(Student student, Long id) {
        Student st = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        st.setFirstName(student.getFirstName());
        st.setLastName(student.getLastName());
        st.setEmail(student.getEmail());
        st.setAddress(student.getAddress());
        return studentRepository.save(st);
    }

    @Override
    public void deleteStudentById(Long id) {
        Student st = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        studentRepository.delete(st);
    }

    private String capitalizeFirstLetter(String name){
        if(name.isEmpty()){
            return "";

        }
        return name.substring(0,1).toUpperCase() + name.substring(1);

    }


}
