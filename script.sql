select * from student;
select *
from student where age between 21 and 22;
select name from student;
select *
from student where name like '%o%';
select *
from student where age < 21;
select *
from student order by age;
select *
from student, faculty
where student.faculty_id = faculty.id
  and student.id = 2;
select *
from faculty, student
where  faculty_id = 3;

