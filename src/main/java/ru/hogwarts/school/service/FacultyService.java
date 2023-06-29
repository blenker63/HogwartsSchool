package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.HashMap;

public class FacultyService {
    private final HashMap<Long, Faculty> faculties = new HashMap<Long, Faculty>();
    private long idFaculty = 0;

    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(++idFaculty);
        faculties.put(idFaculty, faculty);
        return faculty;
    }

    public Faculty readFaculty(long idFaculty) {
        return faculties.get(idFaculty);
    }

    public Faculty updateFaculty(Faculty faculty) {
        if (faculties.containsKey(idFaculty)) {
            faculties.put(faculty.getId(), faculty);
            return faculty;
        }
        return null;
    }
    public Faculty deleteFaculty(long idFaculty) {
        return faculties.remove(idFaculty);
    }

    public Collection<Faculty> readAllFaculty() {
        return faculties.values();
    }
}
