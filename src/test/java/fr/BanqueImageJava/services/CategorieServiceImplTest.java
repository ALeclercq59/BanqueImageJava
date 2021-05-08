package fr.BanqueImageJava.services;

import fr.BanqueImageJava.entities.Categorie;
import fr.BanqueImageJava.entities.Image;
import fr.BanqueImageJava.entities.Users;
import fr.BanqueImageJava.services.categorie.CategorieService;
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
class CategorieServiceImplTest {

    private final static Logger log = LoggerFactory.getLogger(CategorieServiceImplTest.class);

    @Autowired
    private CategorieService service;

    @BeforeEach
    void setUp() {
        log.trace("Check if service Categorie is Injected ...");
        assertNotNull(service, "ERROR Service Categorie NOT Injected !!!");
        log.trace("Service Categorie Injected");

    }

    @Test
    void getAll() {
        var lst = service.getAll();
        lst.forEach(a -> log.trace("{}", a));
        log.trace("Total number of categories : {}", lst.size());
    }

    @Test
    void create() {
        Users user = new Users();
        user.setIdUser(1L);
        user.setFirstname("TestFirstname");
        user.setFirstname("Testname");
        user.setEmail("test@test.com");
        user.setPassword("TEST");
        user.setRole(0L);

        Categorie categorie = new Categorie();
        categorie.setLibelle("Bâtiment");

        Image imageTest = new Image();
        imageTest.setDescription("Un beau bâtiment");
        imageTest.setLien("vbn,");
        imageTest.setCopyright(0);
        imageTest.setPublication(0);
        imageTest.setDateAccord(null);
        imageTest.setCategories(Collections.singletonList(categorie));

        categorie.setImages(Collections.singletonList(imageTest));

        imageTest.setUsers(user);
        service.create(categorie);
        log.trace("Create Categorie : {}", categorie);
    }

    @Test
    @Transactional
    void read() {
        Long id = 3L;
        Categorie categorie = service.read(id);
        log.trace("Categorie : {}", categorie);
    }

    @Test
    void update() {
        Categorie categorie = service.read(3L);
        categorie.setLibelle("Gratte-ciel");
        service.update(categorie);
        log.trace("Update Categorie : {}", categorie);
    }

    @Test
    void delete() {
        service.delete(3L);
        assertNull(service.read(3L));
    }

    @Test
    void getOneCategoryByLibelle() {
        String libelle = "Fusee";
        Categorie categorie = service.getOneCategoryByLibelle(libelle);
        log.trace("Category by libelle : {}", categorie);
    }
}
