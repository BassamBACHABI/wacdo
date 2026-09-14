package com.gdu.wacdo.entites;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Collaborateur")
public class Collaborateur {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nom;
  private String prenom;
  private String email;
  private Date datePremiereEmbauche;
  private Boolean admin;
  private String password;

  public Collaborateur(Long id, String nom, String prenom, String email, Date datePremiereEmbauche, Boolean admin, String password) {
    this.id = id;
    this.nom = nom;
    this.prenom = prenom;
    this.email = email;
    this.datePremiereEmbauche = datePremiereEmbauche;
    this.admin = admin;
    this.password = password;
  }

  public Collaborateur() {

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public String getPrenom() {
    return prenom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Date getDatePremiereEmbauche() {
    return datePremiereEmbauche;
  }

  public void setDatePremiereEmbauche(Date datePremiereEmbauche) {
    this.datePremiereEmbauche = datePremiereEmbauche;
  }

  public Boolean getAdmin() {
    return admin;
  }

  public void setAdmin(Boolean admin) {
    this.admin = admin;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
