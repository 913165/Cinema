package com.cinema.booking.service;

import com.cinema.booking.dto.RegistrationDTO;
import com.cinema.booking.model.User;

public interface UserService {
	User findUserByEmail(String email);
	void save(RegistrationDTO registrationDTO);
	void createPasswordResetTokenForUser(User user, String token);
}
