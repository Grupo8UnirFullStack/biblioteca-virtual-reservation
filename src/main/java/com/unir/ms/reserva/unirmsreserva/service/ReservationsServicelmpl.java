package com.unir.ms.reserva.unirmsreserva.service;

import com.unir.ms.reserva.unirmsreserva.data.ReservationJpaRepository;
import com.unir.ms.reserva.unirmsreserva.facade.BooksFacade;
import com.unir.ms.reserva.unirmsreserva.model.Book;
import com.unir.ms.reserva.unirmsreserva.model.db.Reservation;
import com.unir.ms.reserva.unirmsreserva.model.request.ReservationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ReservationsServicelmpl implements ReservationsService {

    @Autowired
    private BooksFacade booksFacade;

    @Autowired
    private ReservationJpaRepository repository;

    @Override
    public Reservation createReservation(ReservationRequest request) {
        List<Book> books = request.getBook().stream().map(booksFacade::getBook).filter(Objects::nonNull).toList();
        Reservation reservation = Reservation.builder().book(books.stream().map(Book::getId).collect(Collectors.toList())).build();
        repository.save(reservation);
        return reservation;
//        if(reserva.size() != request.getProducts().size() || products.stream().anyMatch(product -> !product.getVisible())) {
//            return null;
//        } else {
//            Order order = Order.builder().products(products.stream().map(Product::getId).collect(Collectors.toList())).build();
//            repository.save(order);
//            return order;
//        }
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
