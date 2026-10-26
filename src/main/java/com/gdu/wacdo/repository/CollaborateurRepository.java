package com.gdu.wacdo.repository;


import com.gdu.wacdo.entites.Collaborateur;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CollaborateurRepository extends JpaRepository<Collaborateur, Long> {
  Optional<Collaborateur> findByEmail(String email);

  List<Collaborateur> findByAffectationsIsEmpty();

  @Query("""
    select c from Collaborateur c
    where (:recherche = ''
      or lower(c.nom) like concat('%', :recherche, '%')
      or lower(c.prenom) like concat('%', :recherche, '%')
      or lower(c.email) like concat('%', :recherche, '%'))
      and (:nonAffectes = false or c.affectations is empty)
    order by c.nom, c.prenom
    """)
  List<Collaborateur> rechercher(@Param("recherche") String recherche,
                                 @Param("nonAffectes") boolean nonAffectes);

  @EntityGraph(attributePaths = {"affectations", "affectations.restaurant", "affectations.fonction"})
  Optional<Collaborateur> findWithAffectationsById(Long id);
}
