package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    @Autowired
    StudentService(StudentRepository studentRepository)
    {
        this.studentRepository = studentRepository;
    }
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {

        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

        if(studentOptional.isPresent())
        {
            throw new IllegalStateException("Email already taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long Studentid) {
        boolean exists = studentRepository.existsById(Studentid);
        if(!exists)
        {
            throw new IllegalArgumentException("The Id "+ Studentid +" doesn't exists");
        }
        studentRepository.deleteById(Studentid);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.getReferenceById(studentId);
//        if(studen.)
//        {
//            throw new IllegalArgumentException("The Id "+ studentId +" doesn't exists");
//        }
        if(name!=null && name.length()>0 && !name.equals(student.getName()))
        {
            student.setName(name);
        }

        if(email!=null && email.length()>0 && !email.equals(student.getEmail()))
        {
            Optional<Student> studentEmailOptional = studentRepository.findStudentByEmail(email);
            if(studentEmailOptional.isPresent())
                throw new IllegalStateException("Email already taken");
            student.setEmail(email);
        }

    }
}
