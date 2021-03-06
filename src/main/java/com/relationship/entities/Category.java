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
@Table(name =  "tb_category")
public class Category implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "description", nullable = false)
	private String description;
		
	@Column(updatable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar createdDate = Calendar.getInstance();
	

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar updatedDate = Calendar.getInstance();
	

	@JsonIgnore
	@OneToMany(mappedBy = "category", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<SubCategory> subcategory;
	
	public Category(Long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.createdDate = Calendar.getInstance();
	}
	
}





