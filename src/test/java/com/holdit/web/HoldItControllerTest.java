package com.holdit.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.holdit.model.ReservationCreateRequest;
import com.holdit.model.ReservationData;
import com.holdit.model.ReservationListRequest;
import com.holdit.model.ReservationsResponse;
import com.holdit.service.CreateReservation;
import com.holdit.service.ListReservations;

@WebMvcTest(HoldItController.class)
public class HoldItControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean CreateReservation createReservation;
    @MockBean ListReservations listReservations;

    @Test
    void createReservation() throws Exception {
        final String requestJson = "{"
                + "\"partySize\": 4,"
                + "\"reservationDate\": \"2023-12-01\","
                + "\"reservationSlot\": \"7\","
                + "\"partyName\": \"Test Party\","
                + "\"contactPhone\": \"123-456-7890\","
                + "\"contactEmail\": \"test@example.com\""
                + "}";

        when(createReservation.apply(any(ReservationCreateRequest.class))).thenReturn("See you soon!");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/reservation/reserve")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("See you soon!"));
    }

    @Test
    void listReservations() throws Exception {
        final String requestJson = "{}";
        final ReservationData reservationData = 
            new ReservationData(2, "2023-12-02", "8", "Frank", "123-456-7890", "test@example.com");
        final ReservationsResponse response = new ReservationsResponse(List.of(reservationData));
        when(listReservations.apply(any(ReservationListRequest.class))).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/reservation/list")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("{\"reservations\":[{\"partySize\":2,\"reservationDate\":\"2023-12-02\",\"reservationSlot\":\"8\",\"partyName\":\"Frank\",\"contactPhone\":\"123-456-7890\",\"contactEmail\":\"test@example.com\"}]}"));
    }
}
