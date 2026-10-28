package com.gdu.wacdo.service;

import com.gdu.wacdo.dtos.CollaborateurFormDTO;
import com.gdu.wacdo.dtos.CollaborateurViewDTO;
import com.gdu.wacdo.entites.Collaborateur;
import com.gdu.wacdo.mapper.CollaborateurMapper;
import com.gdu.wacdo.repository.CollaborateurRepository;
import org.glassfish.jaxb.core.v2.TODO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CollaborateurServiceTest {

  private static LocalDate dateEmbaucheCommune;

  @Mock
  private CollaborateurRepository collaborateurRepository;

  @Mock
  private PasswordEncoder passwordEncoder;

  @Mock
  private CollaborateurMapper collaborateurMapper;

  @InjectMocks
  private CollaborateurService collaborateurService;

  private Collaborateur collaborateur;
  private CollaborateurFormDTO formulaire;

  @BeforeAll
  static void preparerDonneesCommunes() {
    dateEmbaucheCommune = LocalDate.of(2020, 4, 15);
  }

  @BeforeEach
  void preparerCollaborateurEtFormulaire() {
    collaborateur = new Collaborateur();
    collaborateur.setId(12L);
    collaborateur.setNom("Martin");
    collaborateur.setPrenom("Sophie");
    collaborateur.setEmail("sophie.martin@example.fr");
    collaborateur.setDatePremiereEmbauche(dateEmbaucheCommune);
    collaborateur.setAdmin(false);
    collaborateur.setPassword("ancien-hash");

    formulaire = new CollaborateurFormDTO();
    formulaire.setId(12L);
    formulaire.setNom("Martin");
    formulaire.setPrenom("Sophie");
    formulaire.setEmail("sophie.martin@example.fr");
    formulaire.setDatePremiereEmbauche(dateEmbaucheCommune);
    formulaire.setAdmin(false);
  }

  @Test
  void createEncodeLeMotDePasseAvantLaSauvegarde() {
    formulaire.setPassword("motdepasse123");
    when(collaborateurMapper.toEntity(formulaire)).thenReturn(collaborateur);
    when(passwordEncoder.encode("motdepasse123")).thenReturn("hash-encode");

    collaborateurService.create(formulaire);

    assertEquals("hash-encode", collaborateur.getPassword());
    verify(collaborateurRepository).save(collaborateur);
    verify(collaborateurRepository, times(1)).save(collaborateur);
  }

  @Test
  void updateConserveLeMotDePasseQuandLeChampEstVide() {
    formulaire.setPassword("");
    when(collaborateurRepository.findById(12L)).thenReturn(Optional.of(collaborateur));

    collaborateurService.update(formulaire);

    assertEquals("ancien-hash", collaborateur.getPassword());
    verify(collaborateurMapper).copyToEntity(formulaire, collaborateur);
    verifyNoInteractions(passwordEncoder);
    verify(collaborateurRepository).save(collaborateur);
  }

  @Test
  void updateEncodeUnNouveauMotDePasseRenseigne() {
    formulaire.setPassword("nouveau-mdp");
    when(collaborateurRepository.findById(12L)).thenReturn(Optional.of(collaborateur));
    when(passwordEncoder.encode("nouveau-mdp")).thenReturn("nouveau-hash");

    collaborateurService.update(formulaire);

    assertEquals("nouveau-hash", collaborateur.getPassword());
    verify(collaborateurRepository).save(collaborateur);
  }

  @Test
  void rechercherUnCollaborateur() {
    CollaborateurViewDTO vue = new CollaborateurViewDTO();
    List<Collaborateur> entites = List.of(collaborateur);
    List<CollaborateurViewDTO> vues = List.of(vue);
    when(collaborateurRepository.rechercher("sophie", false)).thenReturn(entites);
    when(collaborateurMapper.toViewList(entites)).thenReturn(vues);

    List<CollaborateurViewDTO> resultat = collaborateurService.rechercher("  Sophie  ", false);

    assertSame(vues, resultat);
    verify(collaborateurRepository).rechercher("sophie", false);
  }

  /*TODO
  est-ce qu'un user avec le role user peut accéder à l'app
  est-ce qu'un user non connecté peut accéder à la page principale de l'app
  */
}
