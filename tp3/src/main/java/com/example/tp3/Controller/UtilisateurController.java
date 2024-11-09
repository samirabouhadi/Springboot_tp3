package com.example.tp3.Controller;

import com.example.tp3.Model.Utilisateur;
import com.example.tp3.Model.UtilisateurImage;
import com.example.tp3.Service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UtilisateurController {
    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurService.findAll();
    }

    @PostMapping
    public Utilisateur createUtilisateur(@RequestBody Utilisateur utilisateur, @RequestParam Long roleId) {
        return utilisateurService.createUtilisateurWithRole(utilisateur, roleId);
    }

    @GetMapping("/{id}")
    public Utilisateur getUtilisateurById(@PathVariable Long id) {
        return utilisateurService.findById(id).orElseThrow();
    }

    @PutMapping("/{utilisateurId}/role/{roleId}")
    public Utilisateur assignRoleToUtilisateur(@PathVariable Long utilisateurId, @PathVariable Long roleId) {
        return utilisateurService.assignRole(utilisateurId, roleId);
    }

    @PostMapping("/{utilisateurId}/image")
    public Utilisateur addImageToUtilisateur(@PathVariable Long utilisateurId, @RequestBody UtilisateurImage image) {
        return utilisateurService.addImageToUtilisateur(utilisateurId, image);
    }

    @DeleteMapping("/{id}")
    public void deleteUtilisateur(@PathVariable Long id) {
        utilisateurService.deleteById(id);
    }
}
