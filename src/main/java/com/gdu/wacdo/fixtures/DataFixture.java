package com.gdu.wacdo.fixtures;

import com.gdu.wacdo.entites.Affectation;
import com.gdu.wacdo.entites.Collaborateur;
import com.gdu.wacdo.entites.Fonction;
import com.gdu.wacdo.entites.Restaurant;
import com.gdu.wacdo.repository.AffectationRepository;
import com.gdu.wacdo.repository.CollaborateurRepository;
import com.gdu.wacdo.repository.FonctionRepository;
import com.gdu.wacdo.repository.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Jeu de données de test, inséré au démarrage lorsque la base est vide.
 * Sert à disposer de restaurants, collaborateurs et affectations pour tester l'application.
 */
@Slf4j
@Component
@Profile("dev")
public class DataFixture implements CommandLineRunner {

  /** Mot de passe attribué à tous les collaborateurs du jeu de test. */
  private static final String MOT_DE_PASSE = "password";

  private final RestaurantRepository restaurantRepository;
  private final CollaborateurRepository collaborateurRepository;
  private final FonctionRepository fonctionRepository;
  private final AffectationRepository affectationRepository;
  private final PasswordEncoder passwordEncoder;

  public DataFixture(RestaurantRepository restaurantRepository,
                     CollaborateurRepository collaborateurRepository,
                     FonctionRepository fonctionRepository,
                     AffectationRepository affectationRepository,
                     PasswordEncoder passwordEncoder) {
    this.restaurantRepository = restaurantRepository;
    this.collaborateurRepository = collaborateurRepository;
    this.fonctionRepository = fonctionRepository;
    this.affectationRepository = affectationRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public void run(String @NonNull ... args) {
    if (collaborateurRepository.count() > 0) {
      log.info("Jeu de test déjà présent, aucune insertion.");
      return;
    }

    log.info("Insertion du jeu de test...");

    // Restaurants
    Restaurant bordeaux = restaurant("Wacdo Bordeaux Lac", "12 avenue des Pins", 33300, "Bordeaux");
    Restaurant merignac = restaurant("Wacdo Mérignac", "8 rue de l'Aéroport", 33700, "Mérignac");
    Restaurant pessac = restaurant("Wacdo Pessac Centre", "45 cours de la Libération", 33600, "Pessac");

    // Fonctions
    Fonction directeur = fonction("Directeur de restaurant");
    Fonction manager = fonction("Manager");
    Fonction equipier = fonction("Équipier polyvalent");
    Fonction cuisinier = fonction("Cuisinier");

    // Collaborateurs (mot de passe : password)
    Collaborateur admin = collaborateur("Martin", "Sophie", "admin@wacdo.fr",
      LocalDate.of(2015, 3, 2), true);
    Collaborateur dubois = collaborateur("Dubois", "Karim", "karim.dubois@wacdo.fr",
      LocalDate.of(2018, 9, 17), false);
    Collaborateur leroy = collaborateur("Leroy", "Emma", "emma.leroy@wacdo.fr",
      LocalDate.of(2021, 1, 11), false);
    Collaborateur nguyen = collaborateur("Nguyen", "Tien", "tien.nguyen@wacdo.fr",
      LocalDate.of(2023, 6, 5), false);

    // Affectations terminées
    affectation(dubois, bordeaux, equipier, LocalDate.of(2018, 9, 17), LocalDate.of(2020, 8, 31));
    affectation(leroy, merignac, equipier, LocalDate.of(2021, 1, 11), LocalDate.of(2022, 12, 31));

    // Affectations en cours (dateFin à null)
    affectation(admin, bordeaux, directeur, LocalDate.of(2015, 3, 2), null);
    affectation(dubois, merignac, manager, LocalDate.of(2020, 9, 1), null);
    affectation(leroy, pessac, manager, LocalDate.of(2023, 1, 1), null);
    affectation(nguyen, bordeaux, cuisinier, LocalDate.of(2023, 6, 5), null);

    log.info("Jeu de test inséré : {} restaurants, {} fonctions, {} collaborateurs, {} affectations.",
      restaurantRepository.count(), fonctionRepository.count(),
      collaborateurRepository.count(), affectationRepository.count());
    log.info("Connexion administrateur : {} / {}", admin.getEmail(), MOT_DE_PASSE);
  }

  private Restaurant restaurant(String nom, String adresse, int codePostal, String ville) {
    Restaurant restaurant = new Restaurant();
    restaurant.setNom(nom);
    restaurant.setAdresse(adresse);
    restaurant.setCodePostal(codePostal);
    restaurant.setVille(ville);
    return restaurantRepository.save(restaurant);
  }

  private Fonction fonction(String intitulePoste) {
    Fonction fonction = new Fonction();
    fonction.setIntitulePoste(intitulePoste);
    return fonctionRepository.save(fonction);
  }

  private Collaborateur collaborateur(String nom, String prenom, String email,
                                      LocalDate datePremiereEmbauche, boolean admin) {
    Collaborateur collaborateur = new Collaborateur();
    collaborateur.setNom(nom);
    collaborateur.setPrenom(prenom);
    collaborateur.setEmail(email);
    collaborateur.setDatePremiereEmbauche(datePremiereEmbauche);
    collaborateur.setAdmin(admin);
    collaborateur.setPassword(passwordEncoder.encode(MOT_DE_PASSE));
    return collaborateurRepository.save(collaborateur);
  }

  private Affectation affectation(Collaborateur collaborateur, Restaurant restaurant, Fonction fonction,
                                  LocalDate dateDebut, LocalDate dateFin) {
    Affectation affectation = new Affectation();
    affectation.setCollaborateur(collaborateur);
    affectation.setRestaurant(restaurant);
    affectation.setFonction(fonction);
    affectation.setDateDebut(enDate(dateDebut));
    affectation.setDateFin(enDate(dateFin));
    return affectationRepository.save(affectation);
  }

  private Date enDate(LocalDate date) {
    return date == null ? null : Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
  }
}