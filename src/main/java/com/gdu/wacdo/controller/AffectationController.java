package com.gdu.wacdo.controller;


import com.gdu.wacdo.dtos.AffectationFormDTO;
import com.gdu.wacdo.dtos.CollaborateurFormDTO;
import com.gdu.wacdo.entites.Affectation;
import com.gdu.wacdo.service.AffectationService;
import com.gdu.wacdo.service.CollaborateurService;
import com.gdu.wacdo.service.FonctionService;
import com.gdu.wacdo.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Data
@RequestMapping("/affectation")
public class AffectationController {

  @Autowired
  private final AffectationService affectationService;
  private final CollaborateurService collaborateurService;
  private final RestaurantService restaurantService;
  private final FonctionService fonctionService;


  @GetMapping
  public String get(Model model){
    List<Affectation> affectations = this.affectationService.findAll();
    model.addAttribute("affectations", affectations);
    return "Affectation/liste";
  }

  @GetMapping("/create")
  public String createForm(Model model){
    model.addAttribute("affectation", new AffectationFormDTO());
    model.addAttribute("collaborateurs", this.collaborateurService.findAll());
    model.addAttribute("restaurants", this.restaurantService.getAll());
    model.addAttribute("fonctions", this.fonctionService.findAll());
    return "Affectation/create";
  }

  @PostMapping("/create")
  public String create(@Valid @ModelAttribute("affectation") AffectationFormDTO affectation,
                       BindingResult result) {
    if (result.hasErrors()) {
      return "Collaborateur/create";
    }
    this.affectationService.create(affectation);
    return "redirect:/affectation";
  }

  @GetMapping("/update/{id}")
  public String updateForm(@PathVariable Long id, Model model){
    Affectation affectation = this.affectationService.findById(id);
    model.addAttribute("affectation", affectation);
    return "Affectation/update";
  }

  @PostMapping("/update")
  public String update(@ModelAttribute Affectation affectation) {
    this.affectationService.update(affectation);
    return "redirect:/affectation";
  }

  @PostMapping("/delete/{id}")
  public String delete(@PathVariable Long id) {
    this.affectationService.deleteById(id);
    return "redirect:/affectation";
  }
}
