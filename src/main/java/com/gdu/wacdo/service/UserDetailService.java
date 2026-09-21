package com.gdu.wacdo.service;

import com.gdu.wacdo.entites.Collaborateur;
import com.gdu.wacdo.repository.CollaborateurRepository;
import lombok.Data;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Data
public class UserDetailService implements UserDetailsService {
  private final CollaborateurRepository collaborateurRepository;

  @Override
  public UserDetails loadUserByUsername(@NonNull String email) throws UsernameNotFoundException {
    Collaborateur collaborateur = collaborateurRepository.findByEmail(email)
      .orElseThrow(() -> new UsernameNotFoundException("Collaborateur n'existe pas : "+email));

    return User.withUsername(collaborateur.getEmail())
      .password(collaborateur.getPassword())
      .roles(Boolean.TRUE.equals(collaborateur.getAdmin()) ? "ADMIN" : "USER")
      .build();
  }
}
