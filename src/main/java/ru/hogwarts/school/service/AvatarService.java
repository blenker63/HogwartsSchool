package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.AvatarRepository;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.rmi.StubNotFoundException;
import java.util.List;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;


import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarService {

//    @Value("${avatar.dir.path}")
    private final String avatarsDir;

    private final StudentService studentService;
    private final AvatarRepository avatarRepository;

//    @Autowired
    public AvatarService(StudentService studentService,
                         AvatarRepository avatarRepository,
                         @Value("${avatar.dir.path}") String avatarsDir) {
        this.studentService = studentService;
        this.avatarsDir = avatarsDir;
        this.avatarRepository = avatarRepository;
    }

//    public void uploadAvatar(long idStudent, MultipartFile file) throws IOException {
//        Student student = studentService.readStudent(idStudent);
//        if (student == null) {
//            throw new StudentNotFoundException();
//        }
//        var ext = file.getName().substring(file.getName().lastIndexOf(".") + 1);
//        var dir = Path.of(avatarsDir).toFile();
//        if (!dir.exists()) {
//            Files.createDirectory(Path.of(avatarsDir));
//        }
//        var path = avatarsDir + "/" + student.getId() + "." + file.getName() + "." + ext;
//        try (var in = file.getInputStream();
//             var out = new BufferedOutputStream(new FileOutputStream(path))) {
//            in.transferTo(out);
//        }
//        Avatar avatar = findAvatar(idStudent);
//        avatar.setPreview(file.getBytes());
//        avatar.setFilePath(path);
//        avatar.setFileSize(file.getSize());
//        avatar.setMediaType(file.getContentType());
//        avatar.setStudent(student);
//        avatarRepository.save(avatar);
//    }

    public void uploadAvatar(long idStudent, MultipartFile file) throws IOException {
        var student = studentService.readStudent(idStudent);
        if (student == null) {
            throw new StudentNotFoundException();
        }
        Path filePath = Path.of(avatarsDir, idStudent + "." + getExtension(file.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (InputStream is = file.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
        ) {
            bis.transferTo(bos);
        }
        Avatar avatar = findAvatar(idStudent);
//        Avatar avatar = new Avatar();
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
//        avatar.setPreview(file.getBytes());
        avatar.setPreview(generateImagePreview(filePath));
        avatarRepository.save(avatar);

    }
    private byte[] generateImagePreview(Path filePath) throws IOException {
        try (InputStream is = Files.newInputStream(filePath);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
             BufferedImage image = ImageIO.read(bis);

        int height = image.getHeight() / (image.getWidth() / 100);
        BufferedImage preview = new BufferedImage(100, height, image.getType());
        Graphics2D graphics = preview.createGraphics();
        graphics.drawImage(image, 0, 0, 100, height, null);
        graphics.dispose();

        ImageIO.write(preview, getExtension(filePath.getFileName().toString()), baos);
        return baos.toByteArray();


        }
    }
    public Avatar findAvatar(Long idStudent) {
//        return avatarRepository.findByStudentId(idStudent).orElseThrow();
        return avatarRepository.findByStudentId(idStudent).orElse(new Avatar());
    }
    public List<Avatar> getAvatarPage(int pageNumber, int pageSize) {
        var request = PageRequest.of(pageNumber, pageSize);
        return avatarRepository.findAll(request).getContent();
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
