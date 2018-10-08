package com.cinema.booking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinema.booking.model.Cinema;
import com.cinema.booking.repository.CinemaRepository;

@Service
public class CinemaServiceImpl implements CinemaService  {
	@Autowired
	private CinemaRepository cinemaRepository;
	
	public List<Cinema> getAllCinema(){
		return cinemaRepository.findAll();
	}
	


	@Override
	public void save(Cinema cinema) {
		cinemaRepository.save(cinema);
	}
	
	public void populateCinemaData() {
		Cinema cinema1 = new Cinema();
		cinema1.setCinemaName("Cosy Cinema-Ahmedabad");
		this.save(cinema1);;
		Cinema cinema2 = new Cinema();
		cinema2.setCinemaName("Cosy Cinema-Mumbai");
		this.save(cinema2);;
		Cinema cinema3 = new Cinema();
		cinema3.setCinemaName("Cosy Cinema-Pune");
		this.save(cinema3);;
	}
}
