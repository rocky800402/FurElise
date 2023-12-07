package com.furelise.estabcase.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EstabCaseRepository extends JpaRepository<EstabCase, Integer> {
	
	@Query("SELECT planOrdID FROM EstabCase")
	List<EstabCase> findByPlanOrdID(Integer planOrdID);
}