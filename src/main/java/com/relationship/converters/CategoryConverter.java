package com.relationship.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.relationship.dto.CategoryDTO;
import com.relationship.entities.Category;

@Component
public class CategoryConverter {

	public CategoryDTO entityToDto(Category category) {
		CategoryDTO dto = new CategoryDTO();
		dto.setId(category.getId());
		dto.setName(category.getName());
		dto.setDescription(category.getDescription());
		dto.setProducts(category.getProducts());
		return dto;
		
//		ModelMapper mapper = new ModelMapper();
//		CategoryDTO map = mapper.map(category, CategoryDTO.class);
//		return map;
	}
	
	public List<CategoryDTO> entityToDto(List<Category> category ){
		return	category.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
	}
	
	public Category dtoToEntity(CategoryDTO dto) {
		Category category = new Category();
		category.setId(dto.getId());
		category.setName(dto.getName());
		category.setDescription(dto.getDescription());
		category.setProducts(dto.getProducts());
		return category;
		
//		ModelMapper mapper = new ModelMapper();
//		Category map = mapper.map(dto, Category.class);
//		return map;
	}
	
	public List<Category> dtoToEntity(List<CategoryDTO> dto)
	{
		return dto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
	}
}
