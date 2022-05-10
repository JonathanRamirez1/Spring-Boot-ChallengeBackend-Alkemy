package com.api.ChallengeBackend.repositories;

import com.api.ChallengeBackend.models.ERole;
import com.api.ChallengeBackend.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);
}
