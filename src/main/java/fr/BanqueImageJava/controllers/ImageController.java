package fr.BanqueImageJava.controllers;

import fr.BanqueImageJava.entities.Image;
import fr.BanqueImageJava.services.ImageService;
import fr.BanqueImageJava.services.UserService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/images")
public class ImageController {
    private final static Logger log = LoggerFactory.getLogger(ImageController.class);

    public ImageService service;
    public UserService userService;

    public ImageController(ImageService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    String linkImage = "D:\\Cours B3\\JAVA\\TP-BanqueImage\\TP-BanqueImage\\src\\images\\";

    @GetMapping({"", "/all"})
    public List<Image> getAllImages() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Image getOneImage(@PathVariable("id") Long id) {
        return service.read(id);
    }

    @PostMapping("/create")
    public Image create(@PathParam("description") String description, @PathParam("idUser") Long idUser) {
        Image image = new Image();
        image.setDescription(description);
        image.setCopyright(1);
        image.setPublication(1);
        image.setLien("vide");
        image.setUsers(userService.read(idUser));
        return service.create(image);
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadFile(@PathParam("idUser") Long idUser, @RequestPart MultipartFile file, @PathParam("description") String description, @PathParam("copyright") int copyright) throws IOException {

        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();

        Image image = new Image();
        image.setUsers(userService.read(idUser));
        image.setDescription(description);
        image.setCopyright(copyright);
        image.setPublication(1);
        image.setLien("vide");
        service.create(image);

        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String name = uuidAsString + extension;

        File convertFile = new File(linkImage + name);
        convertFile.createNewFile();
        FileOutputStream fout = new FileOutputStream(convertFile);
        fout.write(file.getBytes());
        fout.close();

        Image imageCreated = service.read(image.getId());
        imageCreated.setLien(linkImage + name);
        service.update(imageCreated);

        service.urlSignImage(name, linkImage + name);

        return new ResponseEntity<>("File is uploaded seccessfully", HttpStatus.OK);
    }
}
