package com.relationship.entities;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name =  "tb_product")
public class Product implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "price", nullable = false)
	private Double price;
	
	@Column(name = "active", nullable = false)
	private Boolean active;
	
	@Column(name = "tag", nullable = false)
	private String tag;
	
	@Column(updatable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar createdDate = Calendar.getInstance();
	

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar updatedDate = Calendar.getInstance();
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "category_id")
	private Category category;
}



