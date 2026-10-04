package com.gdu.wacdo.controller;

import com.gdu.wacdo.entites.Affectation;
import com.gdu.wacdo.entites.Fonction;
import com.gdu.wacdo.service.FonctionService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Data
@RequestMapping("/fonction")
public class FonctionController {
  @Autowired
    private FonctionService fonctionService;

  @GetMapping
  public String get(Model model){
    List<Fonction> fonctions = this.fonctionService.findAll();
    model.addAttribute("fonctions", fonctions);
    return "Fonction/liste";
  }

  @GetMapping("/create")
  public String createForm(Model model){
    model.addAttribute("fonction", new Fonction());
    return "Fonction/create";
  }

  @PostMapping("/create")
  public String create(@ModelAttribute Fonction fonction) {
    this.fonctionService.create(fonction);
    return "redirect:/fonction";
  }

  @GetMapping("/update/{id}")
  public String updateForm(@PathVariable Long id, Model model){
    Fonction fonction = this.fonctionService.findById(id);
    model.addAttribute("fonction", fonction);
    return "Fonction/update";
  }

  @PostMapping("/update")
  public String update(@ModelAttribute Fonction fonction) {
    this.fonctionService.update(fonction);
    return "redirect:/fonction";
  }

  @PostMapping("/delete/{id}")
  public String delete(@PathVariable Long id) {
    this.fonctionService.deleteById(id);
    return "redirect:/fonction";
  }
}
