package com.unir.ms.reserva.unirmsreserva.model.db;

import lombok.*;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ReservationDTO {

    private String reservationdate;
    private List<Long> books;

}
