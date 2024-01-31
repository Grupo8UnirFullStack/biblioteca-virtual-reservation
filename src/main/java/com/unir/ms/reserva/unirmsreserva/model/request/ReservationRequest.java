package com.unir.ms.reserva.unirmsreserva.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequest {

	@NotNull(message = "`libros no puede ser nulo`")
	@NotEmpty(message = "`libros` no puede estar vacio")
	private List<String> book;
}
