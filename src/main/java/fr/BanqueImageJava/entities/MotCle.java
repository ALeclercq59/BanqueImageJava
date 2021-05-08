package fr.BanqueImageJava.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = " mot_cle")
@Getter
@Setter
@ToString
public class MotCle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmotcle")
    private Long id;

    private String libelle;

    @ManyToMany(mappedBy = "motCles")
    @ToString.Exclude
    @JsonIgnoreProperties("motCles")
    private List<Image> images;
}
