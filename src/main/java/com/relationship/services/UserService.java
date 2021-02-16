package com.relationship.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.relationship.entities.User;
import com.relationship.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
    UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }
}
