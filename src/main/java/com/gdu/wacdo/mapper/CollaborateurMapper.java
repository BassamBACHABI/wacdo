package com.gdu.wacdo.mapper;

import com.gdu.wacdo.dtos.CollaborateurFormDTO;
import com.gdu.wacdo.dtos.CollaborateurViewDTO;
import com.gdu.wacdo.entites.Collaborateur;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class CollaborateurMapper {

  private final ModelMapper modelMapper;

  public CollaborateurViewDTO toView(Collaborateur collaborateur) {
    return this.modelMapper.map(collaborateur, CollaborateurViewDTO.class);
  }

  public List<CollaborateurViewDTO> toViewList(List<Collaborateur> collaborateurs) {
    return collaborateurs.stream().map(this::toView).toList();
  }

  public CollaborateurFormDTO toForm(Collaborateur collaborateur) {
    return this.modelMapper.map(collaborateur, CollaborateurFormDTO.class);
  }

  public Collaborateur toEntity(CollaborateurFormDTO form) {
    return this.modelMapper.map(form, Collaborateur.class);
  }

  public void copyToEntity(CollaborateurFormDTO form, Collaborateur cible) {
    this.modelMapper.map(form, cible);
  }
}