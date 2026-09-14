package com.gdu.wacdo.entites;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Affectation")
public class Affectation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Date dateDebut;
  private Date dateFin;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "restaurant_id")
  private Restaurant restaurant;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "collaborateur_id")
  private Collaborateur collaborateur;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "fonction_id")
  private Fonction fonction;

  public Affectation(Long id, Date dateDebut, Date dateFin, Restaurant restaurant, Collaborateur collaborateur, Fonction fonction) {
    this.id = id;
    this.dateDebut = dateDebut;
    this.dateFin = dateFin;
    this.restaurant = restaurant;
    this.collaborateur = collaborateur;
    this.fonction = fonction;
  }

  public Affectation() {

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getDateDebut() {
    return dateDebut;
  }

  public void setDateDebut(Date dateDebut) {
    this.dateDebut = dateDebut;
  }

  public Date getDateFin() {
    return dateFin;
  }

  public void setDateFin(Date dateFin) {
    this.dateFin = dateFin;
  }

  public Restaurant getRestaurant() {
    return restaurant;
  }

  public void setRestaurant(Restaurant restaurant) {
    this.restaurant = restaurant;
  }

  public Collaborateur getCollaborateur() {
    return collaborateur;
  }

  public void setCollaborateur(Collaborateur collaborateur) {
    this.collaborateur = collaborateur;
  }

  public Fonction getFonction() {
    return fonction;
  }

  public void setFonction(Fonction fonction) {
    this.fonction = fonction;
  }
}
