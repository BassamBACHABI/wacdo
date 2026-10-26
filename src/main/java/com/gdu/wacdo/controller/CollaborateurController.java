package com.gdu.wacdo.controller;

import com.gdu.wacdo.dtos.CollaborateurFormDTO;
import com.gdu.wacdo.dtos.CollaborateurDetailDTO;
import com.gdu.wacdo.dtos.CollaborateurViewDTO;
import com.gdu.wacdo.service.CollaborateurService;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Controller
@Data
@RequestMapping("/collaborateurs")
public class CollaborateurController {

  private final CollaborateurService collaborateurService;

  @GetMapping
  public String getCollaborateur(@RequestParam(defaultValue = "") String q, Model model){
    List<CollaborateurViewDTO> collaborateurs = this.collaborateurService.rechercher(q, false);
    model.addAttribute("collaborateurs", collaborateurs);
    model.addAttribute("nonAffectes", false);
    model.addAttribute("q", q);
    model.addAttribute("rechercheAction", "/collaborateurs");
    return "Collaborateur/liste";
  }

  @GetMapping("/non-affectes")
  public String getCollaborateursNonAffectes(@RequestParam(defaultValue = "") String q, Model model){
    List<CollaborateurViewDTO> collaborateurs = this.collaborateurService.rechercher(q, true);
    model.addAttribute("collaborateurs", collaborateurs);
    model.addAttribute("nonAffectes", true);
    model.addAttribute("q", q);
    model.addAttribute("rechercheAction", "/collaborateurs/non-affectes");
    return "Collaborateur/liste";
  }

  @GetMapping("/detail/{id}")
  public String detail(@PathVariable Long id,
                       @RequestParam(defaultValue = "") String poste,
                       @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDebut,
                       Model model) {
    CollaborateurDetailDTO collaborateur = this.collaborateurService.findDetailById(id, poste, dateDebut);
    model.addAttribute("collaborateur", collaborateur);
    model.addAttribute("poste", poste);
    model.addAttribute("dateDebut", dateDebut);
    return "Collaborateur/detail";
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
