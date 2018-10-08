package com.cinema.booking.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cinema.booking.dto.BookingWebDto;
import com.cinema.booking.dto.Seat;
import com.cinema.booking.model.Bookings;
import com.cinema.booking.model.Movie;
import com.cinema.booking.service.BookingService;
import com.cinema.booking.service.CinemaService;
import com.cinema.booking.service.MovieService;

@Controller
public class BookingController {

	@Autowired
	private MovieService movieService;
	@Autowired
	private CinemaService cinemaService;
	@Autowired
	private BookingService bookingService;

	@PostMapping("/bookTicket")
	public String seatChart(@ModelAttribute("bookingWebDto") BookingWebDto bookingWebDto, Model model) {
		System.out.println(bookingWebDto);
		Bookings bookings = new Bookings();
		Long movieId = Long.parseLong(bookingWebDto.getMovieSelect());
		Movie movie = movieService.findById(movieId);
		List<Bookings> currentBookings = bookingService.getCurrentBookings();
		System.out.println("currentBookings " + currentBookings);
		String strCurrentBookedSeats = null;
		if (currentBookings != null && currentBookings.size() > 0) {
			strCurrentBookedSeats = currentBookings.get(0).getListSeats();
			strCurrentBookedSeats = strCurrentBookedSeats.replaceAll("[\\[\\](){}]", "");
		}
		if (strCurrentBookedSeats == null) {
			strCurrentBookedSeats = new String();
		}
		System.out.println("Current Booked Seats : " + strCurrentBookedSeats);
		model.addAttribute("strCurrentBookedSeats", strCurrentBookedSeats);
		model.addAttribute("bookingWebDto", bookingWebDto);
		model.addAttribute("movie", movie);
		return "seatChart";
	}

	@RequestMapping(value = "/selectSeats", method = RequestMethod.POST)
	public String selectSeats(@ModelAttribute("bookingWebDtoSeatSelect") BookingWebDto bookingWebDtoSeatSelect,
			HttpServletRequest request, Model model) {
		Map<String, String[]> parameters = request.getParameterMap();
		System.out.println(parameters.keySet());

		System.out.println(bookingWebDtoSeatSelect);
		List<Seat> seatList = new ArrayList<Seat>();
		for (String strSeat : bookingWebDtoSeatSelect.getSelectedSeats()) {
			Seat seat = new Seat();
			seat.setSeatId(1);
			seat.setSeatNo(strSeat);
			seatList.add(seat);
		}
		model.addAttribute("bookingWebDtoSeatSelect", bookingWebDtoSeatSelect);
		model.addAttribute("seatList", seatList);
		return "confirmBooking";
	}

	@RequestMapping(value = "/confirmBooking", method = RequestMethod.POST)
	public String confirmBooking(@ModelAttribute("bookingWebDtoFinal") BookingWebDto bookingWebDtoFinal, Model model) {
		System.out.println("BookingWebDtoFinal : " + bookingWebDtoFinal);
		Bookings bookingsInfo = new Bookings();
		bookingsInfo.setBookingMadeDate(new Date());
		bookingsInfo.setBookingForDate(new Date());
		bookingsInfo.setListSeats(bookingWebDtoFinal.getSelectedSeats().toString());
		bookingsInfo.setShowTime(bookingWebDtoFinal.getTimeSelect());
		bookingService.bookMovieTicket(bookingsInfo);
		model.addAttribute("BookingWebDtoFinal", bookingWebDtoFinal);
		System.out.println("confirmBooking");
		return "bookingSummary";
	}
}
