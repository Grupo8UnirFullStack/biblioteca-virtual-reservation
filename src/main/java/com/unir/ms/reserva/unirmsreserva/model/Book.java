package com.unir.ms.reserva.unirmsreserva.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Book {
    private Long id;
    private Integer year;
    private String isbn;
    private String description;
    private String title;
    private Integer auth_id;
    private Integer gender_id;
    private Integer image_id;
    private Integer stock;


}
