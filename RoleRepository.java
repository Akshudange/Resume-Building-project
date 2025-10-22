package com.example.Resume_system.Repository;

import com.example.Resume_system.Entity.Role;
import com.example.Resume_system.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);

}
