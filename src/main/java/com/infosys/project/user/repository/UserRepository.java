package com.infosys.project.user.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.project.user.entity.BuyerEntity;


@Repository
public interface UserRepository extends JpaRepository<BuyerEntity, Integer>{

	BuyerEntity findByEmail(String email);

	


	//Optional<Buyer> findById(Integer buyerId);

}
