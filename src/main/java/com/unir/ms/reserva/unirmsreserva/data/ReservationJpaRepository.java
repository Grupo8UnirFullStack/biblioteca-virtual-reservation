package com.unir.ms.reserva.unirmsreserva.data;

import com.unir.ms.reserva.unirmsreserva.model.db.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationJpaRepository extends JpaRepository<Reservation, Long> {
}
