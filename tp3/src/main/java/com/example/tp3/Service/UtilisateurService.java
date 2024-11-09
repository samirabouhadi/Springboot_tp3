package com.example.tp3.Service;

import com.example.tp3.Model.Role;
import com.example.tp3.Model.Utilisateur;
import com.example.tp3.Model.UtilisateurImage;
import com.example.tp3.Repository.RoleRepository;
import com.example.tp3.Repository.UtilisateurImageRepository;
import com.example.tp3.Repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UtilisateurImageRepository utilisateurImageRepository;

    public List<Utilisateur> findAll() {
        return utilisateurRepository.findAll();
    }
    public Utilisateur createUtilisateurWithRole(Utilisateur utilisateur, Long roleId) {
        Role role = roleRepository.findById(roleId)
                 .orElseThrow(() -> new NullPointerException("Utilisateur non trouvé avec l'ID "));
        utilisateur.setRole(role);
        return utilisateurRepository.save(utilisateur);
    }

    public Optional<Utilisateur> findById(Long id) {
        return utilisateurRepository.findById(id);
    }

    public Utilisateur assignRole(Long utilisateurId, Long roleId) {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new NullPointerException("Utilisateur non trouvé avec l'ID : " + utilisateurId));
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new NullPointerException("Rôle non trouvé"));
        utilisateur.setRole(role);
        return utilisateurRepository.save(utilisateur);
    }
    public Utilisateur addImageToUtilisateur(Long utilisateurId, UtilisateurImage image) {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new NullPointerException("Utilisateur non trouvé avec l'ID : " + utilisateurId));
        image.setUtilisateur(utilisateur);
        utilisateur.setUtilisateurImage(image);
        utilisateurImageRepository.save(image);
        return utilisateurRepository.save(utilisateur);
    }

    public void deleteById(Long id) {
        if (!utilisateurRepository.existsById(id)) {
            throw new NullPointerException("Utilisateur non trouvé");
        }
        utilisateurRepository.deleteById(id);
    }

    public void deleteRoleById(Long roleId) {
        if (!roleRepository.existsById(roleId)) {
            throw new NullPointerException("Rôle non trouvé");
        }
        roleRepository.deleteById(roleId);
    }

    public void deleteImageFromUtilisateur(Long utilisateurId, Long imageId) {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new NullPointerException("Utilisateur non trouvé"));
        UtilisateurImage image = utilisateurImageRepository.findById(imageId)
                .orElseThrow(() -> new NullPointerException("Image non trouvée"));
        if (!image.getUtilisateur().equals(utilisateur)) {
            throw new IllegalArgumentException("L'image ne correspond pas à l'utilisateur spécifié");
        }
        utilisateur.setUtilisateurImage(null);
        utilisateurImageRepository.delete(image);
        utilisateurRepository.save(utilisateur);
    }
}
