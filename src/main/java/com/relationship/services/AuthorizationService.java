package com.relationship.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.relationship.entities.Authorization;
import com.relationship.repositories.AuthorizationRepository;

@Service
public class AuthorizationService {

	@Autowired
    AuthorizationRepository repository;

    public List<Authorization> findAll(){
        return repository.findAll();
    }

}
