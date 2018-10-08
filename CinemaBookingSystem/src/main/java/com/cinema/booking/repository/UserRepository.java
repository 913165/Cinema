package com.cinema.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cinema.booking.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
   User findUserByEmail(String email);
}
