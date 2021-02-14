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

import com.relationship.converters.CategoryConverter;
import com.relationship.dto.CategoryDTO;
import com.relationship.entities.Category;
import com.relationship.repositories.CategoryRepository;
import com.relationship.services.CategoryService;

@RestController
@RequestMapping(value = "/api")
public class CategoryController {

	@Autowired
	CategoryService service;
	
	@Autowired
	CategoryRepository repository;
	
	@Autowired
	CategoryConverter converter;
	
	/** List All Category */
	@GetMapping("/categories")
	public ResponseEntity<List<CategoryDTO>> getAllCategories(){
		List<Category> list = service.findAll();
		return ResponseEntity.ok().body( converter.entityToDto(list) );
	}
	
	/** converter.entityToDto(list) */
	
	/** List Category ID*/
	@GetMapping("/category/{id}")
	public ResponseEntity<CategoryDTO> getByCategoryId(@PathVariable("id") Long id){
		Category obj = service.findById(id);
		return ResponseEntity.ok().body(converter.entityToDto(obj));
	}
	
	/** List Category Title */
	@GetMapping("/categories/name")
	public ResponseEntity<List<CategoryDTO>> getAllTutorialsTitle(@RequestBody(required = false) String name){
		List<Category> list = service.findByNameContaining(name);
		return ResponseEntity.ok().body(converter.entityToDto(list));
	}
	
	/** Insert Category */
	@PostMapping("/category")
	public ResponseEntity<CategoryDTO> createTutorial(@RequestBody @Valid CategoryDTO dto){
		Category obj = converter.dtoToEntity(dto);
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
	
	/** Response BAD REQUEST */
//	@ResponseStatus(value = HttpStatus.NOT_FOUND) 
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public String handleNotFound(MethodArgumentNotValidException ex) {
//		String errors = "Erro na requisição"; 
//		return errors;
//	}
}
