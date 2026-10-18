package com.gdu.wacdo.service;

import com.gdu.wacdo.dtos.AffectationDTO;
import com.gdu.wacdo.entites.Affectation;
import com.gdu.wacdo.repository.AffectationRepository;
import com.gdu.wacdo.repository.CollaborateurRepository;
import com.gdu.wacdo.repository.FonctionRepository;
import com.gdu.wacdo.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Data
public class AffectationService {

  private final AffectationRepository affectationRepository;
  private final CollaborateurRepository collaborateurRepository;
  private final RestaurantRepository restaurantRepository;
  private final FonctionRepository  fonctionRepository;

  public void create(AffectationDTO form) {
    Affectation affectation = new Affectation();
    affectation.setCollaborateur(collaborateurRepository.getReferenceById(form.getCollaborateurId()));
    affectation.setRestaurant(restaurantRepository.getReferenceById(form.getRestaurantId()));
    affectation.setFonction(fonctionRepository.getReferenceById(form.getFonctionId()));
    affectation.setDateDebut(form.getDateDebut());
    affectation.setDateFin(form.getDateFin());
    this.affectationRepository.save(affectation);
  }

  @Transactional
  public void update(AffectationDTO affectation) {
    Affectation existant = this.affectationRepository.findById(affectation.getId()).orElseThrow();
    existant.setDateDebut(affectation.getDateDebut());
    existant.setDateFin(affectation.getDateFin());
    existant.setCollaborateur(collaborateurRepository.getReferenceById(affectation.getCollaborateurId()));
    existant.setFonction(fonctionRepository.getReferenceById(affectation.getFonctionId()));
    existant.setRestaurant(restaurantRepository.getReferenceById(affectation.getRestaurantId()));
  }

  public List<Affectation> findAll() {
    return this.affectationRepository.findAll();
  }

  public Affectation findById(Long id) {
    return this.affectationRepository.findById(id).orElse(null);
  }

  public void deleteById(Long id) {
    this.affectationRepository.deleteById(id);
  }
}
