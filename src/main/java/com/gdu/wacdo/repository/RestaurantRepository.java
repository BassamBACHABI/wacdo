package com.gdu.wacdo.repository;

import com.gdu.wacdo.entites.Affectation;
import com.gdu.wacdo.entites.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

  @Query("""
    select a from Affectation a
      join a.collaborateur c
      join a.fonction f
    where a.restaurant.id = :restaurantId
      and a.dateDebut <= :aujourdhui
      and (a.dateFin is null or a.dateFin >= :aujourdhui)
      and (:q = ''
           or lower(c.nom) like lower(concat('%', :q, '%'))
           or lower(c.prenom) like lower(concat('%', :q, '%'))
           or lower(f.intitulePoste) like lower(concat('%', :q, '%'))
           or (:date is not null and a.dateDebut = :date))
    order by c.nom, c.prenom
    """)
  List<Affectation> findEnCours(@Param("restaurantId") Long restaurantId,
                                @Param("aujourdhui") LocalDate aujourdhui,
                                @Param("q") String q,
                                @Param("date") LocalDate date);
}
