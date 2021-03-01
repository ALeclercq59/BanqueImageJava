package fr.BanqueImageJava.services;

import fr.BanqueImageJava.entities.Image;
import fr.BanqueImageJava.entities.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    private final static Logger log = LoggerFactory.getLogger(UserServiceImplTest.class);

    @Autowired
    private UserService service;

    @BeforeEach
    void setUp() {
        log.trace("Check if service User is Injected ...");
        assertNotNull(service, "ERROR Service User NOT Injected !!!");
        log.trace("Service User Injected");
    }

    @Test
    void getAll() {
        var lst = service.getAll();
        lst.forEach(a -> log.trace("{}", a));
        log.trace("Total number of users : {}", lst.size());
    }

    @Test
    void create() {
        Users user = new Users();
        user.setFirstname("TestFirstname");
        user.setFirstname("Testname");
        user.setEmail("test@test.com");
        user.setPassword("TEST");
        user.setRole(0L);

        service.create(user);
        log.trace("Create Image : {}", user);
    }

    @Test
    @Transactional
    void read() {
        Long id = 4L;
        Users user = service.read(id);
        log.trace("User : {}", user);
    }

    @Test
    void update() {
        Users user = service.read(2L);
        user.setFirstname("Anthony1");
        service.update(user);
        log.trace("Update Image : {}", user);
    }

    @Test
    void delete() {
        service.delete(2L);
        assertNull(service.read(2L));
    }
}
