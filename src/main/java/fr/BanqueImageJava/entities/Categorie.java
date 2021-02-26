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

    @ManyToMany
    @JoinTable(name = "image_categorie",
            joinColumns = @JoinColumn(name = "idCategorie"),
            inverseJoinColumns = @JoinColumn(name = "idImage"))
    private List<Image> images;

    @Override
    public String toString() {
        return "Categorie{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", images=" + images +
                '}';
    }
}
