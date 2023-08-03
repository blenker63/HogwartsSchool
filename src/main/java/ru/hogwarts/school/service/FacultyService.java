package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.*;
import java.util.stream.Stream;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty readFaculty(long idFaculty) {
        return facultyRepository.findById(idFaculty).get();
    }

    public Faculty updateFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long idFaculty) {
        facultyRepository.deleteById(idFaculty);
    }

    public Collection<Faculty> readAllFaculty() {
        return facultyRepository.findAll();
    }

    public Faculty colorFilterFaculty(String color) {
        return facultyRepository.findByColor(color);
    }

    public Collection<Faculty> findByColorOrName(String color, String name) {
        return facultyRepository.findAllByColorOrNameIgnoreCase(color, name);
    }

    public Optional<String> LongNameFaculty() {
        return facultyRepository.findAll().stream()
                .map(Faculty::getName)
                .max(Comparator.comparingInt(String::length));
    }

    public Integer number() {
        long start = System.currentTimeMillis();
       Integer sum = Stream.iterate(1, a -> a  + 1)
               .parallel()                                   //последовательное выполнение - 22 мс, паралельное выполнение - 8мс
                .limit(1_000_000)
                .reduce(0, (a, b) -> (a + b));
        System.out.println(System.currentTimeMillis() - start);
        return sum;
    }
}
