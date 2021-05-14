package fr.BanqueImageJava.controllers;

import fr.BanqueImageJava.entities.Categorie;
import fr.BanqueImageJava.services.categorie.CategorieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/categories")
public class CategorieController {
    private final static Logger log = LoggerFactory.getLogger(CategorieController.class);

    private CategorieService service;

    public CategorieController(CategorieService service) {
        this.service = service;
    }

    @GetMapping({"", "/all"})
    public List<Categorie> getAllCategories() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Categorie getOneCategory(@PathVariable("id") Long id) {
        return service.read(id);
    }

    @GetMapping("/{libelle}")
    public Categorie getOneCategoryByTitle(@PathVariable("libelle") String libelle) {
        return service.getOneCategoryByLibelle(libelle);
    }
}
