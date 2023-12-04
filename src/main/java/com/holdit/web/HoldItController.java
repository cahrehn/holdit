package com.holdit.web;

import org.springframework.web.bind.annotation.RestController;

import com.holdit.model.ReservationCreateRequest;
import com.holdit.service.CreateReservation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class HoldItController {

	private final CreateReservation createReservation;

	public HoldItController(final CreateReservation reserveService) {
		this.createReservation = reserveService;
	}

	@PostMapping(value="/api/v1/reservation/reserve")
	public String reserve(@RequestBody ReservationCreateRequest request) {
		return createReservation.apply(request);
	}
}
