package ru.hogwarts.school.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Student;

import java.lang.reflect.Array;
import java.util.*;

public class StudentServiceTest {
    private final StudentService studentService = new StudentService();

    @BeforeEach
    @Test
    public void createStudentTest() {
        Student expected = new Student(1L, "name1", 20);
        Student actualStudent = new Student(0L, "name1", 20);
        Assertions.assertEquals(expected, studentService.createStudent(actualStudent));
    }

    @Test
    public void readStudentTest() {
        Student expected = new Student(1L, "name1", 20);
        Assertions.assertEquals(expected, studentService.readStudent(1));
    }

    @Test
    public void updateStudentTest() {
        Student expected = new Student(1L, "name11", 22);
        Student actualStudent = new Student(1L, "name11", 22);
        Assertions.assertEquals(expected, studentService.updateStudent(actualStudent));
    }

    @Test
    public void updateStudentNullTest() {
        Student expected = null;
        Student actualStudent = new Student(2L, "name22", 23);
        Assertions.assertEquals(expected, studentService.updateStudent(actualStudent));
    }

    @Test
    public void deleteStudentTest() {
        Student expected = new Student(1L, "name1", 20);
        Assertions.assertEquals(expected, studentService.deleteStudent(1));
    }

    @Test
    public void redAllStudentTest() {
        studentService.createStudent(new Student(0L, "name2", 20));
        studentService.createStudent(new Student(0L, "name3", 23));
        studentService.createStudent(new Student(0L, "name4", 22));
        HashMap<Long, Student> expected = new HashMap<>();
        expected.put(1L, new Student(1L, "name1", 20));
        expected.put(2L, new Student(2L, "name2", 20));
        expected.put(3L, new Student(3L, "name3", 23));
        expected.put(4L, new Student(4L, "name4", 22));
        Assertions.assertIterableEquals(expected.values(), studentService.readAllStudent());
    }
}
