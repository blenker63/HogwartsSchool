package ru.hogwarts.school.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.HashMap;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FacultyServiceTest {
    @Mock
    FacultyRepository facultyRepositoryMoc;
    //    @InjectMocks
    FacultyService facultyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        facultyService = new FacultyService(facultyRepositoryMoc);
    }
    @Test
    public void updateFacultyTest() {
when(facultyRepositoryMoc.findById(1L)).thenReturn(Optional.of(new Faculty(1L, "grif", "red")));
    when(facultyRepositoryMoc.save(any())).then(invocation -> invocation.getArguments()[0]);
    var updatedFaculty = facultyService.updateFaculty(new Faculty(1L, "name1", "green"));
    assertThat(updatedFaculty.getColor()).isEqualTo(22);

    var empty = facultyService.updateFaculty(new Faculty(10L, "empty", "green"));
    assertThat(empty).isNull();
}
    @Test
    public void deleteFacultyTest() {
//        Faculty expected = new Faculty(1L, "name1", "orange");
//        facultyService.createFaculty(new Faculty(1L, "name1", "orange"));
//        Assertions.assertEquals(expected, facultyService.deleteFaculty(1));
        facultyService.readFaculty(1);
        verify(facultyRepositoryMoc, times(1)).deleteById(1L);
    }

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
}
