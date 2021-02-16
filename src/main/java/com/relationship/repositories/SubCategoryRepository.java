package com.relationship.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.relationship.entities.SubCategory;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long>{
	
	List<SubCategory> findAllByOrderByNameAsc();
	
	List<SubCategory> findByNameContaining(String name);
}
