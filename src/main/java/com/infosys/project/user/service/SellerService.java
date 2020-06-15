package com.infosys.project.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.project.user.dto.LoginDTO;
import com.infosys.project.user.dto.SellerDTO;
import com.infosys.project.user.entity.SellerEntity;
import com.infosys.project.user.repository.sellerRepository;
import com.infosys.project.user.validator.SellerValidator;
import com.infosys.project.user.validator.Validator;

@Service
public class SellerService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public SellerService sellerservice;

	@Autowired
	public sellerRepository sellerrepo;
	

	public String registerSeller(SellerDTO sellerDTO) throws Exception {
		try {
			SellerValidator.sellerValidate(sellerDTO);
			logger.info("Registration request for user {}", sellerDTO);
			SellerEntity be=sellerDTO.createEntity();
			sellerrepo.save(be);
			return ("new user new created");
		}
		catch(Exception e) {
		throw new Exception ("Exception occured");
		}
	}
	
	public boolean Sellerlogin(LoginDTO loginDTO) {
		
		logger.info("Login request for customer {} with password {}", loginDTO.getEmail(), loginDTO.getPassword());
		Validator.emailid(loginDTO.getEmail());
		SellerEntity optBuyer = sellerrepo.findByEmail(loginDTO.getEmail());
		if (optBuyer.getPassword().equals(loginDTO.getPassword())) {
				return true;
			}
		return false;
	}
	
	public void removeseller(Integer sellerid) {
		sellerrepo.deleteById(sellerid);
	}
}


