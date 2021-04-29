package fr.BanqueImageJava.services;

import fr.BanqueImageJava.entities.Categorie;
import fr.BanqueImageJava.entities.Image;
import fr.BanqueImageJava.entities.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ImageServiceImplTest {

    private final static Logger log = LoggerFactory.getLogger(ImageServiceImplTest.class);

    @Autowired
    private ImageService service;

    @BeforeEach
    void setUp() {
        log.trace("Check if service Image is Injected ...");
        assertNotNull(service, "ERROR Service Image NOT Injected !!!");
        log.trace("Service Image Injected");
    }

    @Test
    void getAll() {
        var lst = service.getAll();
        lst.forEach(a -> log.trace("{}", a));
        log.trace("Total number of images : {}", lst.size());
    }

    @Test
    void create() {
        Users user = new Users();
        user.setIdUser(2L);
        user.setFirstname("TestFirstname");
        user.setFirstname("Testname");
        user.setEmail("test@test.com");
        user.setPassword("TEST");
        user.setRole(0L);

        Categorie categorie = new Categorie();
        categorie.setId(4L);
        categorie.setLibelle("Bâtiment");

        Image imageTest = new Image();
        imageTest.setDescription("Un beau bâtiment wow");
        imageTest.setLien("vbn,");
        imageTest.setCopyright(0);
        imageTest.setPublication(0);
        imageTest.setDateAccord(null);
        imageTest.setCategories(Collections.singletonList(categorie));

        categorie.setImages(Collections.singletonList(imageTest));

        imageTest.setUsers(user);
        service.create(imageTest);
        log.trace("Create Image : {}", imageTest);
    }

    @Test
    @Transactional
    void read() {
        Long id = 4L;
        Image image = service.read(id);
        log.trace("Image : {}", image);
    }

    @Test
    void update() {
        Image image = service.read(4L);
        image.setLien("batiment.jpg");
        service.update(image);
        log.trace("Update Image : {}", image);
    }

    @Test
    void delete() {
        service.delete(3L);
        assertNull(service.read(3L));
    }
}
