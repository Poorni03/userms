package com.infosys.project.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.project.user.entity.WishListEntity;

@Repository
public interface WishListRepository extends JpaRepository<WishListEntity, Integer>{

}
