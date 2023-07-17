package ru.hogwarts.school.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.StudentService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    private final AvatarService avatarService;

    public StudentController(StudentService studentService,
                             AvatarService avatarService) {
        this.studentService = studentService;
        this.avatarService = avatarService;

    }

    @PostMapping(consumes = {"application/xml", "application/json"})
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping("/id/{idStudent}")
    public ResponseEntity<Student> readStudent(@PathVariable long idStudent) {
//    public Student readStudent(@PathVariable long idStudent) {
        Student student = studentService.readStudent(idStudent);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
//    return studentService.readStudent(idStudent);
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student foundStudent = studentService.updateStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("{idStudent}")
    public ResponseEntity deleteStudent(@PathVariable long idStudent) {
        studentService.deleteStudent(idStudent);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> readAllStudent() {
        return ResponseEntity.ok(studentService.readAllStudent());
    }

    @GetMapping("{age}")
    public ResponseEntity<Collection<Student>> ageFilterStudent(@PathVariable int age) {
        return ResponseEntity.ok(studentService.ageFilterStudent(age));
    }

    @GetMapping("/findByAgeBetween")
    public Collection<Student> findByAgeBetween(@RequestParam int minAge, @RequestParam int maxAge) {
        return studentService.findByAgeBetween(minAge, maxAge);
    }

    @PostMapping(value = "/{idStudent}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadAvatar(@PathVariable long idStudent,
                                               @RequestParam MultipartFile avatar)
            throws IOException {
        if (avatar.getSize() >= 1024 * 300) {
            return ResponseEntity.badRequest().body("File is too big");
        }
        avatarService.uploadAvatar(idStudent, avatar);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/id/{id}/avatar/preview")
    public ResponseEntity<byte[]> downloadAvatar(@PathVariable long idStudent) {
        Avatar avatar = avatarService.findAvatar(idStudent);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getPreview().length);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getPreview());
    }
    @GetMapping(value = "/id/{idStudent}/avatar")
    public void downloadAvatar(@PathVariable Long idStudent, HttpServletResponse response) throws IOException {
        Avatar avatar = avatarService.findAvatar(idStudent);

        Path path = Path.of(avatar.getFilePath());

        try (InputStream is = Files.newInputStream(path);
             OutputStream os  = response.getOutputStream();) {
            response.setContentType(avatar.getMediaType());
            response.setContentLengthLong(avatar.getFileSize());
            is.transferTo(os);


        }
    }
}