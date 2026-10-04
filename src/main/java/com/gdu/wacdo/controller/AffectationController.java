package com.gdu.wacdo.controller;


import com.gdu.wacdo.entites.Affectation;
import com.gdu.wacdo.service.AffectationService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Data
@RequestMapping("/affectation")
public class AffectationController {

  @Autowired
    private AffectationService affectationService;

  @GetMapping
  public String get(Model model){
    List<Affectation> affectations = this.affectationService.findAll();
    model.addAttribute("affectations", affectations);
    return "Affectation/liste";
  }

  @GetMapping("/create")
  public String createForm(Model model){
    model.addAttribute("affectation", new Affectation());
    return "Affectation/create";
  }

  @PostMapping("/create")
  public String create(@ModelAttribute Affectation affectation) {
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
