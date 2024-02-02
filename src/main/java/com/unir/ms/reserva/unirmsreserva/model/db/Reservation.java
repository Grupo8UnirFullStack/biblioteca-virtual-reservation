package com.unir.ms.reserva.unirmsreserva.model.db;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "reservation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reservationdate")
    private String reservationdate;

    @ElementCollection
    @Column(name = "books")
    private List<Long> books;

    public void update(ReservationDTO reservationDto) {
        this.reservationdate = reservationDto.getReservationdate();
    }


}
