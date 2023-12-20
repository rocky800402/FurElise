package com.furelise.estabcase.controller;

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
import com.furelise.shopcart.model.ShopCart;
import com.furelise.shopcart.model.ShopCartRepository;
import com.furelise.vacation.model.Vacation;
import com.furelise.vacation.model.VacationRepository;


@RestController
@RequestMapping("/estabcase")
public class EstabCaseController  {
	
	@Autowired
	private EstabCaseService estabCaseService;
	
	@Autowired
	private OrdDetailRepository ordDetailRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private EmpRepository empRepository;

	@Autowired
	private ComplaintRepository complaintRepository;

	@Autowired
	private MemRepository memRepository;

	@Autowired
	private PeriodRepository periodRepository;
	
	@Autowired
	private OrdRepository ordRepository;

	@Autowired
	private PickupTimeRepository pickupTimeRepository;

	@Autowired
	private PickupWayRepository pickupWayRepository;

	@Autowired
	private PlanRepository planRepository;
	
	@Autowired
	private PlanOrdRepository planOrdRepository;
	
	@Autowired
	private PlanStatusRepository planStatusRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductClassRepository productClassRepository;
	
	@Autowired
	private SaleRepository saleRepository;
	
	@Autowired
	private ShopCartRepository shopCartRepository;
	
	@Autowired
	private VacationRepository vacationRepository;
	
	
	@GetMapping("/{id}")
	public List<EstabCase> getAllEstabCases(@PathVariable String id){
//		List<OrdDetail> ordDetailList=ordDetailRepository.findAll();
//		OrdDetail ordDetail = ordDetailList.get(0);
//		System.out.println(ordDetail);
//		System.out.println(ordDetail.getOrdDetailPK());
//		Ord ord= ordRepository.findById(150001).orElseThrow();
//		System.out.println(ord);
		List<EstabCase> estabCaseList = estabCaseService.getAllEstabCase();
		System.out.println(estabCaseList);
		return estabCaseList;
		
	}
	
	@GetMapping("/admin")
	public List<Admin> getAllAdmins(){
		
		List<Admin> adminsList = adminRepository.findAll();
		System.out.println(adminsList);
		return adminsList;
		
	}
	
	
	@GetMapping("/city")
	public List<City> getAllCitys(){
		
		List<City> cityList = cityRepository.findAll();
		System.out.println(cityList);
		return cityList;
		
	}
	
	
	@GetMapping("/complaint")
	public List<Complaint> getAllComplaints(){
		
		List<Complaint> complaintList = complaintRepository.findAll();
		System.out.println(complaintList);
		return complaintList;
		
	}
	
	
	@GetMapping("/emp")
	public List<Emp> getAllEmps(){
		
		List<Emp> empList = empRepository.findAll();
		System.out.println(empList);
		return empList;
		
	}

	
	@GetMapping("/mem")
	public List<Mem> getAllMems(){
		
		List<Mem> memList = memRepository.findAll();
		System.out.println(memList);
		return memList;
		
	}

	
	@GetMapping("/ord")
	public List<Ord> getAllOrds(){
		
		List<Ord> ordList = ordRepository.findAll();
		System.out.println(ordList);
		return ordList;
		
	}

	
	@GetMapping("/orddetail")
	public List<OrdDetail> getAllOrdDetails(){
		
		List<OrdDetail> ordDetailList = ordDetailRepository.findAll();
		System.out.println(ordDetailList);
		return ordDetailList;
		
	}

	
	@GetMapping("/periodList")
	public List<Period> getAllPeriods(){
		
		List<Period> periodList = periodRepository.findAll();
		System.out.println(periodList);
		return periodList;
		
	}

	
	@GetMapping("/pickupTimeList")
	public List<PickupTime> getAllPickupTime(){
		
		List<PickupTime> pickupTimeList = pickupTimeRepository.findAll();
		System.out.println(pickupTimeList);
		return pickupTimeList;
		
	}
	
	
	@GetMapping("/pickupWayList")
	public List<PickupWay> getAllPickupWay(){
		
		List<PickupWay> pickupWayList = pickupWayRepository.findAll();
		System.out.println(pickupWayList);
		return pickupWayList;
		
	}

	
	@GetMapping("/planList")
	public List<Plan> getAllPlan(){
		
		List<Plan> planList = planRepository.findAll();
		System.out.println(planList);
		return planList;
		
	}
	
	
	@GetMapping("/planOrdList")
	public List<PlanOrd> getAllPlanOrd(){
		
		List<PlanOrd> planOrdList = planOrdRepository.findAll();
		System.out.println(planOrdList);
		return planOrdList;
		
	}
	
	
	@GetMapping("/planStatusList")
	public List<PlanStatus> getAllPlanStatus(){
		
		List<PlanStatus> planStatusList = planStatusRepository.findAll();
		System.out.println(planStatusList);
		return planStatusList;
		
	}
	
	
	@GetMapping("/postList")
	public List<Post> getAllPosts(){
		
		List<Post> postList = postRepository.findAll();
		System.out.println(postList);
		return postList;
		
	}

	
	@GetMapping("/productList")
	public List<Product> getAllProducts(){
//		List<Product> productList = productRepository.findAll();
		System.out.println(productRepository.existsBypName("垃圾專用回收袋"));
		return null;
		
	}
	
	
	@GetMapping("/productClassList")
	public List<ProductClass> getAllProductClass(){
		
		List<ProductClass> productClassList = productClassRepository.findAll();
		System.out.println(productClassList);
		return productClassList;
		
	}
	
	
	@GetMapping("/saleList")
	public List<Sale> getAllSales(){
		
		List<Sale> saleList = saleRepository.findAll();
		System.out.println(saleList);
		return saleList;
		
	}
	
	
	@GetMapping("/shopCartList")
	public List<ShopCart> getAllShopCart(){
		
		List<ShopCart> shopCartList = shopCartRepository.findAll();
		System.out.println(shopCartList);
		return shopCartList;
		
	}
	
	
	@GetMapping("/vacationList")
	public List<Vacation> getAllVacation(){
		
		List<Vacation> vacationList = vacationRepository.findAll();
		System.out.println(vacationList);
		return vacationList;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
     
//	public String getAllEstabCases(HttpServletRequest req, HttpServletResponse res) {
//
//	
//	List<EstabCase> estabCaseList = estabCaseService.getAllEstabCase();
//	req.setAttribute("estabCaseList", estabCaseList);
//	return "/estabcase/listAllEstabCase.jsp";
//}
//    
//	
	
	
	
	
//	@Override
//	public void destroy() {
//	}
//
//	@Override
//	public void init() throws ServletException {
//		estabCaseService = new EstabCaseService();
//	}
//	
//	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		
//		doPost(req, res);
//	}
//
//
//	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		req.setCharacterEncoding("UTF-8");
//		String action = req.getParameter("action");
//		String forwardPath = "";
//		switch (action) {
//			case "getAll": {
//				forwardPath = getAllEstabCases(req, res);
//				break;
//			}
//			case "add": {
//				forwardPath = addEstabCases(req, res);
//				break;
//			}
//			case "getOne": {
//				forwardPath = getOneOfUpdate(req, res);
//				break;
//			}
//			case "update": {
//				forwardPath = updateEstabCases(req, res);
//				break;
//			}
//			default:
//				forwardPath = "/index.jsp";
//			}
//		
//		res.setContentType("text/html; charset=UTF-8");
//		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
//		dispatcher.forward(req, res);
//		return ;
//		
//
//	}
//	public String getAllEstabCases(HttpServletRequest req, HttpServletResponse res) {
////		String pege = req.getParameter("page");
////		int currentPage = (pege==null)?1:Integer.parseInt(pege);
//		
//		List<EstabCase> estabCaseList = estabCaseService.getAllEstabCase();
//		req.setAttribute("estabCaseList", estabCaseList);
//		return "/estabcase/listAllEstabCase.jsp";
//	}
//	public String addEstabCases(HttpServletRequest req, HttpServletResponse res) {
//		List<String> errorMsgs = new LinkedList<String>();
//		// Store this set in the request scope, in case we need to
//		// send the ErrorPage view.
//		req.setAttribute("errorMsgs", errorMsgs);
//		String url = "";
//		/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//		String empID = "";
//
//		empID = req.getParameter("empID");
//		String enameReg12 = "^12\\d{4}$";
//		if (empID == null || empID.trim().length() == 0 ) {
//			errorMsgs.add("夥伴編號: 請勿空白");
//		} else if(!empID.trim().matches(enameReg12)) { //以下練習正則(規)表示式(regular-expression)
//			errorMsgs.add("(無符合代碼的夥伴),夥伴編號: 12開頭,只能是數字, 且長度必需6位數");
//        }
//		//------------------------
//		Integer empIDInteger = null;
//		try {
//			empIDInteger = Integer.valueOf(empID);
//		} catch (NumberFormatException e) {
//			empIDInteger=120001;
//			errorMsgs.add("夥伴編號格式錯誤");
//		}
//		
//		//------------------------
//		
//		String planOrdID = req.getParameter("planOrdID");
//		String enameReg18 = "^18\\d{4}$";
//		if (planOrdID == null || planOrdID.trim().length()==0) {
//			errorMsgs.add("方案訂單編號: 請勿空白");
//		} else if(!planOrdID.trim().matches(enameReg18)) { //以下練習正則(規)表示式(regular-expression)
//			errorMsgs.add("(無符合代碼的方案訂單),方案訂單編號: 18開頭，只能是數字, 且長度必需6位數");
//        }
//		
//		//------------------------
//		Integer planOrdIDInteger = null;
//		try {
//			planOrdIDInteger = Integer.valueOf(planOrdID);
//		} catch (NumberFormatException e) {
//			planOrdIDInteger=180001;
//			errorMsgs.add("方案訂單編號格式錯誤");
//		}
//		
//		//------------------------
//		
//		java.sql.Date estabCaseDate = null;
//		try {
//			estabCaseDate = java.sql.Date.valueOf(req.getParameter("estabCaseDate").trim());
//		}catch (NumberFormatException se) {
//			estabCaseDate=new java.sql.Date(System.currentTimeMillis());
//			errorMsgs.add("請輸入日期!");
//		} catch (IllegalArgumentException e) {
//			estabCaseDate=new java.sql.Date(System.currentTimeMillis());
//			errorMsgs.add("請輸入日期!");
//		}
//		
//		Double planPricePerCase = null;
//		try {
//			planPricePerCase = Double.valueOf(req.getParameter("planPricePerCase").trim());
//		} catch (NumberFormatException e) {
//			planPricePerCase = 0.0;
//			errorMsgs.add("單筆收入請填數字.");
//		}
//		EstabCase estabCase = new EstabCase();
//		estabCase.setEmpID(empIDInteger);
//		estabCase.setPlanOrdID(planOrdIDInteger);
//		estabCase.setEstabCaseDate(estabCaseDate);
//		estabCase.setPlanPricePerCase(new BigDecimal(planPricePerCase));
//		
//		if (!errorMsgs.isEmpty()) {
//			url = "/index.jsp";
//			req.setAttribute("estabCase", estabCase); // 含有輸入格式錯誤的empVO物件,也存入req
//			RequestDispatcher failureView = req.getRequestDispatcher(url);
//			
//		}else {
//			url = "/estabcase/listAllEstabCase.jsp";
//			estabCase = estabCaseService.addEstabCase(empIDInteger, planOrdIDInteger, estabCaseDate, new BigDecimal(planPricePerCase));
//			System.out.print(estabCase);
//
//			List<EstabCase> estabCaseList = estabCaseService.getAllEstabCase();
//			req.setAttribute("estabCaseList", estabCaseList);
//		}
//		return url;
//	}
//	
//	public String getOneOfUpdate(HttpServletRequest req, HttpServletResponse res) {
//		List<String> errorMsgs = new LinkedList<String>();
//		// Store this set in the request scope, in case we need to
//		// send the ErrorPage view.
//		req.setAttribute("errorMsgs", errorMsgs);
//		
//		Integer estabCaseID = Integer.valueOf(req.getParameter("estabCaseID"));
//		EstabCase estabCase = estabCaseService.getEstabCasePK(estabCaseID);
//		req.setAttribute("estabCase", estabCase);
//		
//		return "/estabcase/updateEstabCaseInput.jsp";
//		
//	}
//	
//	public String updateEstabCases (HttpServletRequest req, HttpServletResponse res) {
//		List<String> errorMsgs = new LinkedList<String>();
//		// Store this set in the request scope, in case we need to
//		// send the ErrorPage view.
//		
//		req.setAttribute("errorMsgs", errorMsgs);
//		String url = "";
//		/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//		Integer estabCaseID = Integer.valueOf(req.getParameter("estabCaseID"));
//		
//		
//		String empID = req.getParameter("empID");
//		String enameReg12 = "^12\\d{4}$";
//		if (empID == null || empID.trim().length() == 0) {
//			errorMsgs.add("夥伴編號: 請勿空白");
//		} else if(!empID.trim().matches(enameReg12)) { //以下練習正則(規)表示式(regular-expression)
//			errorMsgs.add("(無符合代碼的夥伴),夥伴編號: 12開頭,只能是數字, 且長度必需6位數");
//        }
//		//------------------------
//		Integer empIDInteger = null;
//		try {
//			empIDInteger = Integer.valueOf(empID);
//		} catch (NumberFormatException e) {
//			empIDInteger=120001;
//			errorMsgs.add("夥伴編號格式錯誤");
//		}
//		
//		//------------------------
//		
//		
//		String planOrdID = req.getParameter("planOrdID");
//		String enameReg18 = "^18\\d{4}$";
//		if (planOrdID == null || planOrdID.trim().length() == 0) {
//			errorMsgs.add("方案訂單編號: 請勿空白");
//		} else if(!planOrdID.trim().matches(enameReg18)) { //以下練習正則(規)表示式(regular-expression)
//			errorMsgs.add("(無符合代碼的方案訂單),方案訂單編號: 18開頭，只能是數字, 且長度必需6位數");
//        }
//		//------------------------
//		Integer planOrdIDInteger = null;
//		try {
//			planOrdIDInteger = Integer.valueOf(planOrdID);
//		} catch (NumberFormatException e) {
//			planOrdIDInteger=180001;
//			errorMsgs.add("方案訂單編號格式錯誤");
//		}
//		
//		//------------------------
//		
//		java.sql.Date estabCaseDate = null;
//		try {
//			estabCaseDate = java.sql.Date.valueOf(req.getParameter("estabCaseDate").trim());
//		} catch (NumberFormatException se) {
//			estabCaseDate=new java.sql.Date(System.currentTimeMillis());
//			errorMsgs.add("請輸入日期!");
//		} catch (IllegalArgumentException e) {
//			estabCaseDate=new java.sql.Date(System.currentTimeMillis());
//			errorMsgs.add("請輸入日期!");
//		}
//		
//		Double planPricePerCase = null;
//		try {
//			planPricePerCase = Double.valueOf(req.getParameter("planPricePerCase").trim());
//		} catch (NumberFormatException e) {
//			planPricePerCase = 0.0;
//			errorMsgs.add("單筆收入請填數字.");
//		}
//		String estabCaseStatus = req.getParameter("estabCaseStatus");
//		String enameReg = "^[0-3]$";
//		if (estabCaseStatus == null || estabCaseStatus.trim().length() == 0) {
//			errorMsgs.add("案件狀態: 請勿空白");
//		} else if(!estabCaseStatus.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//			errorMsgs.add("(無符合代碼的案件狀態),案件狀態編號: 請填寫數字0~3");
//        }
//		//------------------------
//		Integer estabCaseStatusInteger = null;
//		try {
//			estabCaseStatusInteger = Integer.valueOf(estabCaseStatus);
//		} catch (NumberFormatException e) {
//			estabCaseStatusInteger=0;
//			errorMsgs.add("案件狀態格式錯誤");
//		}
//		
//		//------------------------
//		EstabCase estabCase = new EstabCase();
//		estabCase.setEstabCaseID(estabCaseID);
//		estabCase.setEmpID(empIDInteger);
//		estabCase.setPlanOrdID(planOrdIDInteger);
//		estabCase.setEstabCaseDate(estabCaseDate);
//		estabCase.setPlanPricePerCase(new BigDecimal(planPricePerCase));
//		estabCase.setEstabCaseStatus(estabCaseStatusInteger);
//		
//		if (!errorMsgs.isEmpty()) {
//			url = "/estabcase/updateEstabCaseInput.jsp";
//			req.setAttribute("estabCase", estabCase); // 含有輸入格式錯誤的empVO物件,也存入req
//			RequestDispatcher failureView = req.getRequestDispatcher(url);
//			
//		}else {
//			url = "/estabcase/listAllEstabCase.jsp";
//			estabCase = estabCaseService.updateEstabCase(estabCaseID,empIDInteger, planOrdIDInteger, estabCaseDate, new BigDecimal(planPricePerCase),estabCaseStatusInteger);
//			System.out.print(estabCase);
////			req.setAttribute("estabCase", estabCase);
//			List<EstabCase> estabCaseList = estabCaseService.getAllEstabCase();
//			req.setAttribute("estabCaseList", estabCaseList);
//		}
//		return url;
//		
//	}

}
