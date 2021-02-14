package com.relationship.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.relationship.dto.CategoryDTO;
import com.relationship.dto.ProductDTO;
import com.relationship.entities.Category;
import com.relationship.entities.Product;

@Component
public class ProductConverter {

	public ProductDTO entityToDto(Product product) {
		ProductDTO dto = new ProductDTO();
		dto.setId(product.getId());
		dto.setName(product.getName());
		dto.setDescription(product.getDescription());
		dto.setPrice(product.getPrice());
		dto.setActive(product.getActive());
		dto.setTag(product.getTag());
		dto.setCategory(product.getCategory());
		return dto;
		
//		ModelMapper mapper = new ModelMapper();
//		ProductDTO map = mapper.map(product, ProductDTO.class);
//		return map;
	}
	
	public List<ProductDTO> entityToDto(List<Product> product ){
		return	product.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
	}
	
	public Product dtoToEntity(ProductDTO dto) {
		Product product = new Product();
		product.setId(dto.getId());
		product.setName(dto.getName());
		product.setDescription(dto.getDescription());
		product.setPrice(dto.getPrice());
		product.setActive(dto.getActive());
		product.setTag(dto.getTag());
		product.setCategory(dto.getCategory());
		return product;
//		
//		ModelMapper mapper = new ModelMapper();
//		Product map = mapper.map(dto, Product.class);
//		return map;
	}
	
	public List<Product> dtoToEntity(List<ProductDTO> dto)
	{
		return dto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
	}
}
