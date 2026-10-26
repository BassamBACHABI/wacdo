package com.gdu.wacdo.config;

import com.gdu.wacdo.dtos.CollaborateurFormDTO;
import com.gdu.wacdo.dtos.AffectationDetailDTO;
import com.gdu.wacdo.entites.Affectation;
import com.gdu.wacdo.entites.Collaborateur;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();

    modelMapper.createTypeMap(CollaborateurFormDTO.class, Collaborateur.class)
      .addMappings(mapping -> {
        mapping.skip(Collaborateur::setId);
        mapping.skip(Collaborateur::setPassword);
        mapping.skip(Collaborateur::setAffectations);
      });

    modelMapper.createTypeMap(Collaborateur.class, CollaborateurFormDTO.class)
      .addMappings(mapping -> mapping.skip(CollaborateurFormDTO::setPassword));

    modelMapper.createTypeMap(Affectation.class, AffectationDetailDTO.class)
      .addMappings(mapping -> {
        mapping.map(source -> source.getRestaurant().getNom(), AffectationDetailDTO::setRestaurantNom);
        mapping.map(source -> source.getFonction().getIntitulePoste(), AffectationDetailDTO::setFonctionIntitule);
      });

    return modelMapper;
  }
}
