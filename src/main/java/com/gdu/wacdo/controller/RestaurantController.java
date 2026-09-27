package com.gdu.wacdo.controller;

import com.gdu.wacdo.entites.Collaborateur;
import com.gdu.wacdo.entites.Restaurant;
import com.gdu.wacdo.service.RestaurantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/restaurants")
public class RestaurantController {

  final RestaurantService restaurantService;

  public RestaurantController(RestaurantService restaurantService) {
    this.restaurantService = restaurantService;
  }

  @GetMapping
  public String getRestaurants(Model model) {
    List<Restaurant> restaurants = restaurantService.getAll();
    model.addAttribute("restaurants", restaurants);
    return "Restaurant/liste";
  }

  @GetMapping("/create")
  public String addRestaurant(Model model) {
    Restaurant restaurant = new Restaurant();
    model.addAttribute("restaurant", restaurant);
    return "Restaurant/create";
  }

  @PostMapping("/create")
  public String addRestaurant(Restaurant restaurant) {
    this.restaurantService.saveRestaurant(restaurant);
    return "redirect:/restaurants";
  }

  @GetMapping("/update/{id}")
  public String updateForm(@PathVariable Long id, Model model){
    Restaurant restaurant = this.restaurantService.getRestaurantById(id);
    model.addAttribute("restaurant", restaurant);
    return "Restaurant/update";
  }

  @PostMapping("/update")
  public String update(@ModelAttribute Restaurant restaurant) {
    this.restaurantService.updateRestaurant(restaurant);
    return "redirect:/restaurants";
  }

  @PostMapping("/delete/{id}")
  public String delete(@PathVariable Long id) {
    this.restaurantService.deleteRestaurant(id);
    return "redirect:/restaurants";
  }
}