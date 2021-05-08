package fr.BanqueImageJava.controllers;

import fr.BanqueImageJava.entities.MotCle;
import fr.BanqueImageJava.services.motCle.MotCleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("api/motcles")
public class MotCleController {
    private final static Logger log = LoggerFactory.getLogger(MotCleController.class);

    private MotCleService service;

    public MotCleController(MotCleService service) {
        this.service = service;
    }

    @GetMapping({"", "/all"})
    public List<MotCle> getAllMotCles() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public MotCle getOneMotCle(@PathVariable("id") Long id) {
        return service.read(id);
    }

    @PostMapping("/create")
    public MotCle create(@PathParam("motcle") MotCle motCle) {
        return service.create(motCle);
    }
}
