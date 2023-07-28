SELECT student.name, student.age,
       faculty.name
FROM student
         INNER JOIN faculty  on student.faculty_id = faculty.id;

SELECT avatar.file_path,
       student.name
FROM avatar
         INNER JOIN student  on avatar.student_id = student.id;