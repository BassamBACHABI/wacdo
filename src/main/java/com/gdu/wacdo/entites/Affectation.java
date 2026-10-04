package com.gdu.wacdo.entites;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
public class Affectation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private LocalDate dateDebut;
  private LocalDate dateFin;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "restaurant_id")
  private Restaurant restaurant;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "collaborateur_id")
  private Collaborateur collaborateur;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "fonction_id")
  private Fonction fonction;
}
