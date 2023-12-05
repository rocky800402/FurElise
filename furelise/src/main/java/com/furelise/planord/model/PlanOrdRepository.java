package com.furelise.planord.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanOrdRepository extends JpaRepository<PlanOrd, Integer>{

}
