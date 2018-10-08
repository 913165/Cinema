package com.cinema.booking.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cinema.booking.model.Cinema;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long>  {

}
