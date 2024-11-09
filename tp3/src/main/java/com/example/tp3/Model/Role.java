package com.example.tp3.Model;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @OneToMany(mappedBy = "role")
    private List<Utilisateur> utilisateurs;

    // Getters, setters et constructeurs
}
