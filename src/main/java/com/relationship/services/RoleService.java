package com.relationship.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.relationship.entities.Role;
import com.relationship.repositories.RoleRepository;

@Service
public class RoleService {

	@Autowired
    RoleRepository repository;

    public List<Role> findAll(){
        return repository.findAll();
    }

}
