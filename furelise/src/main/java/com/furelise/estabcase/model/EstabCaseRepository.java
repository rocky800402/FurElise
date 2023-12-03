package com.furelise.estabcase.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstabCaseRepository extends JpaRepository<EstabCase, Integer> {
    
}