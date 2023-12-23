package com.furelise.shopcart;

import java.util.List;

import com.furelise.mem.model.entity.Mem;
import com.furelise.mem.repository.MemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.furelise.admin.model.Admin;
import com.furelise.admin.model.AdminRepository;
import com.furelise.city.model.City;
import com.furelise.city.model.CityRepository;
import com.furelise.complaint.model.Complaint;
import com.furelise.complaint.model.ComplaintRepository;
import com.furelise.emp.model.Emp;
import com.furelise.emp.model.EmpRepository;
import com.furelise.estabcase.model.EstabCase;
import com.furelise.estabcase.model.EstabCaseService;
import com.furelise.ord.model.Ord;
import com.furelise.ord.model.OrdRepository;
import com.furelise.orddetail.model.OrdDetail;
import com.furelise.orddetail.model.OrdDetailRepository;
import com.furelise.period.model.Period;
import com.furelise.period.model.PeriodRepository;
import com.furelise.pickuptime.model.PickupTime;
import com.furelise.pickuptime.model.PickupTimeRepository;
import com.furelise.pickupway.model.PickupWay;
import com.furelise.pickupway.model.PickupWayRepository;
import com.furelise.plan.model.Plan;
import com.furelise.plan.model.PlanRepository;
import com.furelise.planord.model.PlanOrd;
import com.furelise.planord.model.PlanOrdRepository;
import com.furelise.planstatus.model.PlanStatus;
import com.furelise.planstatus.model.PlanStatusRepository;
import com.furelise.post.model.Post;
import com.furelise.post.model.PostRepository;
import com.furelise.product.model.Product;
import com.furelise.product.model.ProductRepository;
import com.furelise.productclass.model.ProductClass;
import com.furelise.productclass.model.ProductClassRepository;
import com.furelise.sale.model.Sale;
import com.furelise.sale.model.SaleRepository;

import com.furelise.shopcart.model2.ShopCartService;



@RestController
@RequestMapping("/test")
public class TestController  {
	
	@Autowired
	private ShopCartService shopCartService;
	
	
	@GetMapping()
	public void test(){
		//測試service新增
//		shopCartService.addProduct(110004, 10, 1);
//		shopCartService.addProduct(110004, 20, 2);
//		shopCartService.addProduct(110004, 30, 3);
//		shopCartService.addProduct(110004, 40, 4);
		
		//測試service修改
//		shopCartService.updateQuantity(110004, 10, 1);
		
		//測試service移除特定商品
//		shopCartService.removeProduct(110004, 40);
		
		//測試service清除整台購物車
//		shopCartService.clearCart(110004);
		
		//未成功
		//測試service取得該會員購物車內的所有商品
//		shopCartService.getShopCartProducts(110004);
		
		//未成功
		//測試service取得購物車內特定商品的數量
		shopCartService.getOneProduct(110004, 20);
	}
	



}
