package fr.BanqueImageJava.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categorie")
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcategorie")
    private Long id;

    private String libelle;

    @ManyToMany(mappedBy="categories")
    private List<Image> images;

    @Override
    public String toString() {
        return "Categorie{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                '}';
    }
}
