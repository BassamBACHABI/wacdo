package com.gdu.wacdo.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Data
public class CollaborateurFormDTO {

  private Long id;

  @NotBlank(message = "Le nom est obligatoire")
  private String nom;

  @NotBlank(message = "Le prénom est obligatoire")
  private String prenom;

  @NotBlank(message = "L''email est obligatoire")
  @Email(message = "L''email n''est pas valide")
  private String email;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate datePremiereEmbauche;

  private Boolean admin;

  @Size(min = 8, message = "Le mot de passe doit faire au moins 8 caractères")
  private String password;
}