package fr.BanqueImageJava.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categorie")
@Getter
@Setter
@ToString
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcategorie")
    private Long id;

    private String libelle;

    @ManyToMany(mappedBy="categories", cascade=CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnoreProperties("categories")
    private List<Image> images;

}
