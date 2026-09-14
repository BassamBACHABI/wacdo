package com.gdu.wacdo.entites;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Collaborateur {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nom;
  private String prenom;
  private String email;
  private LocalDate datePremiereEmbauche;
  private Boolean admin;
  private String password;

  @OneToMany(mappedBy = "collaborateur")
  List<Affectation> affectations = new ArrayList<>();
}
