package com.holdit.service;

import org.springframework.stereotype.Service;

import com.holdit.model.ReservationCreateRequest;

@Service
public class CreateReservationImpl implements CreateReservation {

    private final CheckReservationPossible checkReservationPossible;
    private final ReservationRepository reservationRepository;

    public CreateReservationImpl(CheckReservationPossible checkReservationPossible,
            ReservationRepository reservationRepository) {
        this.checkReservationPossible = checkReservationPossible;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public String apply(final ReservationCreateRequest request) {
        return checkReservationPossible.apply(request)
            .map(error -> {
                return error;
            })
            .orElseGet(() -> {
                if (reservationRepository.isReservationAvailable(request)) {
                    reservationRepository.create(request);
                    return "See you soon!";
                }
                return "Try another time.";
            });
    }    
}
