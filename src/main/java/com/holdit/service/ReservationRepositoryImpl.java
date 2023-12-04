package com.holdit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.holdit.config.HoldItConfig;
import com.holdit.model.ReservationCreateRequest;
import com.holdit.model.ReservationData;
import com.holdit.model.ReservationListRequest;

@Component
public class ReservationRepositoryImpl implements ReservationRepository {

    final Map<String, List<ReservationData>> data = new TreeMap<>();
    final HoldItConfig config;


    public ReservationRepositoryImpl(@Qualifier("pepperoniHugSpotConfig") HoldItConfig config) {
		this.config = config;
	}

	@Override
    public boolean isReservationAvailable(final ReservationCreateRequest request) {
        if (!data.containsKey(request.getReservationSlot())) {
            return true;
        }
        final Integer numPatrons = data.get(request.getReservationSlot())
            .stream()
            .map(ReservationData::getPartySize)
            .collect(Collectors.summingInt(Integer::intValue));
        return numPatrons + request.getPartySize() <= config.getMaxCapacity();
    }

    @Override
    public void create(ReservationCreateRequest request) {
         if (!data.containsKey(request.getReservationSlot())) {
            data.put(request.getReservationSlot(), new ArrayList<ReservationData>());
         }
        final ReservationData reservationData = new ReservationData(
            request.getPartySize(),
            request.getReservationDate(),
            request.getReservationSlot(),
            request.getPartyName(),
            request.getContactPhone(),
            request.getContactEmail());
        data.get(request.getReservationSlot()).add(reservationData);
    }

	@Override
	public List<ReservationData> list(final ReservationListRequest request) {
		return data.values().stream().flatMap(List::stream).collect(Collectors.toList());
	}
}
