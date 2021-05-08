package fr.BanqueImageJava.controllers;

import fr.BanqueImageJava.entities.Users;
import fr.BanqueImageJava.services.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UsersController {
    private final static Logger log = LoggerFactory.getLogger(UsersController.class);

    public UserService service;

    public UsersController(UserService service) {
        this.service = service;
    }

    @GetMapping({"", "/all"})
    public List<Users> getAllUsers() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Users getOneUser(@PathVariable("id") Long id) {
        return service.read(id);
    }


}
