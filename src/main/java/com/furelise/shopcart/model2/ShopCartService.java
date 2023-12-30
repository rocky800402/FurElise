package com.furelise.shopcart.model2;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.furelise.mem.model.entity.Mem;
import com.furelise.product.model.Product;
import com.furelise.product.model.ProductRepository;
import com.furelise.product.model.ProductService;
import com.google.gson.Gson;

@Service
public class ShopCartService {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Autowired
	ProductRepository productR;

	@Autowired
	ProductService pSvc;
	
	// 獲取會員編號,若沒有會員編號則紀錄為訪客購物車
	private String getCartKey(Integer memID) {

		if (memID == null) {
			// 如果memID為null，返回一個特定的字串，表示訪客購物車
			return "guestCart:guest";
		} else {
			// 否則，返回正常的購物車key
			return "memCart:" + Integer.toString(memID);
		}
	}

	// 將商品新增至購物車
	public void addProduct(String key, Integer pID, Integer quantity) {
		try {
			// 使用 HashOperations 進行操作
			HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
			if (hashOps.hasKey(key, Integer.toString(pID))) {
				// 如果存在，獲取現有數量，並增加
				String existingQuantity = hashOps.get(key, Integer.toString(pID));
				int newQuantity = Integer.parseInt(existingQuantity) + quantity;
				hashOps.put(key, Integer.toString(pID), Integer.toString(newQuantity));
			} else {
				hashOps.put(key, Integer.toString(pID), Integer.toString(quantity));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // addProduct

	// 修改購物車內特定商品的數量
	public void updateQuantity(String key, Integer pID, Integer quantity) {
		try {
			// 使用 HashOperations 進行操作
			HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();

			// 從 Hash 中獲取序列化的值
			String serializedItem = hashOps.get(key, Integer.toString(pID));

			// 反序列化為對象
			ShopCart theCart = deserialize(serializedItem);

			// 更新數量
			theCart.setPID(pID);
			theCart.setQuantity(quantity);

			// 將更新後的對象再次序列化並存入Redis
			hashOps.put(key, Integer.toString(pID), Integer.toString(quantity));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 移除購物車內的特定商品
	public void removeProduct(String key, Integer pID) {
		
		try {
			// 使用 HashOperations 進行操作
			HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();

			// 從 Hash 中刪除指定字段
			hashOps.delete(key, Integer.toString(pID));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// removeProduct

	// 結帳後清空會員的整台購物車
	public void clearCart(Integer memID) {
		try {
			// 組合購物車的 key
			String cartkey = getCartKey(memID);

			// 刪除購物車的 key
			redisTemplate.delete(cartkey);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void clearCart(BigInteger memID) {
		try {
			// 組合購物車的 key
			String cartkey = getCartKey(memID);

			// 刪除購物車的 key
			redisTemplate.delete(cartkey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private String getCartKey(BigInteger memID) {
		if (memID == null) {
			// 如果memID為null，返回一個特定的字串，表示訪客購物車
			return "guestCart:guest";
		} else {
			// 否則，返回正常的購物車key
			return "memCart:" + memID.toString();
		}
	}

	// 取得訪客購物車內的所有商品
	public Map<Product, String> getCartProducts(String key) {
		HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
		Map<String, String> map = hashOps.entries(key);
		Map<Product, String> map2 = new HashMap<Product, String>();
		for (String thePID : map.keySet()) {
			Product product = pSvc.getProductById(Integer.valueOf(thePID));
			map2.put(product, map.get(thePID));
		}
		return map2;
		
//		if (memID.equals("guestCart:guest")) {
//			// 在這裡實現訪客購物車的邏輯
//			key = getCartKey(null);
//
//		} else {
//			String memId = memID.replaceAll("memCart:", "");
//			// 會員的購物車
//			key = getCartKey(Integer.valueOf(memId));
//	
//			// 使用 HashOperations 進行操作
//			HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
//			Map<String, String> map = hashOps.entries(key);
//			Map<Product, String> map2 = new HashMap<Product, String>();
//			for (String thePID : map.keySet()) {
//				Product product = pSvc.getProductById(Integer.valueOf(thePID));
//				map2.put(product, map.get(thePID));
//			}
//
//			return map2;
//		}
	}

	// 取得購物車內特定商品的數量
	public String getOneProduct(Integer memID, Integer pID) {
		try {
			String key = getCartKey(memID);

			// 使用 HashOperations 進行操作
			HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();

			// 從 Redis 中獲取特定商品的數量
			return hashOps.get(key, Integer.toString(pID));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	} // getOneProduct

	private ShopCart deserialize(String serializedItem) {
		Gson gson = new Gson();
		try {
			return gson.fromJson(serializedItem, ShopCart.class);
		} catch (Exception e) {
			// 如果出現 JSON 解析錯誤，你可以在這裡採取一些措施，例如紀錄錯誤、返回默認值等

			// 在這裡修復把serializedItem回傳的數字，包裝成一個 JSON 字串
			if (serializedItem.matches("-?\\d+")) {

				return gson.fromJson("{\"quantity\":" + serializedItem + "}", ShopCart.class);
			}
			e.printStackTrace();
			return null;
		}
	} // deserialize

	public Map<Product, String> saveCartProducts(String key, Map<Product, String> newMemCart) {
		 try {
	            HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
	            // 存儲新的購物車內容
	            for (Map.Entry<Product, String> entry : newMemCart.entrySet()) {
	                Product product = entry.getKey();
	                String productId = String.valueOf(product.getPID());
	                String quantity = entry.getValue();
	                hashOps.put(key, productId, quantity);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		return newMemCart;
	    }


}// ShopCartService
