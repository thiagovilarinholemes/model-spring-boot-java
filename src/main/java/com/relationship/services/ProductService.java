package com.relationship.services;


import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.relationship.entities.Product;
import com.relationship.repositories.ProductRepository;
import com.relationship.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {

	@Autowired
	ProductRepository repository;
	
	/** Method List All Tutorials */
	@Transactional(readOnly = true)
	public List<Product> findAll(){
		return repository.findAllByOrderByNameAsc();
	}
	
	/** Method List Tutorial ID */
	@Transactional(readOnly = true)
	public Product findById(Long id){
		Optional<Product> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ResourceNotFoundException(id));
	}
	
	/** Method List Tutorials Title */
	@Transactional(readOnly = true)
	public List<Product> findByNameContaining(String name){
		JSONObject json = new JSONObject(name);
		return repository.findByNameContaining(json.getString("name"));
	}
	
	/** Method List Tutorial Tag */
	@Transactional(readOnly = true)
	public List<Product> findByTagLike(String tag){
		JSONObject json = new JSONObject(tag);
		return repository.searchTag(json.getString("tag"));
	}
	
	/** Method Insert Product */
	@Transactional
	public Product insert(Product obj) {
		return repository.save(obj);
	}
}


