package fr.BanqueImageJava.repositories;

import fr.BanqueImageJava.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByEmailEqualsAndPasswordEquals(String mail, String password);
}
