package com.holdit.service;

import java.util.Optional;
import java.util.function.Function;

import com.holdit.model.ReservationCreateRequest;

public interface CheckReservationPossible extends Function<ReservationCreateRequest, Optional<String>> {

}
