package com.gdu.wacdo.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FonctionDTO {

  private Long id;

  @NotBlank(message = "L'intitule est obligatoire")
  private String intitulePoste;
}
