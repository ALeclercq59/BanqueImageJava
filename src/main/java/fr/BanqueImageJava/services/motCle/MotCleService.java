package fr.BanqueImageJava.services.motCle;

import fr.BanqueImageJava.entities.MotCle;

import java.util.List;

public interface MotCleService {
    List<MotCle> getAll();
    MotCle create(MotCle motCle);
    MotCle read(Long id);
    MotCle update(MotCle motCle);
    void delete(Long id);
    MotCle getOneMotCleByLibelle(String libelle);
}
