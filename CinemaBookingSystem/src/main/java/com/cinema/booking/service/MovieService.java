package com.cinema.booking.service;

import java.util.List;

import com.cinema.booking.model.Movie;

public interface MovieService {
	public List<Movie> getAllMovies();
	public void save(Movie movie);
	public void delete(Long id);
	public Movie findById(Long id);
}
