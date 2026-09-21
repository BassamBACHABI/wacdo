package com.gdu.wacdo.service;

import com.gdu.wacdo.entites.Collaborateur;
import com.gdu.wacdo.repository.CollaborateurRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

}
