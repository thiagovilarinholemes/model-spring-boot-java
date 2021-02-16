package com.relationship.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name =  "tb_subcategory")
public class SubCategory implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name",  nullable = false)
	private String name;
	
	@Column(name = "description",  nullable = false)
	private String description;
	
	@Column(updatable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar createdDate = Calendar.getInstance();
	

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar updatedDate = Calendar.getInstance();
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "category_id")
	private Category category;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "subcategories", fetch = FetchType.EAGER, cascade = CascadeType.ALL) // Nome do relacionamento da tabela estrangeira, list
	private List<Product> products;

	public SubCategory(Long id, String name, String description, Category category) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.category = category;
		this.createdDate = Calendar.getInstance();
	}
	
	
}
