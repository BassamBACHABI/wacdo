package com.gdu.wacdo.service;

import com.gdu.wacdo.dtos.AffectationFormDTO;
import com.gdu.wacdo.entites.Affectation;
import com.gdu.wacdo.repository.AffectationRepository;
import com.gdu.wacdo.repository.CollaborateurRepository;
import com.gdu.wacdo.repository.FonctionRepository;
import com.gdu.wacdo.repository.RestaurantRepository;
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

  public void create(AffectationFormDTO form) {
    Affectation affectation = new Affectation();
    affectation.setCollaborateur(collaborateurRepository.getReferenceById(form.getCollaborateurId()));
    affectation.setRestaurant(restaurantRepository.getReferenceById(form.getRestaurantId()));
    affectation.setFonction(fonctionRepository.getReferenceById(form.getFonctionId()));
    affectation.setDateDebut(form.getDateDebut());
    affectation.setDateFin(form.getDateFin());
    this.affectationRepository.save(affectation);
  }

  public void update(Affectation affectation) {
    Affectation existant = this.affectationRepository.findById(affectation.getId()).orElseThrow();
    existant.setDateDebut(affectation.getDateDebut());
    existant.setDateFin(affectation.getDateFin());
    existant.setCollaborateur(affectation.getCollaborateur());
    existant.setFonction(affectation.getFonction());
    existant.setRestaurant(affectation.getRestaurant());
    this.affectationRepository.save(existant);
  }

  public List<Affectation> findAll() {
    log.info("mes affectations : {}", this.affectationRepository.findAll().getFirst().getCollaborateur().getEmail());
    return this.affectationRepository.findAll();
  }

  public Affectation findById(Long id) {
    return this.affectationRepository.findById(id).orElse(null);
  }

  public void deleteById(Long id) {
    this.affectationRepository.deleteById(id);
  }
}
