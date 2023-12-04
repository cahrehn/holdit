package com.holdit.service;

import com.holdit.model.ReservationCreateRequest;

public interface ReservationRepository {
    
    boolean isReservationAvailable(ReservationCreateRequest request);

    void create(ReservationCreateRequest request);

}
