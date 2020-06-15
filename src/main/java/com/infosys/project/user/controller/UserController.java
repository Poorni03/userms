package com.infosys.project.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.infosys.project.user.dto.BuyerDTO;
import com.infosys.project.user.dto.CartDTO;
import com.infosys.project.user.dto.LoginDTO;

import com.infosys.project.user.dto.ProductDTO;
import com.infosys.project.user.dto.SellerDTO;
import com.infosys.project.user.service.BuyerService;
import com.infosys.project.user.service.SellerService;


@RestController
@RequestMapping(value="/api/")
@EnableAutoConfiguration
@CrossOrigin
public class UserController {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public RestTemplate template;
	
	@Autowired
	public BuyerService buyerservice;
	@Autowired
	public SellerService sellerservice;
	
	
	@PostMapping(value="buyer/register",  consumes = MediaType.APPLICATION_JSON_VALUE)
	public String registerUser(@RequestBody BuyerDTO buyerDTO) {
		try {
		logger.info("Registration request for user {}", buyerDTO);
		System.out.println("before ser");
		buyerservice.registerUser(buyerDTO);
		System.out.println("after ser");
		return "Successful";
	}catch(Exception e) {
		return("Not sucessfulL");
	}
	}
	@PostMapping(value="seller/register",  consumes = MediaType.APPLICATION_JSON_VALUE)
	public String registerSeller(@RequestBody SellerDTO sellerDTO) {
		try {
		logger.info("Registration request for user {}", sellerDTO);
		sellerservice.registerSeller(sellerDTO);
		return "Successful";
	}catch(Exception e) {
		return("Not sucessful");
	}}


	@PostMapping(value = "buyer/login",consumes = MediaType.APPLICATION_JSON_VALUE)
	public String Buyerlogin(@RequestBody LoginDTO loginDTO) {
		logger.info("Login request for customer {} with password {}", loginDTO.getEmail(),loginDTO.getPassword());
		if(buyerservice.Buyerlogin(loginDTO)) {
			return "Logged in successfully!";
		}
		return "Incorrect emailid or password!";
	}
	

	@PostMapping(value = "seller/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String Sellerlogin(@RequestBody LoginDTO loginDTO) {
		logger.info("Login request for customer {} with password {}", loginDTO.getEmail(),loginDTO.getPassword());
		if(sellerservice.Sellerlogin(loginDTO)) {
			return "Logged in successfully!";
		}
		return "Incorrect emailid or password!";
	}
	


	@DeleteMapping(value="removebuyer/{buyerid}", consumes= MediaType.APPLICATION_JSON_VALUE)
	public String removebuyer(@PathVariable Integer buyerid) {
		logger.info("Request for order removal by buyer {}", buyerid);
		buyerservice.removebuyer(buyerid);
		return "Deleted successfully";
		
	}
	@DeleteMapping(value="removeseller/{sellerid}", consumes= MediaType.APPLICATION_JSON_VALUE)
	public String removeseller(@PathVariable Integer sellerid) {
		logger.info("Request for order removal by seller {}", sellerid);
		sellerservice.removeseller(sellerid);
		return "Deleted successfully";
		
		
	} 
	
	@PostMapping(value="add/wishlist/{prodid}/{productname}",  consumes = MediaType.APPLICATION_JSON_VALUE)
	public String addtowishlist(@PathVariable Integer prodid,@PathVariable String productname) {
		try {
			logger.info("Add products to wishlist request {}", productname);
			ProductDTO prodDTO=template.getForObject("http://PRODUCTMS"+"/products/productid/"+prodid,ProductDTO.class);
			buyerservice.addtowishlist(prodDTO);
			return "Added successfully";
		
		}catch(Exception e) {
			return "Unable to add";
		}
		}
		
	@DeleteMapping(value="delete/{buyerid}")
	public String delete(@PathVariable Integer buyerid) {
		try {
			buyerservice.delete(buyerid);
			return "deleted successfully";
		}catch(Exception e) {
			return "Unable to delete";
		}}
		

		@PostMapping(value="add/cart/{prodid}/{productname}/{quantity}",  consumes = MediaType.APPLICATION_JSON_VALUE)
		public String addtocart(@PathVariable Integer prodid,@PathVariable String productname, @PathVariable Integer quantity) {
			try {
				logger.info("Add products to order request {}", productname);
				ProductDTO prodDTO=template.getForObject("http://PRODUCTMS"+"/products/productid/"+prodid,ProductDTO.class);
				CartDTO cart=new CartDTO();
				cart.setProdid(prodDTO.getProdid());
				cart.setQuantity(quantity);
				buyerservice.addtocart(cart);
				return "Added successfully";
			
			}catch(Exception e) {
				return "Unable to add";
			}}
		
		@PostMapping(value="add/products/{prodid}/{quantity}",consumes = MediaType.APPLICATION_JSON_VALUE)
		public String order(@PathVariable Integer prodid,@PathVariable Integer quantity) {
			try {
				logger.info("Add products to Order request {}", prodid);
				
				String orderDTO=template.getForObject("http://ORDERMS"+"/add/products/"+prodid+quantity,String.class);
				return "Added successfully";
			
			}catch(Exception e) {
				return "Unable to add";
			}
			
		}

}
