package com.gdu.wacdo.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AffectationDetailDTO {
  private Long id;
  private LocalDate dateDebut;
  private LocalDate dateFin;
  private String restaurantNom;
  private String fonctionIntitule;
}
