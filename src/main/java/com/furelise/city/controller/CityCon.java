package com.furelise.city.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.furelise.city.model.*;

@Controller
@RequestMapping("/city")
public class CityCon {

		@Autowired
		CityService citySvc;
		
		@GetMapping("/all")
		public String cityList(Model model) {
			model.addAttribute("cityList", citySvc.getAllCity());
			
			return "b_city_list";
		}
		
		
		//cityCode不可空白、不可重複、三位數字
		//cityName不可空白
		@GetMapping("/new")
		public String createForm(Model model) {
			
			model.addAttribute("city", new City());
			return "b_city_create";
		}
		
		@PostMapping("/new")
		public String citySubmit(@Valid @ModelAttribute City city, Model model) {
			model.addAttribute("city", city);
			citySvc.addCity(city);
			return "b_city_detail";
		}
		
		@PostMapping("/getOne")
		public String getOne(@RequestParam Integer cityID, Model model) {
			City city = citySvc.getCityById(cityID);
			model.addAttribute("city", city);
			System.out.println(city);
			return "b_city_update"; 
		}
		
		@PostMapping("/update")
		public String cityUpdate(@ModelAttribute City city, Model model) {
			System.out.println(city); //ID欄要用readonly，不能disabled
			City newCity = citySvc.updateCity(city);
			model.addAttribute("city", newCity);
			return "b_city_detail";
		}
		
		@PostMapping("/delete")
		public String cityDelete(@RequestParam String cityID, Model model) {
			citySvc.delete(Integer.valueOf(cityID));
			model.addAttribute("cityList", citySvc.getAllCity());
			
			return "redirect:/city/all";
			}
		
	}