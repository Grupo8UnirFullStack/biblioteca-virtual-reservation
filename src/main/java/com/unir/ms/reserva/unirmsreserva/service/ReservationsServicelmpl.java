package com.unir.ms.reserva.unirmsreserva.service;

import com.unir.ms.reserva.unirmsreserva.data.ReservationJpaRepository;
import com.unir.ms.reserva.unirmsreserva.facade.BooksFacade;
import com.unir.ms.reserva.unirmsreserva.model.Book;
import com.unir.ms.reserva.unirmsreserva.model.db.Reservation;
import com.unir.ms.reserva.unirmsreserva.model.request.ReservationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ReservationsServicelmpl implements ReservationsService {

    @Autowired
    private BooksFacade booksFacade;

    @Autowired
    private ReservationJpaRepository repository;

    @Override
    public ResponseEntity<?> createReservation(ReservationRequest request) {
        List<Book> books = request.getBooks().stream().map(booksFacade::getBook).filter(Objects::nonNull).toList();
        //List<Book> reservationdate = request.getReservationdate().map(booksFacade::getBook).filter(Objects::nonNull).toList();



        if (books.size() != request.getBooks().size()) {
            log.info("El libro no se encuentra en el listado", books.size(), request.getBooks().size());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: el libro no se encuentra registrado.");

        } else if (books.stream().anyMatch(book -> book.getStock() == 0)) {
            Book bookWithoutStock = books.stream().filter(book -> book.getStock() == 0).findFirst().orElse(null);
            log.info("No se encuentran existencias del libro", books.size(), request.getBooks().size());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: el libro que desea reservar: '" + (bookWithoutStock != null ? bookWithoutStock.getTitle() : "")  + "' no se encuentra en existencia.");
        } else {
            Reservation reservation = Reservation.builder()
                    .books(books.stream().map(Book::getId).collect(Collectors.toList()))
                    .reservationdate(request.getReservationdate())
                    .build();
            repository.save(reservation);
            return ResponseEntity.status(HttpStatus.CREATED).body("Se ha realizado con éxito la reserva");
        }
    }



    @Override
    public Reservation getReservation(String id) {
        return repository.findById(Long.valueOf(id)).orElse(null);
    }

    @Override
    public List<Reservation> getReservations() {
        List<Reservation> reservations = repository.findAll();
        return reservations.isEmpty() ? null : reservations;
    }
}
