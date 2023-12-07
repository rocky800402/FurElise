package com.furelise.planstatus.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanStatusRepository extends JpaRepository<PlanStatus, Integer>{

}
