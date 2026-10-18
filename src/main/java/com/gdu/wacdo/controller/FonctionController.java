package com.gdu.wacdo.controller;

import com.gdu.wacdo.dtos.FonctionDTO;
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
    List<FonctionDTO> fonctions = this.fonctionService.findAll();
    model.addAttribute("fonctions", fonctions);
    return "Fonction/liste";
  }

  @GetMapping("/create")
  public String createForm(Model model){
    model.addAttribute("fonction", new FonctionDTO());
    return "Fonction/create";
  }

  @PostMapping("/create")
  public String create(@ModelAttribute FonctionDTO fonction) {
    this.fonctionService.create(fonction);
    return "redirect:/fonction";
  }

  @GetMapping("/update/{id}")
  public String updateForm(@PathVariable Long id, Model model){
    FonctionDTO fonction = this.fonctionService.findById(id);
    model.addAttribute("fonction", fonction);
    return "Fonction/update";
  }

  @PostMapping("/update")
  public String update(@ModelAttribute FonctionDTO fonction) {
    this.fonctionService.update(fonction);
    return "redirect:/fonction";
  }

  @PostMapping("/delete/{id}")
  public String delete(@PathVariable Long id) {
    this.fonctionService.deleteById(id);
    return "redirect:/fonction";
  }
}
