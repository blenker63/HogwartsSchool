package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.Collection;

@Service
public class FacultyService {
//    private final HashMap<Long, Faculty> faculties = new HashMap<Long, Faculty>();
//    private long idFaculty = 0;
private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
//        faculty.setId(++idFaculty);
//        faculties.put(idFaculty, faculty);
//        return faculty;

        return  facultyRepository.save(faculty);
    }

    public Faculty readFaculty(long idFaculty) {
//        return faculties.get(idFaculty);
        return  facultyRepository.getById(idFaculty);
    }

    public Faculty updateFaculty(Faculty faculty) {
//        if (faculties.containsKey(faculty.getId())) {
//            faculties.put(faculty.getId(), faculty);
//            return faculty;
//        }
//        return null;
        return  facultyRepository.save(faculty);
    }
    public void deleteFaculty(long idFaculty) {
//        return faculties.remove(idFaculty);
         facultyRepository.deleteById(idFaculty);
    }

    public Collection<Faculty> readAllFaculty() {
//        return faculties.values();
        return facultyRepository.findAll();
    }

//    public Collection colorFilterFaculty(String color) {
//        return faculties.values().stream()
//                .filter(faculty -> faculty.getColor() == color)
//                .collect(Collectors.toList());
//    }
}
