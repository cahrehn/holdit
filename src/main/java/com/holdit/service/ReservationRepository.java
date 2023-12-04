package com.holdit.service;

import java.util.List;

import com.holdit.model.ReservationCreateRequest;
import com.holdit.model.ReservationData;
import com.holdit.model.ReservationListRequest;

public interface ReservationRepository {
    
    boolean isReservationAvailable(ReservationCreateRequest request);

    void create(ReservationCreateRequest request);

    List<ReservationData> list(ReservationListRequest request);

}
