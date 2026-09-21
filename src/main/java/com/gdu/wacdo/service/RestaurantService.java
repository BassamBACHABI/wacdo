package com.gdu.wacdo.service;

import com.gdu.wacdo.entites.Restaurant;
import com.gdu.wacdo.repository.RestaurantRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Data
public class RestaurantService {

  private final RestaurantRepository restaurantRepository;

  public List<Restaurant> getAll() {
    return restaurantRepository.findAll();
  }
}
