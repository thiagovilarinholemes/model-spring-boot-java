package com.relationship.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.relationship.entities.SubCategory;
import com.relationship.repositories.SubCategoryRepository;
import com.relationship.services.exceptions.DatabaseException;
import com.relationship.services.exceptions.ResourceNotFoundException;

@Service
public class SubCategoryService {

	@Autowired
	SubCategoryRepository repository;
	
	/** Method List All Category */
	@Transactional(readOnly = true)
	public List<SubCategory> findAll(){
		return repository.findAllByOrderByNameAsc();
	}
	
	/** Method List Category ID */
	@Transactional(readOnly = true)
	public SubCategory findById(Long id){
		Optional<SubCategory> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ResourceNotFoundException(id));
	}
	
	/** Method List Category Title */
	@Transactional(readOnly = true)
	public List<SubCategory> findByNameContaining(String name){
		JSONObject json = new JSONObject(name);
		return repository.findByNameContaining(json.getString("name"));
	}
	
	/** Method Insert SubCategory */
	@Transactional
	public SubCategory insert(SubCategory obj) {
		return repository.save(obj);
	}
	
	/** Method Update SubCategory */
	@Transactional
	public SubCategory update(Long id, SubCategory obj){
        try {
        	SubCategory entity = repository.getOne(id);
      
            /** Call Method updateDate */
            updateData(entity, obj);

            return repository.save(entity);
        }
        catch (EntityNotFoundException e){
            throw new DatabaseException(e.getMessage());
        }
    }
	
	/** Method Update Data SubCategory  */
	public void updateData(SubCategory entity, SubCategory obj){
		entity.setName(obj.getName());
		entity.setDescription(obj.getDescription());
		entity = repository.save(entity);
	}
}
