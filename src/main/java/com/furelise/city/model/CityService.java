package com.furelise.city.model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.furelise.city.model.*;

@Service
public class CityService {

	@Autowired
	CityRepository dao;

	public boolean addCity(City city) {
		//verify if cityCode duplicated
		boolean proceed = false;
		if (!dao.existsByCityCode(city.getCityCode())) {
			dao.save(city);
			proceed = true;
		}
		return proceed;
	}
	
	public boolean updateCity(City city, String oldCityCode) {
//		verify if cityCode duplicated, only accept original or unique one
		boolean proceed = false;
		String newCityCode = city.getCityCode();
		if (newCityCode.equals(oldCityCode) || !dao.existsByCityCode(newCityCode)) {
			dao.save(city);
			proceed = true;
		}
		return proceed;
	}
	
//	public boolean updateCity(City city) {
////		verify if cityCode duplicated //!should only accept original
////		boolean proceed = false;
////		if (!dao.existsByCityCode(city.getCityCode())) {
//		dao.save(city);
////			proceed = true;
////		}
//		return true;
//	}

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
