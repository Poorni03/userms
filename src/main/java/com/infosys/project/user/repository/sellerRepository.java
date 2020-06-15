package com.infosys.project.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.project.user.entity.SellerEntity;


public interface sellerRepository extends JpaRepository<SellerEntity, Integer>{

SellerEntity findByEmail(String email);

}
