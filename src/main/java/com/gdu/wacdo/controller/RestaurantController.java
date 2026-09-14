package com.gdu.wacdo.controller;

import com.gdu.wacdo.entites.Restaurant;
import com.gdu.wacdo.service.RestaurantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RestaurantController {

  final RestaurantService restaurantService;

  public RestaurantController(RestaurantService restaurantService) {
    this.restaurantService = restaurantService;
  }

  @GetMapping("/restaurants")
  public String getRestaurants(Model model) {
    List<Restaurant> restaurants = restaurantService.getAll();

    model.addAttribute("restaurants", restaurants);
    return "Restaurant/liste";
  }
}