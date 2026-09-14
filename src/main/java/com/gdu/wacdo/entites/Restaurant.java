package com.gdu.wacdo.entites;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "Restaurant")
public class Restaurant {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nom;
  private String adresse;
  private int codePostal;
  private String ville;

  @OneToMany(mappedBy = "restaurant_id")
  List<Affectation> affectations;
}
