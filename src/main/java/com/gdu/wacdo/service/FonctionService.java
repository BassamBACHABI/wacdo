package com.gdu.wacdo.service;

import com.gdu.wacdo.dtos.FonctionDTO;
import com.gdu.wacdo.entites.Fonction;
import com.gdu.wacdo.repository.FonctionRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Data
public class FonctionService {

  private final FonctionRepository fonctionRepository;
  private final ModelMapper modelMapper;

  public void create(FonctionDTO fonction) {
    Fonction fonctionEntity = new Fonction();
    fonctionEntity.setIntitulePoste(fonction.getIntitulePoste());
    this.fonctionRepository.save(fonctionEntity);
  }

  public void update(FonctionDTO fonction) {
    Fonction existant = this.fonctionRepository.findById(fonction.getId()).orElseThrow();
    existant.setIntitulePoste(fonction.getIntitulePoste());
    this.fonctionRepository.save(existant);
  }

  public List<FonctionDTO> findAll() {
    return this.fonctionRepository.findAll().stream()
      .map(fonction -> this.modelMapper.map(fonction, FonctionDTO.class))
      .toList();
  }

  public FonctionDTO findById(Long id) {
    return this.modelMapper.map(this.fonctionRepository.findById(id), FonctionDTO.class);
  }

  public void deleteById(Long id) {
    this.fonctionRepository.deleteById(id);
  }

}
