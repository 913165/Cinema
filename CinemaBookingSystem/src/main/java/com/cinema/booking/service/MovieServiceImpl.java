package com.cinema.booking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinema.booking.model.Movie;
import com.cinema.booking.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {
	@Autowired
	private MovieRepository movieRepository;
	
	@Override
	public List<Movie> getAllMovies() {
		return movieRepository.findAll();
	}

	@Override
	public void save(Movie movie) {
		movieRepository.save(movie);
	}

	@Override
	public void delete(Long id) {
		movieRepository.deleteById(id);
	}

	@Override
	public Movie findById(Long id) {
		Optional<Movie> movie = movieRepository.findById(id); 
		return movie.get();
	}
}
