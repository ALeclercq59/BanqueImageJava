package fr.BanqueImageJava.services;

import fr.BanqueImageJava.entities.Users;

import java.util.List;

public interface UserService {
    List<Users> getAll();
    Users create(Users user);
    Users read(Long id);
    Users update(Users user);
    void delete(Long id);
}
