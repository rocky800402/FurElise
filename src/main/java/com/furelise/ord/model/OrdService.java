package com.furelise.ord.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.furelise.city.model.City;
import com.furelise.city.model.CityRepository;
import com.furelise.mem.model.entity.Mem;
import com.furelise.mem.repository.MemRepository;
import com.furelise.orddetail.model.OrdDetail;
import com.furelise.orddetail.model.OrdDetailPK;
import com.furelise.orddetail.model.OrdDetailRepository;
import com.furelise.product.model.Product;
import com.furelise.product.model.ProductRepository;
import com.furelise.product.model.ProductService;
import com.furelise.sale.model.Sale;
import com.furelise.sale.model.SaleService;
import com.furelise.shopcart.model2.ShopCartService;
//import com.furelise.shopcart.model2.ShopCart;
//import com.furelise.shopcart.model2.ShopCartService;

@Service
public class OrdService {

	@Autowired
	OrdRepository oDao;
	
	@Autowired
	MemRepository memDao;

	@Autowired
	SaleService sSvc;

	@Autowired
	ShopCartService scSvc;

	@Autowired
	OrdDetailRepository odR;

	@Autowired
	ProductRepository pR;
	@Autowired
	ProductService pSvc;

	@Autowired
	CityRepository cR;

	// 創建訂單 用OrdDTO抓
	@Transactional
	public Ord createOrder(@ModelAttribute("ordDTO") OrdDTO ordDto, Model model, HttpServletRequest req) {
		Mem mem = (Mem) req.getSession().getAttribute("mem");

		String coupon = ordDto.getCoupon();
		if (coupon != null) {
			coupon = ordDto.getCoupon(); // 如果 sale 不為 null，保持 coupon 不變
		} else {
			coupon = "sale0000";
		}

		Ord ord = new Ord();

		ord.setOrdDate(new Timestamp(System.currentTimeMillis()));
		ord.setMemID(mem.getMemID());
		ord.setPayment(ordDto.getPayment()); //
		ord.setDeliver(ordDto.getDeliver()); //
		ord.setAddress(ordDto.getAddress()); //
		ord.setCityCode(ordDto.getCityCode());
		ord.setDeliverDate(null); // 出貨時間初始為null

		ord.setSaleID(sSvc.getSaleByCoupon(coupon).getSaleID());
		ord.setOrdStatus(0); // 預設都為0(處理中)
		ord.setArrival(null); // 送達時間初始為null
		
		//抓取小計,折扣金額,運費,實際付款金額以設定ord
		Map<Product, String> shopCartItems = scSvc.getCartProducts("memCart:" + mem.getMemID().toString());// 問題在於沒有獲取到購物車中的商品

		// 計算訂單金額
		BigDecimal sum = calculateOrderSum(shopCartItems);
		BigDecimal discount = calculateDiscount(ordDto.getCoupon()); // 根据实际逻辑计算折扣
		BigDecimal shippingFee = calculateShippingFee(ordDto.getDeliver()); // 根据实际逻辑计算运费
		BigDecimal total = sum.subtract(discount).add(shippingFee);

		// 將訂單的各項金額設定進Ord
		ord.setSum(sum);
		ord.setShipping(shippingFee);
		ord.setTotal(total);
		List<OrdDetail> ordDetails = new ArrayList<>();

		oDao.save(ord);
		
		//進行訂訂單明細OrdDetail設置
	
		for (Map.Entry<Product, String> entry : shopCartItems.entrySet()) {
			
			String quantity = entry.getValue();

			// 設置 OrdDetail 的相關屬性
			OrdDetail ordDetail = new OrdDetail();
		    OrdDetailPK odPK = new OrdDetailPK();
		    
		    // 将 ordDetail 的 ordID 设置为生成的 OrdID
		    odPK.setOrdID(ord.getOrdID());
		    odPK.setPID(entry.getKey().getPID());

		    ordDetail.setOrdDetailPK(odPK);
		    ordDetail.setOrdID(odPK.getOrdID());
		    ordDetail.setPID(odPK.getPID());
		    ordDetail.setDetaQty(Integer.valueOf(quantity));
		    ordDetail.setFeedback(null); // 訂單明細創建初始時評價為 null
		    ordDetail.setLevel(null); // 訂單明細創建初始時評價等級為 null
		    ordDetail.setFbTime(null); // 訂單明細創建初始時評價內容為 null
		    
		    odR.save(ordDetail);
		}

		// 清空購物車
		scSvc.clearCart(mem.getMemID());
		return ord;

	} // createOrder

	// 計算訂單總金額
	private BigDecimal calculateOrderSum(Map<Product, String> shopCartItems) {
		BigDecimal sum = BigDecimal.ZERO;

		for (Map.Entry<Product, String> entry : shopCartItems.entrySet()) {
			// 計算每個商品的小計並加總
			BigDecimal price = entry.getKey().getPPrice();
			int quantity = Integer.parseInt(entry.getValue());
			BigDecimal itemTotal = price.multiply(BigDecimal.valueOf(quantity));
			sum = sum.add(itemTotal);
		}

		return sum;
	}

	// 獲取折扣金額
	private BigDecimal calculateDiscount(String coupon) {
		return sSvc.getSaleByCoupon(coupon).getDis();

	}

	private BigDecimal calculateShippingFee(Integer deliver) {
		BigDecimal shipping = null;
		if(deliver == 0) {
		shipping= new BigDecimal(120.00);
		}
		return shipping;
	}

	//
	public Ord updateOrd(Integer ordID, Integer ordStatus) {
		Ord ord = oDao.findById(ordID).orElse(null);
		ord.setOrdStatus(ordStatus);
		ord.setDeliverDate(new Date(System.currentTimeMillis()));
		oDao.save(ord);
		return ord;

	}

	public List<Ord> getAllOrds() {
		return oDao.findAll();
	}

	public OrdVO getOrdById(Integer ordID) {
		Ord ord = oDao.findById(ordID).orElseThrow();
		return this.getOrdVO(ord);
	}

	public List<Ord> getByStatus(Integer ordStatus) {
		return oDao.findByOrdStatus(ordStatus);
	}

	private OrdVO getOrdVO(Ord ord) {
		City city = cR.findByCityCode(ord.getCityCode());
		List<OrdDetailBO> ordDetailBOList = getOrdDetailBO(ord);
		Sale sale = Objects.isNull(ord.getSaleID()) ? null : sSvc.getOneSale(ord.getSaleID());
		return new OrdVO(city, ord, ordDetailBOList, sale);
	}

	public List<OrdDetailBO> getOrdDetailBO(Ord ord) {
		List<OrdDetail> ordDetails = odR.findByOrdID(ord.getOrdID());
		return ordDetails.stream().map((ordDetail) -> {
			Product product = pSvc.getProductById(ordDetail.getOrdDetailPK().getPID());
			return new OrdDetailBO(product, ordDetail);
		}).toList();
	}

	// 創建訂單 從購物車直接抓
//	public Ord createOrder(Map<String, String> shopCartItems, BigDecimal totalAmount) {
//
//		Ord ord = new Ord();
//
//		ord.setOrdDate(new Timestamp(System.currentTimeMillis()));
//		ord.setMemID(null);
//		ord.setPayment(null);
//		ord.setDeliver(null);
//		ord.setAddress(null);
//		ord.setCityCode(null);
//		ord.setDeliverDate(null);
//		ord.setSum(totalAmount);
//		ord.setShipping(totalAmount);
//		ord.setTotal(totalAmount);
//		ord.setSaleID(saleDao.getSaleByCoupon(null).getSaleID());
//		ord.setOrdStatus(0); // 預設都為0(處理中)
//		ord.setArrival(null);
//
//		// 創建訂單明細OrdDetail
//		List<OrdDetail> ordDetails = convertShopCartItemsToOrdDetails(shopCartItems, ord);
//		ord.setOrdDetails(ordDetails);
//
//		dao.save(ord);
//
//		return ord;
//	}
	// 將購物車商品轉為訂單明細（OrdDetail）
//	private List<OrdDetail> convertShopCartItemsToOrdDetails(Map<String, String> shopCartItems, Ord ord) {
//
//		List<OrdDetail> ordDetails = new ArrayList<>();
//		ObjectMapper objectMapper = new ObjectMapper();
//
//		// 抓出購物車中的所有商品
//		for (int i = 0; i < shopCartItems.size(); i++) {
//
//			String shopCartItemString = shopCartItems.get(i);
//
//			try {
//				// 字串轉換為ShopCart
//				ShopCart shopCartItem = objectMapper.readValue(shopCartItemString, ShopCart.class);
//
//				// 創建OrdDetail
//				// 評價部分的feedback,level,fbTime在創建初始皆為null
//				OrdDetail ordDetail = new OrdDetail(ord.getOrdID(), shopCartItem.getPID(), shopCartItem.getQuantity(),
//						null, null, null);
//
//				ordDetails.add(ordDetail);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//			return ordDetails;
//		}
//		return ordDetails;
//	}

}
