package com.infosys.project.user.dto;

import com.infosys.project.user.entity.WishListEntity;

public class WishListDTO {
	Integer buyerid;
	Integer prodid;
	
	public Integer getBuyerid() {
		return buyerid;
	}
	public void setBuyerid(Integer buyerid) {
		this.buyerid = buyerid;
	}
	public Integer getProdid() {
		return prodid;
	}
	public void setProdid(Integer prodid) {
		this.prodid = prodid;
	}
	
	//Converts Entity into DTO
    public static WishListDTO valueOf(WishListEntity wishlist) {
        WishListDTO wishlistDTO=new WishListDTO();
        wishlistDTO.setBuyerid(wishlist.getBuyerId());
        wishlistDTO.setProdid(wishlist.getProdId());
        return wishlistDTO;
}
    //Converts DTO into Entity
        public  WishListEntity  createEntity() {
        	WishListEntity wishlist=new WishListEntity();
            wishlist.setBuyerId(this.getBuyerid());
            wishlist.setProdId(this.getProdid());
            return wishlist;
    }
	
	@Override
	public String toString() {
		return "WishlistDTO [BuyerId="+ buyerid + "ProdId=" +prodid+"]";
	}
	

}
