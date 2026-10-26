package com.gdu.wacdo.dtos;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CollaborateurDetailDTO {
  private Long id;
  private String nom;
  private String prenom;
  private String email;
  private LocalDate datePremiereEmbauche;
  private Boolean admin;
  private List<AffectationDetailDTO> affectationsEnCours;
  private List<AffectationDetailDTO> historiqueAffectations;
  private List<AffectationDetailDTO> affectationsFutures;
}
