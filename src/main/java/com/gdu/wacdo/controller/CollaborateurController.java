package com.gdu.wacdo.controller;

import com.gdu.wacdo.dtos.CollaborateurFormDTO;
import com.gdu.wacdo.dtos.CollaborateurViewDTO;
import com.gdu.wacdo.service.CollaborateurService;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Data
@RequestMapping("/collaborateurs")
public class CollaborateurController {

  private final CollaborateurService collaborateurService;

  @GetMapping
  public String getCollaborateur(Model model){
    List<CollaborateurViewDTO> collaborateurs = this.collaborateurService.findAll();
    model.addAttribute("collaborateurs", collaborateurs);
    return "Collaborateur/liste";
  }

  @GetMapping("/create")
  public String createForm(Model model){
    model.addAttribute("collaborateur", new CollaborateurFormDTO());
    return "Collaborateur/create";
  }

  @PostMapping("/create")
  public String create(@Valid @ModelAttribute("collaborateur") CollaborateurFormDTO collaborateur,
                       BindingResult result) {
    if (!StringUtils.hasText(collaborateur.getPassword())) {
      result.rejectValue("password", "NotBlank", "Le mot de passe est obligatoire");
    }
    if (result.hasErrors()) {
      return "Collaborateur/create";
    }
    this.collaborateurService.create(collaborateur);
    return "redirect:/collaborateurs";
  }

  @GetMapping("/update/{id}")
  public String updateForm(@PathVariable Long id, Model model){
    model.addAttribute("collaborateur", this.collaborateurService.findFormById(id));
    return "Collaborateur/update";
  }

  @PostMapping("/update")
  public String update(@Valid @ModelAttribute("collaborateur") CollaborateurFormDTO collaborateur,
                       BindingResult result) {
    if (result.hasErrors()) {
      return "Collaborateur/update";
    }
    this.collaborateurService.update(collaborateur);
    return "redirect:/collaborateurs";
  }

  @PostMapping("/delete/{id}")
  public String delete(@PathVariable Long id) {
    this.collaborateurService.deleteById(id);
    return "redirect:/collaborateurs";
  }
}