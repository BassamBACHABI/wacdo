package com.gdu.wacdo.service;

import com.gdu.wacdo.entites.Fonction;
import com.gdu.wacdo.repository.FonctionRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Data
public class FonctionService {

  private final FonctionRepository fonctionRepository;

  public void create(Fonction fonction) {
    this.fonctionRepository.save(fonction);
  }

  public void update(Fonction fonction) {
    Fonction existant = this.fonctionRepository.findById(fonction.getId()).orElseThrow();
    existant.setIntitulePoste(fonction.getIntitulePoste());
    this.fonctionRepository.save(existant);
  }

  public List<Fonction> findAll() {
    return this.fonctionRepository.findAll();
  }

  public Fonction findById(Long id) {
    return this.fonctionRepository.findById(id).orElse(null);
  }

  public void deleteById(Long id) {
    this.fonctionRepository.deleteById(id);
  }

}
