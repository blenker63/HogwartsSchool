package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final HashMap<Long, Student> students = new HashMap<>();
    private long idStudent = 0;

    public Student createStudent(Student student) {
        student.setId(++idStudent);
        students.put(idStudent, student);
        return student;
    }

    public Student readStudent(long idStudent) {
        return students.get(idStudent);
    }

    public Student updateStudent(Student student) {
        if (students.containsKey(student.getId())) {
            students.put(student.getId(), student);
            return student;
        }
        return null;
    }

    public Student deleteStudent(long idStudent) {
        return students.remove(idStudent);
    }

    public Collection<Student> readAllStudent() {
        return students.values();
    }

    public Collection ageFilterStudent(int age) {
        return students.values().stream()
                .filter(students -> students.getAge() == age)
                .collect(Collectors.toList());
    }
}
