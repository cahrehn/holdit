package com.holdit.service;

import org.springframework.stereotype.Component;

import com.holdit.model.ReservationCreateRequest;

@Component
public class ReservationRepositoryImpl implements ReservationRepository {

    @Override
    public boolean isReservationAvailable(final ReservationCreateRequest request) {
        return true;
    }

    @Override
    public void create(ReservationCreateRequest request) {
        
    }
}
