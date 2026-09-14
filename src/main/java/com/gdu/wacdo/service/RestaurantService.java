package com.gdu.wacdo.service;

import com.gdu.wacdo.entites.Restaurant;
import com.gdu.wacdo.repository.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RestaurantService {

  final RestaurantRepository restaurantRepository;


  public RestaurantService(RestaurantRepository restaurantRepository) {
    this.restaurantRepository = restaurantRepository;
  }

  public List<Restaurant> getAll() {

    log.info(restaurantRepository.findAll().toString());
    return restaurantRepository.findAll();
  }
}
