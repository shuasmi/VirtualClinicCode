package com.mypack.controllers;

import java.security.Principal;
import java.time.LocalDateTime;


import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mypack.dao.AppointmentDetailsRepo;
import com.mypack.dao.BlogPostRepo;
import com.mypack.dao.MyOrderRepo;
import com.mypack.dao.MyServiceBro;
import com.mypack.dao.PatientDetailsRepo;
import com.mypack.dao.PatientLoginDetailsRepo;
import com.mypack.dao.VideoService;
import com.mypack.entities.AppointmentDetails;
import com.mypack.entities.BlogPost;
import com.mypack.entities.MyOrder;
import com.mypack.entities.PatientDetails;
import com.mypack.entities.PatientLoginDetails;
import com.mypack.entities.Video;
import com.mypack.helper.Message;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import com.razorpay.*;
//import com.razorpay.RazorpayClient;

@Controller
@RequestMapping("/Patient")
public class PatientController {
	
	@Autowired
	private PatientDetailsRepo patientDetailsRepo;
	
	@Autowired
	private BlogPostRepo blogPostRepo;
	
	@Autowired
	private MyServiceBro myServiceBro;
	
	@Autowired
	private VideoService videoService;
	
	@Autowired
	private AppointmentDetailsRepo appointmentDetailsRepo;
	
	@Autowired
	private PatientLoginDetailsRepo patientLoginDetailsRepo;
	@Autowired
	private MyOrderRepo myOrderRepo;
	
	@RequestMapping(value="/PatientDetails")
	public String patientDetails(Model m)
	{
		
		
		return "PatientDetails";
		
	}
	
	@RequestMapping(value="/AyurvedicConsultation")
	public String ayurvedicConsultation(Model m)
	{
		m.addAttribute("title","Ayurvedic Consultation-Sai Clinic");
		m.addAttribute("PatientDetails",new PatientDetails());
		return "AyurvedicConsultation";
	}
	
	@RequestMapping(value="/AyurvedicConsultation2")
	public String ayurvedicConsultation2(Model m)
	{
		m.addAttribute("title","Ayurvedic Consultation-Sai Clinic");
		m.addAttribute("PatientDetails",new PatientDetails());
		return "AyurvedicConsultation2";
	}
	
	@RequestMapping(value="/AlopathyConsultation")
	public String alopathyConsultation(Model m)
	{
		m.addAttribute("title","Alopathy Consultation-Sai Clinic");
		m.addAttribute("PatientDetails",new PatientDetails());
		return "AlopathyConsultation";
	}
	
	@RequestMapping(value="/AlopathyConsultation2")
	public String alopathyConsultation2(Model m)
	{
		m.addAttribute("title","Alopathy Consultation-Sai Clinic");
		m.addAttribute("PatientDetails",new PatientDetails());
		return "AlopathyConsultation2";
	}
	
	@RequestMapping(value="/HealthTips/{page}")
	public String healthTips(@PathVariable int page,Model m)
	{
		m.addAttribute("title","Health Tips-Sai Clinic");
		

		 
		//List<BlogPost> bg=blogPostRepo.findAll();
		
		
		int pageSize=2;
		 Page<BlogPost> paginatedData =myServiceBro.getPaginatedData(page, pageSize);
		 
		 m.addAttribute("BlogPost",paginatedData);
		 m.addAttribute("currentPage", paginatedData.getNumber());
	     m.addAttribute("totalPages", paginatedData.getTotalPages());
	     m.addAttribute("totalItems", paginatedData.getTotalElements());
	     m.addAttribute("yourEntities", paginatedData.getContent());
		
		return "HealthTips";
	}
	
	@RequestMapping(value="/Yoga/{page}")
	public String yoga(@PathVariable int page,Model m)
	{
		m.addAttribute("title","Yoga-Sai Clinic");
		
		//List<Video> videos=videoService.getAllVideos();
		
		//m.addAttribute("videos",videos);
		int pageSize=2;
		
		Page<Video> pagiData=videoService.getPagiData(page, pageSize);
		m.addAttribute("videos",pagiData);
		m.addAttribute("currentPage",pagiData.getNumber());
		m.addAttribute("totalPages",pagiData.getTotalPages());
		return "Yoga";
	}
	
	@GetMapping("/MakePayment")
	public String makePayment(@Valid @ModelAttribute("PatientDetails")PatientDetails pd,BindingResult res,Model m)
	{
		/*
		if(res.hasErrors())
		{
			System.out.println(res);
			return "AyurvedicConsultation";
		}
		m.addAttribute("title","Make Payment-Sai Clinic");
		pd.setTreatment("Ayurvedic");
		System.out.println(pd);
		PatientDetails result= this.patientDetailsRepo.save(pd);
		m.addAttribute("PatientDetails",result);
		*/
		m.addAttribute("title","Make Payment-Sai Clinic");
		return "MakePayment";
	}
	
	/*
	@PostMapping("/MakePaymentAlopathyConsultation")
	public String makePayment2(@Valid @ModelAttribute("PatientDetails")PatientDetails pd,BindingResult res,Model m)
	{
		if(res.hasErrors())
		{
			System.out.println(res);
			return "AlopathyConsultation";
		}
		m.addAttribute("title","Make Payment-Sai Clinic");
		pd.setTreatment("Alopathy");
		System.out.println(pd);
		
		PatientDetails result= this.patientDetailsRepo.save(pd);
		m.addAttribute("PatientDetails",result);
		
		return "MakePayment2";
	}
	*/
	
	@RequestMapping(value="/PaymentGateway")
	public String paymentGateway()
	{
		
		
		return "PaymentGateway";
	}
	
	/*
	//creating order from payment
	@PostMapping("/create_order")
	@ResponseBody
	public String createOrder(@RequestBody Map<String, Object> data,Principal principal) throws Exception
	{
		System.out.println("Order function executed....");
		System.out.println(data);
		
		int amt=Integer.parseInt(data.get("amount").toString());
		
		var client=new RazorpayClient("rzp_test_QJA1JAIynqVJBn","zROOrGe7O22JKquQ46nATi2O");
		
		JSONObject ob=new JSONObject();
		ob.put("amount", amt*100);
		ob.put("currency", "INR");
		ob.put("receipt", "txn_235425");
		
		//creating new order
		Order order=client.Orders.create(ob);
		System.out.println(order);
		
		//save the order in database
		MyOrder myOrder=new MyOrder();
		
		
		
		myOrder.setAmount("100");
		myOrder.setOrderId(order.get("id"));
		myOrder.setStatus("created");
		myOrder.setPatient(this.patientLoginDetailsRepo.getPatientBtUserName(principal.getName()));
		myOrder.setReceipt(order.get("receipt"));
		
		
		//prac
		// Creating a new PatientDetails
	    PatientDetails patientDetails = new PatientDetails();
	    
	    // Set the association in both entities
	    patientDetails.setOrder(myOrder);
	    myOrder.setPatientDetails(patientDetails);
	    
	    // Save the MyOrder entity first
	    myOrderRepo.save(myOrder);
	    
	    // Update the order column in PatientDetails
	    patientDetails.setOrder(myOrder);

		
		
		
		
		//this.myOrderRepo.save(myOrder);
		
		
		return order.toString();
	}
	*/
	
	@PostMapping("/create_order")
	@ResponseBody
	public String createOrder(@RequestBody Map<String, Object> data, Principal principal,HttpServletRequest request) throws Exception {
	    System.out.println("Order function executed....");
	    System.out.println(data);
	    
	    int amt = Integer.parseInt(data.get("amount").toString());
	    
	    var client = new RazorpayClient("rzp_test_QJA1JAIynqVJBn", "zROOrGe7O22JKquQ46nATi2O");
	    
	    JSONObject ob = new JSONObject();
	    ob.put("amount", amt * 100);
	    ob.put("currency", "INR");
	    ob.put("receipt", "txn_235425");
	    
	    // Creating new order
	    Order order = client.Orders.create(ob);
	    System.out.println(order);
	    
	    //String email=request.getParameter("email");
	    //String code=request.getParameter("code");
	    String email = (String) data.get("email");
	    System.out.println("Email is : "+email);
	    
	    // Retrieve the existing PatientDetails entity
	    PatientDetails patientDetails = patientDetailsRepo.findByEmail(email); // Use appropriate method to retrieve the existing entity
	    //PatientDetails patientDetails2=patientDetailsRepo.findByVerificationCode(code);
	    
	    if (patientDetails != null) {
	        // Creating a new MyOrder
	        MyOrder myOrder = new MyOrder();
	        myOrder.setAmount("100"); // Use the actual amount
	        myOrder.setOrderId(order.get("id")); // Set orderId obtained from Razorpay order
	        myOrder.setStatus("created");
	        myOrder.setPatient(this.patientLoginDetailsRepo.getPatientBtUserName(principal.getName()));
	        myOrder.setReceipt(order.get("receipt"));
	        this.myOrderRepo.save(myOrder);
	        // Set the association in the PatientDetails entity
	        patientDetails.setOrder(myOrder);
	        
	        // Update any other fields in PatientDetails if needed
	        // patientDetails.setXXX(...);
	        
	        // Save the PatientDetails entity with the updated order information
	        //this.myOrderRepo.save(myOrder);
	        patientDetailsRepo.save(patientDetails);
	    } else {
	        return order.toString();
	    }
	    
	    return order.toString();
	}

	@PostMapping("/update_order")
	public ResponseEntity<?> updateOrder(@RequestBody Map<String, Object> data)
	{
		MyOrder myorder=this.myOrderRepo.findByOrderId(data.get("order_id").toString());
		
		myorder.setPaymentId(data.get("payment_id").toString());
		myorder.setStatus(data.get("status").toString());
		
		this.myOrderRepo.save(myorder);
		
		
		
		System.out.println(data);
		return ResponseEntity.ok(Map.of("msg","updated"));
	}
	
	@GetMapping("/PatientLobby")
	public String patientLobby()
	{
		return "lobby2";
	}
	
	@GetMapping("/ScheduleDetails")
	public String scheduleDetails()
	{
		return "ScheduleDetails";
	}
	
	@PostMapping("/ScheduleVerify")
	public String scheduleVerify(HttpServletRequest req,Model m)
	{
		String meetingId=req.getParameter("meetingId");
		
		AppointmentDetails ad=appointmentDetailsRepo.findByPatientName(meetingId);
		
		if(ad!=null)
		{
			m.addAttribute("meetingId",ad.getPatientName());
			return "lobby";
		}
		
		
		return "ScheduleDetails";
	}
}
