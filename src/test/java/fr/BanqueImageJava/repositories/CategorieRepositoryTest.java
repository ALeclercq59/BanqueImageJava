package fr.BanqueImageJava.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategorieRepositoryTest {

    private final static Logger log = LoggerFactory.getLogger(CategorieRepositoryTest.class);

    @Autowired
    private CategorieRepository repository;

    @BeforeEach
    void setUp() {
        log.trace("setUp before All ...");
        assertNotNull(repository,"Categorie Repository is NOT Inject !!!");
    }

    @Test
    void findAllCategories() {
        log.trace("START findAllCategories");
        var lst = repository.findAll();
        lst.forEach(c->log.trace("{}", c));
        log.trace("Number of categories : {}", lst.size());
        log.trace("END findAllCategories");
    }
}
