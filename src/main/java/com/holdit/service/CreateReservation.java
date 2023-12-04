package com.holdit.service;

import java.util.function.Function;

import com.holdit.model.ReservationCreateRequest;

public interface CreateReservation extends Function<ReservationCreateRequest, String> {
    
}
