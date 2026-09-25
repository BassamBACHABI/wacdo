package com.gdu.wacdo.service;

import com.gdu.wacdo.entites.Collaborateur;
import com.gdu.wacdo.repository.CollaborateurRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Data
public class CollaborateurService {

  private final CollaborateurRepository collaborateurRepository;
  private final PasswordEncoder passwordEncoder;

  public void create(Collaborateur collaborateur) {
    collaborateur.setPassword(passwordEncoder.encode(collaborateur.getPassword()));
    this.collaborateurRepository.save(collaborateur);
  }

  public void update(Collaborateur collaborateur) {
    Collaborateur existant = this.collaborateurRepository.findById(collaborateur.getId()).orElseThrow();
    existant.setNom(collaborateur.getNom());
    existant.setPrenom(collaborateur.getPrenom());
    existant.setEmail(collaborateur.getEmail());
    existant.setDatePremiereEmbauche(collaborateur.getDatePremiereEmbauche());
    existant.setAdmin(collaborateur.getAdmin());
    this.collaborateurRepository.save(existant);
  }

  public List<Collaborateur> findAll() {
    return this.collaborateurRepository.findAll();
  }

  public Collaborateur findById(Long id) {
    return this.collaborateurRepository.findById(id).orElse(null);
  }

}
