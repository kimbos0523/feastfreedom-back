package com.feastfreedom.repository;

import com.feastfreedom.entity.SecurityCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityCheckRepository extends JpaRepository<SecurityCheck, Long> {
}
