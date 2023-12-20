/////沒在用///
//package com.furelise.city.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.furelise.city.model.*;
//
//
//@RestController
//@RequestMapping("/city")
//public class CityRESTCon {
//		
//	@Autowired
//	CityService citySvc;
//
//	
//	@PostMapping("/add")
//	public String addCity(@RequestBody City req) {
//		City city = new City();
//		city.setCityCode(req.getCityCode());
//		city.setCityName(req.getCityName());
//		return citySvc.addCity(city);
//	}
//
//	
//	@RequestMapping("/update")
//	//如果要用@PutMapping要傳參數(可能是@RequestBody City city)
//	public String updateCity() {
//		City city = new City();
//		city.setCityID(280018);
//		city.setCityCode("335");
//		city.setCityName("新竹市");		
//		return citySvc.updateCity(city) + " updated successfully";
//	}
//	
//	
//	@GetMapping("/getall")
//	public List<City> getAllCitys(){
//		List<City> cityList = citySvc.getAllCity();
//		return cityList;
//	}
//	
//	
//	@GetMapping(path = "{cityID}")
//	public City getCityById(@PathVariable("cityID") Integer cityID) {
//		return citySvc.getCityById(cityID);
//	}
//}
