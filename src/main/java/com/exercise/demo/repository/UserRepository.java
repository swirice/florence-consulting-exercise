package com.exercise.demo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exercise.demo.entity.User;

public interface UserRepository extends JpaRepository<User, UUID> {

}
