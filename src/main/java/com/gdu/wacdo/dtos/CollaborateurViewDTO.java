package com.gdu.wacdo.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CollaborateurViewDTO {

  private Long id;
  private String nom;
  private String prenom;
  private String email;
  private LocalDate datePremiereEmbauche;
  private Boolean admin;
}