package com.relationship.services;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.relationship.entities.Category;
import com.relationship.repositories.CategoryRepository;
import com.relationship.services.exceptions.DatabaseException;
import com.relationship.services.exceptions.ResourceNotFoundException;

import org.json.JSONObject;

@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository repository;
	
	/** Method List All Category */
	@Transactional(readOnly = true)
	public List<Category> findAll(){
		return repository.findAllByOrderByNameAsc();
	}
	
	/** Method List Category ID */
	@Transactional(readOnly = true)
	public Category findById(Long id){
		Optional<Category> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ResourceNotFoundException(id));
	}
	
	/** Method List Category Title */
	@Transactional(readOnly = true)
	public List<Category> findByNameContaining(String name){
		JSONObject json = new JSONObject(name);
		return repository.findByNameContaining(json.getString("name"));
	}
		
	/** Method Insert Category */
	@Transactional
	public Category insert(Category obj) {
		return repository.save(obj);
	}
	
	/** Method Update Category */
	@Transactional
	public Category update(Long id, Category obj){
        try {
        	Category entity = repository.getOne(id);
      
            /** Call Method updateDate */
            updateData(entity, obj);

            return repository.save(entity);
        }
        catch (EntityNotFoundException e){
            throw new DatabaseException(e.getMessage());
        }
    }
	
	/** Method Delete Tutorial */
//	public String delete(Long id){
//        try {
//            repository.deleteById(id);
//            return "Deletado com sucesso!!!";
//        }
//        catch (EmptyResultDataAccessException e){
//            throw new ResourceNotFoundException(id);
//        }
//        catch (DataIntegrityViolationException e){
//            throw new DatabaseException(e.getMessage());
//        }
//    }
	
	/** Method Update Data Category  */
	public void updateData(Category entity, Category obj){
		entity.setName(obj.getName());
		entity.setDescription(obj.getDescription());
		entity = repository.save(entity);
	}
	
}
