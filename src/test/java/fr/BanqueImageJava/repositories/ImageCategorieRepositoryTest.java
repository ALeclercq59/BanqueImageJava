package fr.BanqueImageJava.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ImageCategorieRepositoryTest {

    private final static Logger log = LoggerFactory.getLogger(ImageCategorieRepositoryTest.class);

    @Autowired
    private ImageCategorieRepository repository;

    @BeforeEach
    void setUp() {
        log.trace("setUp before All ...");
        assertNotNull(repository,"ImageCategorie Repository is NOT Inject !!!");
    }

}
