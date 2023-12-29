package com.furelise.pickuptime.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PickupTimeRepository extends JpaRepository<PickupTime, Integer>{

	boolean existsByTimeRange(String timeRange);
}
