package com.gdu.wacdo.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class AffectationFormDTO {

  @NotNull(message = "Le collaborateur est obligatoire")
  private Long collaborateurId;

  @NotNull(message = "Le restaurant est obligatoire")
  private Long restaurantId;

  @NotNull(message = "La fonction est obligatoire")
  private Long fonctionId;

  @NotNull(message = "La date de début est obligatoire")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate dateDebut;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate dateFin;
}
