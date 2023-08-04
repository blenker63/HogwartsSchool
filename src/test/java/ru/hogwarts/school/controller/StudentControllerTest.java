package ru.hogwarts.school.controller;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.model.Student;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoads() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    public void defaultMessageTest() throws Exception {
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student", String.class))
                .isNotNull();
    }

    @Test
    public void createStudentTest() throws Exception {
        Student studentTest = new Student();
        studentTest.setName("nameTest1");
        studentTest.setAge(100);
        var s = this.restTemplate.postForObject("http://localhost:" + port + "/student", studentTest, Student.class);
        Assertions
                .assertThat(s)
                .isNotNull();
    }

    @Test
    public void getStudentTest() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student", String.class))
                .isNotNull();
    }

    @Test
    public void updateStudentTest() throws Exception {
        Student studentTest = new Student();
        HttpEntity<Student> httpStudent = new HttpEntity<>(studentTest);
//        studentTest.setId(9L);
        studentTest.setName("nameTestUpdate");
        studentTest.setAge(100);
        ResponseEntity<Student> studentEntity = restTemplate.exchange(
                "http://localhost:" + port + "/student",
                HttpMethod.PUT,
                httpStudent,
                Student.class);
        Assertions
                .assertThat(studentEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

//    @Test
//    public void deleteStudentTest() throws Exception {
//}
}