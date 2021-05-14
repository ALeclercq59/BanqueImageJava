package fr.BanqueImageJava.controllers;

import fr.BanqueImageJava.entities.Image;
import fr.BanqueImageJava.services.image.ImageService;
import fr.BanqueImageJava.services.user.UserService;
import fr.BanqueImageJava.services.client.DetectLbalResponse;
import org.apache.tomcat.jni.Local;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("api/images")
public class ImageController {
    private final static Logger log = LoggerFactory.getLogger(ImageController.class);

    public ImageService service;
    public UserService userService;

    public ImageController(ImageService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    String linkImage = "D:/Cours B3/JAVA/TP-BanqueImage/TP-BanqueImage/src/main/resources/images/";

    @GetMapping({"", "/all"})
    public List<Image> getAllImages() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Image getOneImage(@PathVariable("id") Long id) {
        return service.read(id);
    }

    @GetMapping("/publication/{nombre}")
    public List<Image> getImageByPublication(@PathVariable("nombre") Long nombre) {
        return service.getImageByPublication(nombre);
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadFile(@PathParam("idUser") Long idUser, @RequestPart MultipartFile file, @PathParam("title") String title,
                                             @PathParam("description") String description, @PathParam("copyright") Long copyright) throws Exception {

        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();

        Image image = new Image();
        image.setName("");
        image.setUsers(userService.read(idUser));
        image.setTitle(title);
        image.setDescription(description);
        image.setCopyright(copyright);
        image.setPublication(1L);
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

        imageCreated.setName(name);
        imageCreated.setLien("http://localhost:8082/api/images/show/" + name);
        service.update(imageCreated);

        return ResponseEntity.ok().body(imageCreated);
    }


    @GetMapping(value="/analyse/{id}")
    public DetectLbalResponse analyseImage(@PathVariable("id") Long id) throws Exception {
        Image image = service.read(id);
        return service.urlSignImage(image.getName());
    }

    @PutMapping(value="/update")
    public Image updateImage(Image image) {
        return service.update(image);
    }

    @RequestMapping(value = "/show/{name}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImage(@PathVariable("name") String name) throws IOException {
        File file = new File(linkImage + name);

        byte[] data = Files.readAllBytes(file.toPath());

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(data);
    }

    @PutMapping(value="/{id}/addCategorie")
    public void addCategorie(@PathVariable("id") Long id, @PathParam("categories") String[] categories) {
        service.addCategorieForImage(id, categories);
    }

    @PutMapping(value="/{id}/addMotCles")
    public void addMotCles(@PathVariable("id") Long id, @PathParam("motcles") String[] motcles) {
        for (var mot: motcles)
        {
            System.out.println(mot);
        }
        service.addMotclesForImage(id, motcles);
    }

    @PutMapping(value="/{id}/date")
    public Image updateDate(@PathVariable("id") Long id, @PathParam("date") String date){
        Image image = service.read(id);
        image.setDateAccord(LocalDate.parse(date));
        return service.update(image);
    }

    @PutMapping(value="/{id}/publication")
    public Image updatePublication(@PathVariable("id") Long id, @PathParam("publication") Long publication) {
        Image image = service.read(id);
        image.setPublication(publication);
        return service.update(image);
    }

    @PutMapping(value="/{id}/update")
    public Image updateImage(@PathVariable("id") Long id, @PathParam("title") String title, @PathParam("description") String description, @PathParam("copyright") Long copyright, @PathParam("publication") Long publication) {
        Image image = service.read(id);
        image.setTitle(title);
        image.setDescription(description);
        image.setCopyright(copyright);
        image.setPublication(publication);

        return service.update(image);
    }

}
