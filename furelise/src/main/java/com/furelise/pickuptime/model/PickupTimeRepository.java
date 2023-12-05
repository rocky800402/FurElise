package com.furelise.pickuptime.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PickupTimeRepository extends JpaRepository<PickupTime, Integer>{

}
