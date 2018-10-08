package com.cinema.booking.dto;

public class Seat {
	private int seatId;
	private String seatNo;

	public int getSeatId() {
		return seatId;
	}

	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

	@Override
	public String toString() {
		return "Seat [seatId=" + seatId + ", seatNo=" + seatNo + "]";
	}

}
