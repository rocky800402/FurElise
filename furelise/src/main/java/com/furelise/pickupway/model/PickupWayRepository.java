package com.furelise.pickupway.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PickupWayRepository extends JpaRepository<PickupWay, Integer>{

}
