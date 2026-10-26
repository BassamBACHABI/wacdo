package com.gdu.wacdo.service;

import com.gdu.wacdo.dtos.CollaborateurFormDTO;
import com.gdu.wacdo.dtos.AffectationDetailDTO;
import com.gdu.wacdo.dtos.CollaborateurDetailDTO;
import com.gdu.wacdo.dtos.CollaborateurViewDTO;
import com.gdu.wacdo.entites.Collaborateur;
import com.gdu.wacdo.mapper.CollaborateurMapper;
import com.gdu.wacdo.repository.CollaborateurRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

  public List<CollaborateurViewDTO> findNonAffectes() {
    return this.collaborateurMapper.toViewList(this.collaborateurRepository.findByAffectationsIsEmpty());
  }

  public List<CollaborateurViewDTO> rechercher(String recherche, boolean nonAffectes) {
    String terme = recherche == null ? "" : recherche.trim().toLowerCase(Locale.ROOT);
    return this.collaborateurMapper.toViewList(this.collaborateurRepository.rechercher(terme, nonAffectes));
  }

  public CollaborateurDetailDTO findDetailById(Long id, String poste, LocalDate dateDebut) {
    Collaborateur collaborateur = this.collaborateurRepository.findWithAffectationsById(id).orElseThrow();
    CollaborateurDetailDTO detail = this.collaborateurMapper.toDetail(collaborateur);
    detail.setAffectationsEnCours(new ArrayList<>());
    detail.setHistoriqueAffectations(new ArrayList<>());
    detail.setAffectationsFutures(new ArrayList<>());

    LocalDate aujourdHui = LocalDate.now();
    collaborateur.getAffectations().forEach(affectation -> {
      AffectationDetailDTO affectationDetail = this.collaborateurMapper.toAffectationDetail(affectation);
      LocalDate debut = affectation.getDateDebut();
      LocalDate fin = affectation.getDateFin();

      if (debut != null && debut.isAfter(aujourdHui)) {
        detail.getAffectationsFutures().add(affectationDetail);
      } else if (debut != null && (fin == null || !fin.isBefore(aujourdHui))) {
        detail.getAffectationsEnCours().add(affectationDetail);
      } else {
        detail.getHistoriqueAffectations().add(affectationDetail);
      }
    });

    String posteRecherche = poste == null ? "" : poste.trim().toLowerCase(Locale.ROOT);
    detail.setHistoriqueAffectations(detail.getHistoriqueAffectations().stream()
      .filter(affectation -> posteRecherche.isEmpty()
        || (affectation.getFonctionIntitule() != null
          && affectation.getFonctionIntitule().toLowerCase(Locale.ROOT).contains(posteRecherche)))
      .filter(affectation -> dateDebut == null || dateDebut.equals(affectation.getDateDebut()))
      .toList());
    return detail;
  }

  public CollaborateurFormDTO findFormById(Long id) {
    return this.collaborateurMapper.toForm(this.collaborateurRepository.findById(id).orElseThrow());
  }

  public void deleteById(Long id) {
    this.collaborateurRepository.deleteById(id);
  }

}
