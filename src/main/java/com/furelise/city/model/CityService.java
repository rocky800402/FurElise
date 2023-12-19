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

	// 新增時cityCode重複的話會停留在原畫面
	public String addCity(City city) {
		String judge = "b_city_create";
		if (dao.existsByCityCode(city.getCityCode())) {
			judge = "b_city_create";
		} else {
			dao.save(city);
			judge = "redirect:/city/all";
		}
		return judge;
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
