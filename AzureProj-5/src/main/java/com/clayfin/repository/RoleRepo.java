package com.clayfin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clayfin.model.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

}
