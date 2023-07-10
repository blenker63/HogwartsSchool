package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {

        return studentRepository.save(student);
    }

    public Student readStudent(long idStudent) {
        return studentRepository.getById(idStudent);
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(long idStudent) {
        studentRepository.deleteById(idStudent);
    }

    public Collection<Student> readAllStudent() {
//        return students.values();
        return studentRepository.findAll();
    }

    public Collection ageFilterStudent(int age) {
        return studentRepository.findByAge(age);
////        return students.values().stream()
////                .filter(students -> students.getAge() == age)
////                .collect(Collectors.toList());
////        return studentRepository.;
//        return List<Student> findByNameLike(int age);
    }
}

