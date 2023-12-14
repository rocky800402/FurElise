package com.furelise.plan.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer>{
	@Query("SELECT DISTINCT planName, planPrice FROM Plan WHERE times = 1")
	List<Object[]> findDistinctPlanNamesAndPrice();
}
