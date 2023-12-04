package com.holdit.web;

import org.springframework.web.bind.annotation.RestController;

import com.holdit.model.ReservationCreateRequest;
import com.holdit.model.ReservationListRequest;
import com.holdit.model.ReservationsResponse;
import com.holdit.service.CreateReservation;
import com.holdit.service.ListReservations;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class HoldItController {

	private final CreateReservation createReservation;
	private final ListReservations listReservations;

	public HoldItController(CreateReservation createReservation, ListReservations listReservations) {
		this.createReservation = createReservation;
		this.listReservations = listReservations;
	}

	@PostMapping(value="/api/v1/reservation/reserve")
	public String reserve(@RequestBody ReservationCreateRequest request) {
		return createReservation.apply(request);
	}

	@PostMapping(value="/api/v1/reservation/list")
	public ReservationsResponse list(@RequestBody ReservationListRequest request) {
		return listReservations.apply(request);
	}
}
