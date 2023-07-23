package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.AvatarRepository;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.rmi.StubNotFoundException;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarService {
    private String avatarsDir;

    private final StudentService studentService;
    private final AvatarRepository avatarRepository;

    @Autowired
    public AvatarService(StudentService studentService,
                         AvatarRepository avatarRepository,
                         @Value("$avatar.dir.path$") String avatarsDir) {
        this.studentService = studentService;
        this.avatarsDir = avatarsDir;
        this.avatarRepository = avatarRepository;
    }

    public void uploadAvatar(long idStudent, MultipartFile file) throws IOException {
       Student student = studentService.readStudent(idStudent);
        if (student == null) {
            throw new StudentNotFoundException();
        }
        var ext = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        var dir = Path.of(avatarsDir).toFile();
        if (!dir.exists()) {
        Files.createDirectory(Path.of(avatarsDir));
        }
        var path = avatarsDir + "/" + student.getId() + "_" + file.getName() + "." + ext;
        try (var in = file.getInputStream();
             var out = new BufferedOutputStream(new FileOutputStream(path))) {
            in.transferTo(out);
        }
        Avatar avatar = findAvatar(idStudent);
        avatar.setStudent(student);
        avatar.setPreview(file.getBytes());
        avatar.setFilePath(path.toString());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatarRepository.save(avatar);

    }

    //
//    public File findAvatarFile(long idStudent) {
//        var avatar = findAvatar(idStudent);
//        return new File(avatar.getFilePath());
//
//    }
    public Avatar findAvatar(long idStudent) {
        return avatarRepository.findByStudentId(idStudent).orElse(new Avatar());
    }
}
