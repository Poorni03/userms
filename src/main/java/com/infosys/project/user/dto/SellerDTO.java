package com.infosys.project.user.dto;


import com.infosys.project.user.entity.SellerEntity;

public class SellerDTO {
	Integer sellerId;
	String name;
	String email;
	String phoneNumber;
	String password;
	Boolean isActive;
	public Integer getSellerId() {
		return sellerId;
	}
	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public SellerDTO() {
		super();
	}
	
	// Converts Entity into DTO
	public static SellerDTO valueOf(SellerEntity seller) {
			SellerDTO sellerdto = new SellerDTO();
			sellerdto.setSellerId(seller.getSellerId());
			sellerdto.setName(seller.getName());
			sellerdto.setEmail(seller.getEmail());
			sellerdto.setPhoneNumber(seller.getPhoneNumber());
			sellerdto.setPassword(seller.getPassword());
			sellerdto.setIsActive(seller.getIsActive());
			return sellerdto;
		}
	@Override
	public String toString() {
		return "SellerDTO [SellerId="+ sellerId + "Name=" +name+"Email="+ email +"Phonenumber="+ phoneNumber +"Password"+ password +
				"IsActive="+ isActive + "]";
	}
	public SellerEntity createEntity() {
		SellerEntity seller=new SellerEntity();
//		seller.setSellerId(this.getSellerId());
		seller.setName(this.getName());
		seller.setEmail(this.getEmail());
		seller.setPassword(this.getPassword());
		seller.setPhoneNumber(this.getPhoneNumber());
		seller.setIsActive(this.getIsActive());
		return seller;
	}
		
		

}
