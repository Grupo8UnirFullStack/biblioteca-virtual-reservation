package com.unir.ms.reserva.unirmsreserva.controller;

import com.unir.ms.reserva.unirmsreserva.model.db.Reservation;
import com.unir.ms.reserva.unirmsreserva.model.request.ReservationRequest;
import com.unir.ms.reserva.unirmsreserva.service.ReservationsService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ReservationsController {

    private final ReservationsService service;

    @PostMapping("/reservation")
    //Uso de jakarta api validation dependency
    public ResponseEntity<Reservation> createReservation(@RequestBody @Valid ReservationRequest request) {

        log.info("Generando reserva de libros...");
        Reservation created = service.createReservation(request);

        if (created != null) {
            return ResponseEntity.ok(created);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/reservation")
    public ResponseEntity<List<Reservation>> getReservations() {

        List<Reservation> reservations = service.getReservations();
        if (reservations != null) {
            return ResponseEntity.ok(reservations);
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    @GetMapping("/reservation/{id}")
    public ResponseEntity<Reservation> getReservation(@PathVariable String id) {

        Reservation reservation = service.getReservation(id);
        if (reservation != null) {
            return ResponseEntity.ok(reservation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
