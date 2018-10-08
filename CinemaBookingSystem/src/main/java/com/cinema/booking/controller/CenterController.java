package com.cinema.booking.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cinema.booking.dto.DateTimeDto;
import com.cinema.booking.model.Cinema;
import com.cinema.booking.model.Movie;
import com.cinema.booking.service.CinemaService;
import com.cinema.booking.service.MovieService;

@Controller
public class CenterController {
	@Autowired
	private MovieService movieService;

	@Autowired
	private CinemaService cinemaService;

	@GetMapping("/")
	public String entry(Model model) {
		DateTimeDto dateTimeDto = new DateTimeDto();
		List<Cinema> cinemas = cinemaService.getAllCinema();
		List<Movie> movies = movieService.getAllMovies();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 2);
		Date dateDayAfterTommorw = calendar.getTime();
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
		String strDayAfterTommorOw = simpleDateformat.format(dateDayAfterTommorw);
		model.addAttribute("today", "TODAY");
		model.addAttribute("tomorrow", "TOMORROW");
		model.addAttribute("theDayAfterTomorrow", strDayAfterTommorOw.toUpperCase());
		model.addAttribute("movies", movies);
		model.addAttribute("cinemas", cinemas);
		model.addAttribute("dateTimeDto", dateTimeDto);
		return "index";
	}
}
