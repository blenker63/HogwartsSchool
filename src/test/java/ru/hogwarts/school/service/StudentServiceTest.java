//package ru.hogwarts.school.service;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import ru.hogwarts.school.model.Student;
//import ru.hogwarts.school.repositories.StudentRepository;
//
//import java.util.*;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class StudentServiceTest {
//    @Mock
//    StudentRepository studentRepositoryMoc;
////    @InjectMocks
//    StudentService studentService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//        studentService = new StudentService(studentRepositoryMoc);
//    }
//
//
//    @Test
//    public void updateStudentTest() {
//        when(studentRepositoryMoc.findById(1L)).thenReturn(Optional.of(new Student(1L, "name1", 21)));
//        when(studentRepositoryMoc.save(any())).then(invocation -> invocation.getArguments()[0]);
////        var actual = studentService.updateStudent(new Student(1L, "name1", 22)).getAge();
////        Assertions.assertEquals(22, actual);
//        var updatedStudent = studentService.updateStudent(new Student(1L, "name1", 22));
//        assertThat(updatedStudent.getAge()).isEqualTo(22);
//
//        var empty = studentService.updateStudent(new Student(10L, "empty", 10));
//        assertThat(empty).isNull();
//    }
//
//    @Test
//    public void deleteStudentTest() {
//        studentService.readStudent(1);
//        verify(studentRepositoryMoc, times(1)).existsById(1L);
//    }
//    //        @Test
////    public void createStudentTest() {
////        var expected = new Student(1L, "name1", 20);
////        Student actualStudent = new Student(1L, "name1", 20);
//////        Assertions.assertEquals(expected, studentService.createStudent(actualStudent));
////        when(studentRepositoryMoc.save(actualStudent)).thenReturn(actualStudent);
////    }
////    @Test
////    public void readStudentTest() {
////        Student expected = new Student(1L, "name1", 20);
////        studentService.createStudent(new Student(1L, "name1", 20));
////        Assertions.assertEquals(expected, studentService.readStudent(1));
////    }
////
////
////
////    @Test
////    public void redAllStudentTest() {
////        studentService.createStudent(new Student(1L, "name1", 20));
////        studentService.createStudent(new Student(0L, "name2", 20));
////        studentService.createStudent(new Student(0L, "name3", 23));
////        studentService.createStudent(new Student(0L, "name4", 22));
////        HashMap<Long, Student> expected = new HashMap<>();
////        expected.put(1L, new Student(1L, "name1", 20));
////        expected.put(2L, new Student(2L, "name2", 20));
////        expected.put(3L, new Student(3L, "name3", 23));
////        expected.put(4L, new Student(4L, "name4", 22));
////        Assertions.assertIterableEquals(expected.values(), studentService.readAllStudent());
////    }
////
//////    @Test
//////    public void ageFilterStudentTest() {
//////        studentService.createStudent(new Student(0L, "name1", 20));
//////        studentService.createStudent(new Student(0L, "name2", 20));
//////        studentService.createStudent(new Student(0L, "name3", 23));
//////        studentService.createStudent(new Student(0L, "name4", 22));
//////        HashMap<Long, Student> expected = new HashMap<>();
//////        expected.put(1L, new Student(1L, "name1", 20));
//////        expected.put(2L, new Student(2L, "name2", 20));
//////        Assertions.assertIterableEquals(expected.values(), studentService.ageFilterStudent(20));
//////    }
////
//}

