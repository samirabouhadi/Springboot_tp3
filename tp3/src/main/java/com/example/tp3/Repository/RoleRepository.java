package com.example.tp3.Repository;

import com.example.tp3.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
     Optional<Role> findByNom(String nom);
}
