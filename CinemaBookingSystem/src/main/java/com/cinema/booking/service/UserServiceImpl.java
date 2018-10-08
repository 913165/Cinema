package com.cinema.booking.service;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinema.booking.dto.RegistrationDTO;
import com.cinema.booking.model.PasswordResetToken;
import com.cinema.booking.model.Role;
import com.cinema.booking.model.User;
import com.cinema.booking.repository.PasswordTokenRepository;
import com.cinema.booking.repository.UserRepository;



@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordTokenRepository passwordTokenRepository;

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findUserByEmail(email);
	}

	@Override
	public void createPasswordResetTokenForUser(User user, String token) {
		PasswordResetToken passwordResetToken = new PasswordResetToken();
		passwordResetToken.setUser(user);
		passwordResetToken.setToken(token);
		passwordTokenRepository.save(passwordResetToken);
	}

	@Override
	public void save(RegistrationDTO registrationDTO) {
		User user = new User();
		user.setFirstName(registrationDTO.getFirstName());
		user.setLastName(registrationDTO.getLastName());
		user.setEmail(registrationDTO.getEmail());
		user.setPassword(registrationDTO.getPassword());
		Role role = new Role();
		role.setName("ROLD_USER");
		user.setRoles(Arrays.asList(role));
		userRepository.save(user);
	}
}
