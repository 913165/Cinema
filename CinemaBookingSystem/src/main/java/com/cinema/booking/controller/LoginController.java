package com.cinema.booking.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cinema.booking.dto.ForgotPasswordDTO;
import com.cinema.booking.dto.RegistrationDTO;
import com.cinema.booking.model.User;
import com.cinema.booking.service.EmailService;
import com.cinema.booking.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailService emailService;

	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("registrationModel", new RegistrationDTO());
		return "registration";
	}

	@PostMapping("/registration")
	public String registrationSubmit(@ModelAttribute("registrationModel") RegistrationDTO registrationModel,
			Model model) {
		System.out.println(registrationModel);
		userService.save(registrationModel);
		return "login";
	}

	@GetMapping("/forgotPassword")
	public String forgotPassword(Model model) {
		model.addAttribute("forgotPasswordModel", new ForgotPasswordDTO());
		return "forgotPassword";
	}
	@Value("${server.port}")
	int port;
	@PostMapping("/forgotPassword")
	public String forgotPasswordSubmit(@ModelAttribute("forgotPasswordModel") ForgotPasswordDTO forgotPasswordDTO,
			Model model,HttpServletRequest request) {
		System.out.println(forgotPasswordDTO);
		String email = forgotPasswordDTO.getEmail();
		User user = userService.findUserByEmail(email);
		if (user == null) {
			try {
				throw new Exception("user not found");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String token = UUID.randomUUID().toString();
		userService.createPasswordResetTokenForUser(user, token);
		String applicationUrl = request.getScheme() + "://" + request.getServerName()+":"+port+"/";
		System.out.println(applicationUrl);
		String finalUrlForReset =  applicationUrl + "resetPassword?token=" + token;
		emailService.sendMail("tinumistry@gmail.com", "password reset", finalUrlForReset);
		return "login";
	}

}
