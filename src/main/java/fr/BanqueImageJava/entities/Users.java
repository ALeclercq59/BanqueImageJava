package fr.BanqueImageJava.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="user")
@Getter
@Setter
@ToString
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser")
    private Long idUser;

    private String name;
    private String firstname;
    private String email;
    private String password;
    private Long role;

    @OneToMany(mappedBy = "users")
    @ToString.Exclude
    @JsonIgnoreProperties("users")
    private List<Image> images;

}
