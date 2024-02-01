package com.unir.ms.reserva.unirmsreserva.model.db;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "reservation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @Column(name = "books")
    private List<Long> books;
}
