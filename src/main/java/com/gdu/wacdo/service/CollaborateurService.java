package com.gdu.wacdo.service;

import com.gdu.wacdo.dtos.CollaborateurFormDTO;
import com.gdu.wacdo.dtos.CollaborateurViewDTO;
import com.gdu.wacdo.entites.Collaborateur;
import com.gdu.wacdo.mapper.CollaborateurMapper;
import com.gdu.wacdo.repository.CollaborateurRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Slf4j
@Data
public class CollaborateurService {

  private final CollaborateurRepository collaborateurRepository;
  private final PasswordEncoder passwordEncoder;
  private final CollaborateurMapper collaborateurMapper;

  public void create(CollaborateurFormDTO form) {
    Collaborateur collaborateur = this.collaborateurMapper.toEntity(form);
    collaborateur.setPassword(this.passwordEncoder.encode(form.getPassword()));
    this.collaborateurRepository.save(collaborateur);
  }

  public void update(CollaborateurFormDTO form) {
    Collaborateur existant = this.collaborateurRepository.findById(form.getId()).orElseThrow();
    this.collaborateurMapper.copyToEntity(form, existant);

    if (StringUtils.hasText(form.getPassword())) {
      existant.setPassword(this.passwordEncoder.encode(form.getPassword()));
    }

    this.collaborateurRepository.save(existant);
  }

  public List<CollaborateurViewDTO> findAll() {
    return this.collaborateurMapper.toViewList(this.collaborateurRepository.findAll());
  }

  public CollaborateurFormDTO findFormById(Long id) {
    return this.collaborateurMapper.toForm(this.collaborateurRepository.findById(id).orElseThrow());
  }

  public void deleteById(Long id) {
    this.collaborateurRepository.deleteById(id);
  }

}