package com.furelise.planord.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.furelise.planord.model.*;

@Repository
public interface PlanOrdRepository extends JpaRepository<PlanOrd, Integer>{
	List<PlanOrd> findByMemID(Integer memID);
}
