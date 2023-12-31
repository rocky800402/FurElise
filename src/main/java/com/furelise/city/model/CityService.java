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

	public String addCity(City city) {
		//verify if cityCode/cityName duplicated
		String result = "";
		if (dao.existsByCityCode(city.getCityCode().trim())) {
			result = "duplicated cityCode";
		} else if (dao.existsByCityName(city.getCityName().trim())) {
			result = "duplicated cityName";
		} else {
			dao.save(city);
			result = "added successfully";
		}
		return result;
	}
	
	public String updateCity(City city, String oldCityCode, String oldCityName) {
//		verify if cityCode/cityName duplicated, only accept original or unique one
		String result = "";
		String newCityCode = city.getCityCode().trim();
		String newCityName = city.getCityName().trim();
		if (!newCityCode.equals(oldCityCode) && dao.existsByCityCode(newCityCode)) {
			result = "duplicated cityCode";
		} else if (!newCityName.equals(oldCityName) && dao.existsByCityName(newCityName)) {
			result = "duplicated cityName";
		} else {
			dao.save(city);
			result = "updated successfully";
		}
		return result;
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
