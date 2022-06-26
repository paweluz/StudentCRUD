package com.developersmill.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
        studentRepository.findStudentByEmail(student.getEmail()).ifPresent(s -> {
            throw new IllegalStateException("Student already exists");
        });
        System.out.println(student);
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.findById(id).ifPresentOrElse(studentRepository::delete,
                () -> {
                    throw new RuntimeException("student does not exist");
                });
    }

    @Transactional
    public void updateUser(Long id, String name, String email) {
        studentRepository.findById(id).ifPresentOrElse(student -> {
                    if (name != null && name.length() != 0) {
                        student.setName(name);
                    }
                    if (email != null && email.length() != 0 && studentRepository.findStudentByEmail(email).isEmpty()) {
                        student.setEmail(email);
                    }
                },
                () -> {
                    throw new RuntimeException("student does not exist");
                });
    }
}
