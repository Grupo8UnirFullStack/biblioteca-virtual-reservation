package com.unir.ms.reserva.unirmsreserva.service;

import com.unir.ms.reserva.unirmsreserva.model.db.Reservation;
import com.unir.ms.reserva.unirmsreserva.model.request.ReservationRequest;

import java.util.List;

public interface ReservationsService {
    Reservation createReservation(ReservationRequest request);

    Reservation getReservation(String id);

    List<Reservation> getReservations();
}
