package com.furelise.plan.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer>{

	//PlanOrdController // create planName drop down menu
	List<Plan> findByTimes(Integer times);
	
	@Query("SELECT planID FROM Plan WHERE planName = :planName AND times = :times")
	Integer findIdByPlanNameAndTimes(@Param(value = "planName") String planName, @Param(value = "times") Integer times);

	@Query("SELECT DISTINCT times FROM Plan WHERE planName = :planName")
	List<Integer> findTimeByPlanName(@Param(value="planName") String planName);
	
	@Query("SELECT planID FROM Plan WHERE planName = :planName")
	List<Integer> findIdByPlanName(@Param(value = "planName") String planName);
}
