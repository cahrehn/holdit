package com.holdit.model;

import java.util.List;

public class ReservationsResponse {
    
    private final List<ReservationData> reservations;

	public ReservationsResponse(List<ReservationData> reservations) {
		this.reservations = reservations;
	}

	public List<ReservationData> getReservations() {
		return reservations;
	}
}
