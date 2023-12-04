package com.holdit.service;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.holdit.model.ReservationListRequest;
import com.holdit.model.ReservationsResponse;

@Component
public class ListReservations implements Function<ReservationListRequest, ReservationsResponse> {

    private ReservationRepository reservationRepository;

	public ListReservations(final ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
	}

	@Override
	public ReservationsResponse apply(ReservationListRequest request) {
        return new ReservationsResponse(reservationRepository.list(request));
	}
}
