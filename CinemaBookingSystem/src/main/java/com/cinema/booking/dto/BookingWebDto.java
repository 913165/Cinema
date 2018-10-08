package com.cinema.booking.dto;

import java.util.List;

public class BookingWebDto {
	private String movieSelect;
	private String cinemaSelect;
	private String dateSelect;
	private String timeSelect;
	private List<String> selectedSeats;

	public String getMovieSelect() {
		return movieSelect;
	}

	public void setMovieSelect(String movieSelect) {
		this.movieSelect = movieSelect;
	}

	public String getDateSelect() {
		return dateSelect;
	}

	public String getCinemaSelect() {
		return cinemaSelect;
	}

	public void setCinemaSelect(String cinemaSelect) {
		this.cinemaSelect = cinemaSelect;
	}

	public void setDateSelect(String dateSelect) {
		this.dateSelect = dateSelect;
	}

	public String getTimeSelect() {
		return timeSelect;
	}

	public void setTimeSelect(String timeSelect) {
		this.timeSelect = timeSelect;
	}

	public List<String> getSelectedSeats() {
		return selectedSeats;
	}

	public void setSelectedSeats(List<String> selectedSeats) {
		this.selectedSeats = selectedSeats;
	}

	@Override
	public String toString() {
		return "BookingWebDto [movieSelect=" + movieSelect + ", cinemaSelect=" + cinemaSelect + ", dateSelect="
				+ dateSelect + ", timeSelect=" + timeSelect + ", selectedSeats=" + selectedSeats + "]";
	}
}
