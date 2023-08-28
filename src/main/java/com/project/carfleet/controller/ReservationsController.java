package com.project.carfleet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.project.carfleet.entity.Reservations;
import com.project.carfleet.repository.ReservationsRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;



@Controller
public class ReservationsController {

    @Autowired
    private ReservationsRepository reservationsRepository;

    @GetMapping("/reservations")
    @ResponseBody
    public List<Reservations> getAllReservations() {
        return reservationsRepository.findAll();
    }

    @PostMapping("/reservations")
    @ResponseBody
    public Reservations createReservations(@RequestBody Reservations reservations) {
        return reservationsRepository.save(reservations);
    }

    @GetMapping("/reservations/{id}")
    @ResponseBody
    public ResponseEntity<Reservations> getReservationsById(@PathVariable long id) {
        if (id <= 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID invalide");
        }

        Optional<Reservations> reservations = reservationsRepository.findById(id);
        if (reservations.isPresent()) {
            return ResponseEntity.ok(reservations.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Réservations introuvables");
        }
    }

    @DeleteMapping("/reservations/delete/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteReservations(@PathVariable Long id) {
        if (id <= 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID invalide");
        }

        if (reservationsRepository.existsById(id)) {
            reservationsRepository.deleteById(id);
            return ResponseEntity.ok("Réservations supprimées avec succès");
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Réservations introuvables");
        }
    }
}