package com.relationship.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.relationship.entities.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

}
