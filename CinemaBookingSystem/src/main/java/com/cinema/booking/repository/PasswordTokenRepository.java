package com.cinema.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cinema.booking.model.PasswordResetToken;

public interface PasswordTokenRepository extends JpaRepository<PasswordResetToken, Long> {

}
