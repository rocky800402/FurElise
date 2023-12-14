package com.furelise.city.model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {

	@Autowired
	CityRepository dao;

	public City addCity(City city) {
		return dao.save(city);
	}

	public City updateCity(City city) {
		
		return dao.save(city);
	}

	public void delete(Integer cityID) {
		dao.deleteById(cityID);
	}

	public List<City> getAllCity() {
		return dao.findAll();
	}

	public City getCityById(Integer cityID) {
		Optional<City> optional = dao.findById(cityID);
		return optional.orElse(null);
	}

}
