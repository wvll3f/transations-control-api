package com.wdev.secutity.repositories;

import com.wdev.secutity.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User,UUID> {

    Optional<User> findByUsername(String Username);

}
