package com.feastfreedom.repository;

import com.feastfreedom.entity.Kitchen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface KitchenRepository extends JpaRepository<Kitchen, Long> {
    @Query("SELECT k FROM Kitchen k WHERE k.id = :id")
    Kitchen findByIdFromDatabase(Long id);
}
