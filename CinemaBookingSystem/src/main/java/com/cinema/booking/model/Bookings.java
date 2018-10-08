package com.cinema.booking.model;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bookings {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private Date bookingForDate;
	private Date bookingMadeDate;
	private String showTime;
	private int bookingSeatCount;
	private String listSeats;
	private Long movieId;

	public String getListSeats() {
		return listSeats;
	}

	public void setListSeats(String listSeats) {
		this.listSeats = listSeats;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getBookingForDate() {
		return bookingForDate;
	}

	public void setBookingForDate(Date bookingForDate) {
		this.bookingForDate = bookingForDate;
	}

	public int getBookingSeatCount() {
		return bookingSeatCount;
	}

	public void setBookingSeatCount(int bookingSeatCount) {
		this.bookingSeatCount = bookingSeatCount;
	}

	public Date getBookingMadeDate() {
		return bookingMadeDate;
	}

	public void setBookingMadeDate(Date bookingMadeDate) {
		this.bookingMadeDate = bookingMadeDate;
	}

	public String getShowTime() {
		return showTime;
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}
	
	@Override
	public String toString() {
		return "Bookings [id=" + id + ", bookingForDate=" + bookingForDate + ", bookingMadeDate=" + bookingMadeDate
				+ ", showTime=" + showTime + ", bookingSeatCount=" + bookingSeatCount + ", listSeats=" + listSeats
				+ ", movieId=" + movieId + "]";
	}
}
