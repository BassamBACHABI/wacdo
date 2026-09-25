package com.gdu.wacdo.controller;

import com.gdu.wacdo.entites.Collaborateur;
import com.gdu.wacdo.service.CollaborateurService;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Data
@RequestMapping("/collaborateurs")
public class CollaborateurController {

  private final CollaborateurService collaborateurService;

  @GetMapping
  public String getCollaborateur(Model model){
    List<Collaborateur> collaborateurs = this.collaborateurService.findAll();
    model.addAttribute("collaborateurs", collaborateurs);
    return "Collaborateur/liste";
  }

  @GetMapping("/create")
  public String createForm(Model model){
    model.addAttribute("collaborateur", new Collaborateur());
    return "Collaborateur/create";
  }

  @PostMapping("/create")
  public String create(@ModelAttribute Collaborateur collaborateur) {
    this.collaborateurService.create(collaborateur);
    return "redirect:/collaborateurs";
  }

  @GetMapping("/update/{id}")
  public String updateForm(@PathVariable Long id, Model model){
    Collaborateur collaborateur = this.collaborateurService.findById(id);
    model.addAttribute("collaborateur", collaborateur);
    return "Collaborateur/update";
  }

  @PostMapping("/update")
  public String update(@ModelAttribute Collaborateur collaborateur) {
    this.collaborateurService.update(collaborateur);
    return "redirect:/collaborateurs";
  }
}
