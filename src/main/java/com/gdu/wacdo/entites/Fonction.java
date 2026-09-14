package com.gdu.wacdo.entites;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Fonction {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String intitulePoste;

  @OneToMany(mappedBy = "fonction")
  List<Affectation> affectations = new ArrayList<>();
}
