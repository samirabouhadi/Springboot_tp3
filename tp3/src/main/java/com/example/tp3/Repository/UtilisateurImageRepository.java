package com.example.tp3.Repository;

import com.example.tp3.Model.UtilisateurImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurImageRepository extends JpaRepository<UtilisateurImage, Long> {
}
