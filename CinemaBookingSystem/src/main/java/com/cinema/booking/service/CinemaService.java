package com.cinema.booking.service;

import java.util.List;

import com.cinema.booking.model.Cinema;

public interface CinemaService {
	public List<Cinema> getAllCinema();
	public void save(Cinema cinema);
	public void populateCinemaData();
}
