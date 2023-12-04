package com.holdit.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.holdit.model.ReservationCreateRequest;
import com.holdit.model.ReservationData;
import com.holdit.model.ReservationListRequest;

@Component
public class ReservationRepositoryImpl implements ReservationRepository {

    @Override
    public boolean isReservationAvailable(final ReservationCreateRequest request) {
        return true;
    }

    @Override
    public void create(ReservationCreateRequest request) {
       
    }

	@Override
	public List<ReservationData> list(final ReservationListRequest request) {
		return List.of(new ReservationData(0, null, null, null, null, null));
	}
}
