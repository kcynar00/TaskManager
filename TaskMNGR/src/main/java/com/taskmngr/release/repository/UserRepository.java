package com.taskmngr.release.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskmngr.release.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
 Optional<User> findByUsername(String username);
}
