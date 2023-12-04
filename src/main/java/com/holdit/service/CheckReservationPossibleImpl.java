package com.holdit.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.holdit.config.HoldItConfig;
import com.holdit.model.ReservationCreateRequest;

@Component
public class CheckReservationPossibleImpl implements CheckReservationPossible {

    private final HoldItConfig config;

    public CheckReservationPossibleImpl(@Qualifier("pepperoniHugSpotConfig") HoldItConfig config) {
        this.config = config;
    }

    @Override
    public Optional<String> apply(ReservationCreateRequest request) {
        if (request.getPartySize() > config.getMaxCapacity()) {
            return Optional.of("Your party is too large");
        }

        if (!config.getAvailableSlots().contains(request.getReservationSlot())) {
            return Optional.of("We can't accept reservations at " + request.getReservationSlot());
        }

        return Optional.empty();
    }
}
