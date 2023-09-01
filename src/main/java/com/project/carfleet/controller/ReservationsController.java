package com.project.carfleet.controller;
  
import com.project.carfleet.dto.ModelDto;
import com.project.carfleet.dto.VehicleDto;
import com.project.carfleet.entity.Vehicle;
import com.project.carfleet.service.ConvertToDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.project.carfleet.dto.ReservationsDto;
import com.project.carfleet.entity.Reservations;
import com.project.carfleet.repository.ReservationsRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;





@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class ReservationsController {

    @Autowired
    private ReservationsRepository reservationsRepository;
    @Autowired
    private ConvertToDto convertToDto;

    @GetMapping("/reservations")
    @ResponseBody
    public List<ReservationsDto> getAllReservations() {
       List <Reservations> reservationsList = reservationsRepository.findAll();
       return convertToDto.convertListToDto(reservationsList, convertToDto::convertResaToDto);
    }

    
    @PostMapping("/reservations/add")
    @ResponseBody
    public Reservations createReservations(@RequestBody Reservations reservations) {     
        reservationsRepository.save(reservations);
        return reservations;
 }
    
    
    @GetMapping("/reservations/{id}")
    @ResponseBody
    public ResponseEntity<ReservationsDto> getReservationsById(@PathVariable long id) {
        if (id <= 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID invalide");
        }

        Optional<Reservations> reservation = reservationsRepository.findById(id);
        if (reservation.isPresent()) {
            return ResponseEntity.ok(convertToDto.convertResaToDto(reservation.get()));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Réservations introuvables");
        }
    }

    @DeleteMapping("/reservations/{id}/delete")
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

        // @GetMapping("/reservations-all")
        // @ResponseBody
        // public List<ReservationsDto> getAllReservations() {
        //  List <Reservations> reservations = reservationsRepository.findAll();
        //  List <ReservationsDto> reservationsDto = new ArrayList<>();
        //     for (Reservations reservation : reservations) {
        //         reservationsDto.add(new ReservationsDto( reservation.getId(), reservation.getStart_Date(), reservation.getEnd_Date()));
        //     }
        //     return reservationsDto;

        // }
    

}}