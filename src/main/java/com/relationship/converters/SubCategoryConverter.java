package com.relationship.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.relationship.dto.SubCategoryDTO;
import com.relationship.entities.SubCategory;

@Component
public class SubCategoryConverter {

	public SubCategoryDTO entityToDto(SubCategory subcategory) {
		SubCategoryDTO dto = new SubCategoryDTO();
		dto.setId(subcategory.getId());
		dto.setName(subcategory.getName());
		dto.setDescription(subcategory.getDescription());
		dto.setCategory(subcategory.getCategory());
		dto.setProducts(subcategory.getProducts());
		return dto;
		
//		ModelMapper mapper = new ModelMapper();
//		CategoryDTO map = mapper.map(category, CategoryDTO.class);
//		return map;
	}
	
	public List<SubCategoryDTO> entityToDto(List<SubCategory> subcategory ){
		return	subcategory.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
	}
	
	public SubCategory dtoToEntity(SubCategoryDTO dto) {
		SubCategory subcategory = new SubCategory();
		subcategory.setId(dto.getId());
		subcategory.setName(dto.getName());
		subcategory.setDescription(dto.getDescription());
		subcategory.setCategory(dto.getCategory());
		subcategory.setProducts(dto.getProducts());
		return subcategory;
		
//		ModelMapper mapper = new ModelMapper();
//		Category map = mapper.map(dto, Category.class);
//		return map;
	}
	
	public List<SubCategory> dtoToEntity(List<SubCategoryDTO> dto)
	{
		return dto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
	}
	
}
