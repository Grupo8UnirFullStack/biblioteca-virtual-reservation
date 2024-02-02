package com.unir.ms.reserva.unirmsreserva.controller;

import com.unir.ms.reserva.unirmsreserva.model.db.Reservation;
import com.unir.ms.reserva.unirmsreserva.model.db.ReservationDTO;
import com.unir.ms.reserva.unirmsreserva.model.request.ReservationRequest;
import com.unir.ms.reserva.unirmsreserva.service.ReservationsService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ReservationsController {

    private final ReservationsService service;

    @PostMapping("/reservations")
    //Uso de jakarta api validation dependency
    public ResponseEntity<ResponseEntity<?>> createReservation(@RequestBody @Valid ReservationRequest request) {

        log.info("Generando reserva de libros...");
        ResponseEntity<?> created = service.createReservation(request);

        if (created != null) {
            return ResponseEntity.ok(created);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<Reservation>> getReservations() {

        List<Reservation> reservations = service.getReservations();
        if (reservations != null) {
            return ResponseEntity.ok(reservations);
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    @GetMapping("/reservations/{id}")
    public ResponseEntity<Reservation> getReservation(@PathVariable String id) {

        Reservation reservation = service.getReservation(id);
        if (reservation != null) {
            return ResponseEntity.ok(reservation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/reservations/{id}")
    public ResponseEntity<Reservation> patchBook(@PathVariable String id, @RequestBody ReservationDTO patchBody) {

        Reservation patched = service.updateReservation(id, patchBody);
        if (patched != null) {
            return ResponseEntity.ok(patched);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
