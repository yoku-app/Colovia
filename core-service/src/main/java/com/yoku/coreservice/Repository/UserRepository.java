package com.yoku.coreservice.Repository;

import com.yoku.coreservice.Entity.User.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findByUserId(UUID userId);
}
