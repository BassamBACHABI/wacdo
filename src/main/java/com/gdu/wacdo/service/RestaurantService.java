package com.gdu.wacdo.service;

import com.gdu.wacdo.entites.Affectation;
import com.gdu.wacdo.entites.Restaurant;
import com.gdu.wacdo.repository.RestaurantRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Slf4j
@Data
public class RestaurantService {

  private final RestaurantRepository restaurantRepository;

  public List<Affectation> collaborateursEnPoste(Long restaurantId, String q) {
    String recherche = q == null ? "" : q.trim();
    LocalDate date = null;
    if (!recherche.isEmpty()) {
      for (DateTimeFormatter formatter : List.of(
          DateTimeFormatter.ofPattern("dd/MM/yyyy"), DateTimeFormatter.ISO_LOCAL_DATE)) {
        try {
          date = LocalDate.parse(recherche, formatter);
          break;
        } catch (java.time.format.DateTimeParseException ignored) {
        }
      }
    }
    return restaurantRepository.findEnCours(restaurantId, LocalDate.now(), recherche, date);
  }

  public List<Restaurant> getAll() {
    return restaurantRepository.findAll();
  }
  public void saveRestaurant(Restaurant restaurant) {
    restaurantRepository.save(restaurant);
  }

  public Restaurant getRestaurantById(Long id) {
    return restaurantRepository.findById(id).orElse(null);
  }

  public void deleteRestaurant(Long id) {
    restaurantRepository.deleteById(id);
  }

  public void updateRestaurant(Restaurant restaurant) {
    restaurantRepository.save(restaurant);
  }

}
