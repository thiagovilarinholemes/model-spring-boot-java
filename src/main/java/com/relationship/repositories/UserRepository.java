package com.relationship.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.relationship.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findByUserName(String username);
}
