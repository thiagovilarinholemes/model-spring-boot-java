package com.relationship.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.relationship.converters.SubCategoryConverter;
import com.relationship.dto.SubCategoryDTO;
import com.relationship.entities.SubCategory;
import com.relationship.services.SubCategoryService;

@RestController
@RequestMapping(value = "/api")
public class SubCategoryController {
	
	@Autowired
	SubCategoryService service;
	
	@Autowired
	SubCategoryConverter converter;
	
	/** List All SubCategory */
	@GetMapping("/subcategories")
	public ResponseEntity<List<SubCategoryDTO>> getAllCategories(){
		List<SubCategory> list = service.findAll();
		return ResponseEntity.ok().body( converter.entityToDto(list) );
	}
	
	
	/** List SubCategory ID*/
	@GetMapping("/subcategory/{id}")
	public ResponseEntity<SubCategoryDTO> getByCategoryId(@PathVariable("id") Long id){
		SubCategory obj = service.findById(id);
		return ResponseEntity.ok().body(converter.entityToDto(obj));
	}
	
	/** List SubCategory Title */
	@GetMapping("/subcategories/name")
	public ResponseEntity<List<SubCategoryDTO>> getAllTutorialsTitle(@RequestBody(required = false) String name){
		List<SubCategory> list = service.findByNameContaining(name);
		return ResponseEntity.ok().body(converter.entityToDto(list));
	}
	
	/** Insert Category */
	@PostMapping("/subcategory")
	public ResponseEntity<SubCategoryDTO> createTutorial(@RequestBody @Valid SubCategoryDTO dto){
		SubCategory obj = converter.dtoToEntity(dto);
		obj = service.insert(obj);
		return ResponseEntity.ok().body(converter.entityToDto(obj));
	}
	
	
	
	/** Response BAD REQUEST */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}

}
