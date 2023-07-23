package ru.hogwarts.school.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Student;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findByAge(int age);

    Collection<Student> findByAgeBetween(int minAge, int maxAge);

    @Query(value = "select count(*) from student", nativeQuery = true)
    long getStudentQuantity();

    @Query(value = "select AVG(age) from student", nativeQuery = true)
    double getAverageAge();

    @Query(value = "select * from student order by id desc limit 5", nativeQuery = true)
    List<Student> getLastStudent();

}
