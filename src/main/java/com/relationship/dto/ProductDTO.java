package com.relationship.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.relationship.entities.SubCategory;

import lombok.Data;

@Data
public class ProductDTO implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotBlank(message = "O campo NOME é obrigatório!")
	private String name;
	
	@NotBlank(message = "O campo DESCRIÇÃO é obrigatório!")
	private String description;
	
	private Double price;
	
	private Boolean active;
	
	@NotBlank(message = "O campo TAG é obrigatório!")
	private String tag;
	
	private List<SubCategory> subcategories;
}
