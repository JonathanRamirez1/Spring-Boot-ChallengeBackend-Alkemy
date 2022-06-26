package com.api.ChallengeBackend.dao.repository;

import com.api.ChallengeBackend.domain.models.ERole;
import com.api.ChallengeBackend.domain.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);
}