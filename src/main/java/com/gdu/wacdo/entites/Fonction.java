package com.gdu.wacdo.entites;

import jakarta.persistence.*;

@Entity
@Table(name = "Fonction")
public class Fonction {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String intitulePoste;

  public Fonction(Long id, String intitulePoste) {
    this.id = id;
    this.intitulePoste = intitulePoste;
  }

  public Fonction() {

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getIntitulePoste() {
    return intitulePoste;
  }

  public void setIntitulePoste(String intitulePoste) {
    this.intitulePoste = intitulePoste;
  }
}
