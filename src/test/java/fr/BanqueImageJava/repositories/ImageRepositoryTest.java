package fr.BanqueImageJava.repositories;

import fr.BanqueImageJava.entities.Image;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ImageRepositoryTest {

    private final static Logger log = LoggerFactory.getLogger(ImageRepositoryTest.class);

    @Autowired
    private ImageRepository repository;

    @BeforeEach
    void setUp() {
        log.trace("setUp before All ...");
        assertNotNull(repository,"Image Repository is NOT Inject !!!");
    }

    @Test
    void findAllImages() {
        log.trace("START findAllImages");
        var lst = repository.findAll();
        lst.forEach(c->log.trace("{}", c));
        log.trace("Number of images : {}", lst.size());
        log.trace("END findAllImages");
    }

    @Test
    void findImageById() {
        log.trace("START findImageById");
        Long id = 1L;
        Image image = repository.findById(id).orElse(null);
        log.trace("Image Id({}) : {}", id, image);
        log.trace("END findImageById");
    }

    @Test
    void findImagesByUserId() {
        log.trace("START findImagesByUserId");
        Long id = 1L;
        var lst = repository.findAllByUserId(id);
        log.trace("Images Id({}) : {}", id, lst);
        log.trace("END findImagesByUserId");
    }

}
