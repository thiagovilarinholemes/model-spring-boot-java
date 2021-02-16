package com.relationship.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String userName;
    private String password;
}
