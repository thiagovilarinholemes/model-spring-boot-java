package com.relationship.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.relationship.entities.Authorization;

@Repository
public interface AuthorizationRepository extends JpaRepository<Authorization, Long> {

}
