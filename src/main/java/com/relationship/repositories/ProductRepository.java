package com.relationship.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.relationship.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	List<Product> findAllByOrderByNameAsc();
	
	List<Product> findByNameContaining(String name);
	
//	Demo insert
//	Demo
//	@Modifying  // Utilizado para fazer update, insert, delete
//	@Transactional 
//	@Query(
//			value = 
//			    "INSERT INTO tb_product_subcategory (product_id, subcategory_id) VALUES (:productId, :subcategoryId)",
//			nativeQuery = true)
//	Integer insertTbProductTbSubcategory(Long productId, Long subcategoryId);
	

	@Transactional 
	@Query(
		value =
//			"Select p from Product p where p.tag like %?1%")
			"Select * from tb_product where tag Ilike %?1%",
		nativeQuery = true)
	List<Product> searchTag(String tag);
	
//	Demo Use Like
//	List<Product> findByTagLike(String tag);
//	
//	List<Product> findByTagContainingIgnoreCase(String tag);
	
	
}
