package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping("{idStudent}")
    public ResponseEntity<Student> readStudent(@PathVariable long idStudent) {
        Student student = studentService.readStudent(idStudent);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student foundStudent = studentService.updateStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("{idStudent}")
    public Student deleteStudent(@PathVariable long idStudent) {
        return studentService.deleteStudent(idStudent);
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> readAllStudent() {
        return ResponseEntity.ok(studentService.readAllStudent());
    }

    @GetMapping("{age}")
    public ResponseEntity<Collection<Student>> ageFilterStudent(@PathVariable int age) {
        return ResponseEntity.ok(studentService.ageFilterStudent(age));
    }
}
