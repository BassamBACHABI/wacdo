package com.gdu.wacdo.repository;


import com.gdu.wacdo.entites.Collaborateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CollaborateurRepository extends JpaRepository<Collaborateur, Long> {
  Optional<Collaborateur> findByEmail(String email);
}
