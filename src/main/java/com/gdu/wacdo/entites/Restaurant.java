package com.gdu.wacdo.entites;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Restaurant {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nom;
  private String adresse;
  private int codePostal;
  private String ville;

  @OneToMany(mappedBy = "restaurant")
  List<Affectation> affectations = new ArrayList<>();
}
