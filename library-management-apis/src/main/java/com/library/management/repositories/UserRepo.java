package com.library.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.management.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {
}
