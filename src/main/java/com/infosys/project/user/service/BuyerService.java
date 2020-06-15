package com.infosys.project.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.infosys.project.user.dto.BuyerDTO;
import com.infosys.project.user.dto.CartDTO;
import com.infosys.project.user.dto.LoginDTO;
import com.infosys.project.user.dto.ProductDTO;
import com.infosys.project.user.dto.SellerDTO;
import com.infosys.project.user.dto.WishListDTO;
import com.infosys.project.user.entity.BuyerEntity;
import com.infosys.project.user.entity.CartEntity;
import com.infosys.project.user.entity.SellerEntity;
import com.infosys.project.user.entity.WishListEntity;
import com.infosys.project.user.repository.CartRepository;
import com.infosys.project.user.repository.UserRepository;
import com.infosys.project.user.repository.WishListRepository;
import com.infosys.project.user.repository.sellerRepository;
import com.infosys.project.user.validator.Validator;



@Service
public class BuyerService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public UserRepository repo;
	
	@Autowired
	public CartRepository cartrepo;
	
	@Autowired
	public WishListRepository repos;
	
	public String registerUser(BuyerDTO buyerDTO) throws Exception {
		logger.info("Registration request for user {}", buyerDTO);
		System.out.println(" not ok");
		Validator.buyerValidate(buyerDTO);
		System.out.println("ok");
		BuyerEntity be=buyerDTO.createEntity();
		System.out.println("doctor");
		repo.save(be);
		return("new user new created");
		}


		public boolean Buyerlogin(LoginDTO loginDTO) {
		
		logger.info("Login request for customer {} with password {}", loginDTO.getEmail(), loginDTO.getPassword());
		Validator.emailid(loginDTO.getEmail());
		BuyerEntity optBuyer = repo.findByEmail(loginDTO.getEmail());
		if (optBuyer.getPassword().equals(loginDTO.getPassword())) {
				return true;
			}
		return false;
	}
		

		
		public void removebuyer(Integer buyerid) {
			repo.deleteById(buyerid);
		}
		
		public void addtowishlist(ProductDTO prodDTO) {
			System.out.println("in service");
			WishListDTO wishlist=new WishListDTO();
			wishlist.setBuyerid(543);
			wishlist.setProdid(prodDTO.getProdid());
			
		
			WishListDTO wo= new WishListDTO();
			WishListEntity we= wishlist.createEntity();
			repos.save(we);

			
		}
		
		
		public void addtocart(CartDTO cartDTO) {
			System.out.println("in service");
			CartDTO cart1=new CartDTO();
			cart1.setBuyerid(500);
			cart1.setProdid(cartDTO.getProdid());
			cart1.setQuantity(cartDTO.getQuantity());
			CartEntity cart2= cart1.createEntity();
			cartrepo.save(cart2);
		}
		
		public void delete(Integer buyerid) {
			repos.deleteById(buyerid);
			
}
}
		