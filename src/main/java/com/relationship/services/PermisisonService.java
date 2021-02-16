package com.relationship.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.relationship.entities.Permission;
import com.relationship.repositories.PermissionRepository;

@Service
public class PermisisonService {

	@Autowired
    PermissionRepository repository;

    public List<Permission> findAll(){
        return repository.findAll();
    }
}
