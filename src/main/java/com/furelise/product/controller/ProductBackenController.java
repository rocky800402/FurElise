package com.furelise.product.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.furelise.product.model.Product;
import com.furelise.product.model.ProductService;
import com.furelise.productclass.model.ProductClass;
import com.furelise.productclass.model.ProductClassService;

@Controller
@RequestMapping("/product-backen")
public class ProductBackenController {

	@Autowired
	ProductService pSvc;

	@Autowired
	ProductClassService pcSvc;

	@GetMapping("/all")
	public String productList(Model model) {
		model.addAttribute("productList", pSvc.getAllProduct());
		return "b-product-list";
	}

	@GetMapping("/add")
	public String addProduct(Model model) {
		model.addAttribute("product", new Product());
		return "b-product-add";
	}

	@PostMapping("/add")
	public String productSubmit(@Valid Product product, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
	        
	        for (ObjectError error : result.getAllErrors()) {
	            
	        }
			return "b-product-add";
		} else {
			boolean isPass = pSvc.addProduct(product);
			if (isPass) {
				return "redirect:/product-backen/all";
			} else {
				model.addAttribute("errorMsgs", "商品名稱重複");
				return "b-product-add";
			}
		}
	}

	@PostMapping("/getone")
	public String getOne(@RequestParam Integer pID, Model model) {
		Product product = pSvc.getProductById(pID);
		model.addAttribute("product", product);
		return "b-product-update";
	}

	@PostMapping("/update")
	public String productUpdate(@Valid Product product, BindingResult result, Model model,
			@RequestParam("pImage1") MultipartFile[] pImage1, @RequestParam("pImage2") MultipartFile[] pImage2,
			@RequestParam("pImage3") MultipartFile[] pImage3) throws IOException {
		// 去除BindingResult中upFiles欄位的FieldError紀錄
		List<FieldError> errorsListToKeep = result.getFieldErrors().stream()
				.filter(fieldname -> !fieldname.getField().equals("pImage1")).collect(Collectors.toList());
		result = new BeanPropertyBindingResult(product, "product");
		for (FieldError fieldError : errorsListToKeep) {
			result.addError(fieldError);
		}
		if (pImage1[0].isEmpty()) { // 使用者未選擇要上傳的新圖片時
			byte[] upFiles1 = pSvc.getProductById(product.getPID()).getPImage1();
			product.setPImage1(upFiles1);
		} else {
			for (MultipartFile multipartFile : pImage1) {
				byte[] upFiles1 = multipartFile.getBytes();
				product.setPImage1(upFiles1);
			}
		}
		if (pImage2[0].isEmpty()) { // 使用者未選擇要上傳的新圖片時
			byte[] upFiles2 = pSvc.getProductById(product.getPID()).getPImage2();
			product.setPImage2(upFiles2);
		} else {
			for (MultipartFile multipartFile : pImage2) {
				byte[] upFiles2 = multipartFile.getBytes();
				product.setPImage2(upFiles2);
			}
		}
		if (pImage3[0].isEmpty()) { // 使用者未選擇要上傳的新圖片時
			byte[] upFiles3 = pSvc.getProductById(product.getPID()).getPImage3();
			product.setPImage3(upFiles3);
		} else {
			for (MultipartFile multipartFile : pImage3) {
				byte[] upFiles3 = multipartFile.getBytes();
				product.setPImage3(upFiles3);
			}
		}
		if (result.hasErrors()) {
			return "b-product-update";
		} else {
			product = pSvc.updateProduct(product);
			return "redirect:/product-backen/all";
		}
	}

	@GetMapping("/check-detail")
	public String checkDetail(@RequestParam Integer pID, Model model) {
		Product product = pSvc.getProductById(pID);
		model.addAttribute("product", product);
		return "b-product-detail";
	}

	@ModelAttribute("productClassListData")
	protected List<ProductClass> referenceListData() {
		List<ProductClass> list = pcSvc.getAll();
		return list;
	}

}
