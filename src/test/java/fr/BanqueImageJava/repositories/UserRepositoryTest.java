package fr.BanqueImageJava.repositories;

import fr.BanqueImageJava.entities.Image;
import fr.BanqueImageJava.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    private final static Logger log = LoggerFactory.getLogger(UserRepositoryTest.class);

    @Autowired
    private UserRepository repository;

    @BeforeEach
    void setUp() {
        log.trace("setUp before All ...");
        assertNotNull(repository,"ImageCategorie Repository is NOT Inject !!!");
    }

    @Test
    void findAllImages() {
        log.trace("START findAllUsers");
        var lst = repository.findAll();
        lst.forEach(c->log.trace("{}", c));
        log.trace("Number of images : {}", lst.size());
        log.trace("END findAllImages");
    }

    @Test
    void findUserById() {
        log.trace("START findImageById");
        Long id = 1L;
        User user = repository.findById(id).orElse(null);
        log.trace("User Id({}) : {}", id, user);
        log.trace("END findImageById");
    }
}
