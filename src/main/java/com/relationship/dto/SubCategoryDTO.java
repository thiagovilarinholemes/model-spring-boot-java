package com.relationship.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.relationship.entities.Category;
import com.relationship.entities.Product;

import lombok.Data;

@Data
public class SubCategoryDTO implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank(message = "O campo NOME é obrigatório!")
	private String name;
	
	@NotBlank(message = "O campo DESCRIÇÃO é obrigatório!")
	private String description;
	
	private Category category;
	
	private List<Product> products = new ArrayList<Product>();

}
