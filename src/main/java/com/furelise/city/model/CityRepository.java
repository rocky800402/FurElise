package com.furelise.city.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

    City findByCityCode(String cityCode);
}