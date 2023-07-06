//package ru.hogwarts.school.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import ru.hogwarts.school.model.Faculty;
//import ru.hogwarts.school.model.Student;

import java.util.HashMap;

//public class FacultyServiceTest {
//    private final FacultyService facultyService = new FacultyService();
//
////    @BeforeEach
//    @Test
//    public void createFacultyTest() {
//        Faculty expected = new Faculty(1L, "name1", "orange");
//        Faculty actualFaculty = new Faculty(0L, "name1", "orange");
//        Assertions.assertEquals(expected, facultyService.createFaculty(actualFaculty));
//    }
//
//    @Test
//    public void readFacultyTest() {
//        Faculty expected = new Faculty(1L, "name1", "orange");
//        facultyService.createFaculty(new Faculty(1L, "name1", "orange"));
//        Assertions.assertEquals(expected, facultyService.readFaculty(1));
//    }
//
//    @Test
//    public void updateFacultyTest() {
//        Faculty expected = new Faculty(1L, "name11", "blue");
//        facultyService.createFaculty(new Faculty(1L, "name1", "orange"));
//        Faculty actualFaculty = new Faculty(1L, "name11", "blue");
//        Assertions.assertEquals(expected, facultyService.updateFaculty(actualFaculty));
//    }
//
//    @Test
//    public void updateFacultyNullTest() {
//        Faculty expected = null;
//        Faculty actualFaculty = new Faculty(3L, "name28", "red");
//        Assertions.assertEquals(expected, facultyService.updateFaculty(actualFaculty));
//    }
//
//    @Test
//    public void deleteFacultyTest() {
//        Faculty expected = new Faculty(1L, "name1", "orange");
//        facultyService.createFaculty(new Faculty(1L, "name1", "orange"));
//        Assertions.assertEquals(expected, facultyService.deleteFaculty(1));
//    }
//
//    @Test
//    public void redAllFacultyTest() {
//        facultyService.createFaculty(new Faculty(0L, "name1", "orange"));
//        facultyService.createFaculty(new Faculty(0L, "name2", "orange"));
//        facultyService.createFaculty(new Faculty(0L, "name3", "red"));
//        facultyService.createFaculty(new Faculty(0L, "name4", "blue"));
//        HashMap<Long, Faculty> expected = new HashMap<>();
//        expected.put(1L, new Faculty(1L, "name1", "orange"));
//        expected.put(2L, new Faculty(2L, "name2", "orange"));
//        expected.put(3L, new Faculty(3L, "name3", "red"));
//        expected.put(4L, new Faculty(4L, "name4", "blue"));
//        Assertions.assertIterableEquals(expected.values(), facultyService.readAllFaculty());
//    }
//
//    @Test
//    public void colorFilterFacultyTest() {
//        facultyService.createFaculty(new Faculty(0L, "name1", "orange"));
//        facultyService.createFaculty(new Faculty(0L, "name2", "orange"));
//        facultyService.createFaculty(new Faculty(0L, "name3", "red"));
//        facultyService.createFaculty(new Faculty(0L, "name4", "blue"));
//        HashMap<Long, Faculty> expected = new HashMap<>();
//        expected.put(1L, new Faculty(1L, "name1", "orange"));
//        expected.put(2L, new Faculty(2L, "name2", "orange"));
//        Assertions.assertIterableEquals(expected.values(), facultyService.colorFilterFaculty("orange"));
//    }
//}
