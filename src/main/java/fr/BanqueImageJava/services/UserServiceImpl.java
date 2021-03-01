package fr.BanqueImageJava.services;

import fr.BanqueImageJava.entities.Categorie;
import fr.BanqueImageJava.entities.Users;
import fr.BanqueImageJava.repositories.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private final static Logger log = LoggerFactory.getLogger(CategorieServiceImpl.class);

    private final UsersRepository repository;

    public UserServiceImpl(UsersRepository repository) {
        log.trace("CategorieServiceImpl instanced ");
        this.repository = repository;
    }

    @Override
    public List<Users> getAll() {
        return repository.findAll();
    }

    @Override
    public Users create(Users user) {
        return repository.save(user);
    }

    @Override
    public Users read(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Users update(Users user) {
        return repository.saveAndFlush(user);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
