package com.gdu.wacdo.controller;

import com.gdu.wacdo.entites.Collaborateur;
import com.gdu.wacdo.service.CollaborateurService;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Data
@RequestMapping("/collaborateur")
public class CollaborateurController {

  private final CollaborateurService collaborateurService;

  @PostMapping("/create")
  public void create(@RequestBody Collaborateur collaborateur) {
    this.collaborateurService.create(collaborateur);
  }
}
