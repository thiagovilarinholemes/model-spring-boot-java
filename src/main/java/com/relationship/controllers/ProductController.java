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

import com.relationship.converters.ProductConverter;
import com.relationship.dto.ProductDTO;
import com.relationship.entities.Product;
import com.relationship.repositories.ProductRepository;
import com.relationship.services.ProductService;

@RestController
@RequestMapping(value = "/api")
public class ProductController {

	@Autowired
	ProductService service;
	
	@Autowired
	ProductRepository repository;
	
	@Autowired
	ProductConverter converter;
	
	/** List All Product */
	@GetMapping("/products")
	public ResponseEntity<List<ProductDTO>> getAllProducts(){
		List<Product> list = service.findAll();
		return ResponseEntity.ok().body( converter.entityToDto(list) );
	}
	
	/** List Product ID*/
	@GetMapping("/product/{id}")
	public ResponseEntity<ProductDTO> getByProductId(@PathVariable("id") Long id){
		Product obj = service.findById(id);
		return ResponseEntity.ok().body(converter.entityToDto(obj));
	}
	
	/** List Product Title */
	@GetMapping("/products/name")
	public ResponseEntity<List<ProductDTO>> getAllTutorialsTitle(@RequestBody(required = false) String name){
		List<Product> list = service.findByNameContaining(name);
		return ResponseEntity.ok().body(converter.entityToDto(list));
	}
	
	/** List Product Tag */
	@GetMapping("/products/tag")
	public ResponseEntity<List<ProductDTO>> findByTagLike(@RequestBody(required = false) String tag){
		List<Product> list = service.findByTagLike(tag);
		return ResponseEntity.ok().body(converter.entityToDto(list));
	}
	
	/** Insert Category */
	@PostMapping("/product")
	public ResponseEntity<ProductDTO> createTutorial(@RequestBody @Valid ProductDTO dto){
		Product obj = converter.dtoToEntity(dto);
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
