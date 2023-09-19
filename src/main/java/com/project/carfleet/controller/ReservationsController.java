package com.project.carfleet.controller;

import com.project.carfleet.repository.UserRepository;
import com.project.carfleet.repository.VehicleRepository;
import com.project.carfleet.service.ConvertToDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.project.carfleet.dto.ReservationsDto;
import com.project.carfleet.entity.Reservations;
import com.project.carfleet.repository.ReservationsRepository;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class ReservationsController {

    @Autowired
    private ReservationsRepository reservationsRepository;
    @Autowired
    private ConvertToDto convertToDto;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/reservations")
    @ResponseBody
    public List<ReservationsDto> getAllReservations(
            @RequestParam(defaultValue = "", required = false) Long vehicleId,
            @RequestParam(defaultValue = "", required = false) Long userId,
            @RequestParam(defaultValue = "", required = false) String sortBy) {
        List<Reservations> reservationsList;
        if (vehicleId != null) {
            if (sortBy.equals("ASC")) {
                reservationsList = reservationsRepository.findResaByVehicleOrderByASC(vehicleId);
                return convertToDto.convertListToDto(reservationsList, convertToDto::convertResaToDto);
            }
            reservationsList = reservationsRepository.findResaByVehicleOrderByDESC(vehicleId);
            return convertToDto.convertListToDto(reservationsList, convertToDto::convertResaToDto);
        }
        if (userId != null) {
            if (userRepository.findById(userId).get().getRole().getType().equals("ADMIN")) {
                Long fleetId = this.userRepository.findById(userId).get().getFleet().getId();
                reservationsList = reservationsRepository.findAllResaByFleet(fleetId);
                reservationsList.sort(Comparator.comparing(Reservations::getStart_Date));
                if (sortBy.equals("ASC")) {
                    return convertToDto.convertListToDto(reservationsList, convertToDto::convertResaToDto);
                }
                Collections.reverse(reservationsList);
                return convertToDto.convertListToDto(reservationsList, convertToDto::convertResaToDto);
            }
            if (sortBy.equals("ASC")) {
                reservationsList = reservationsRepository.findResaByUserOrderByASC(userId);
                return convertToDto.convertListToDto(reservationsList, convertToDto::convertResaToDto);
            }
            reservationsList = reservationsRepository.findResaByUserOrderByDESC(userId);
            return convertToDto.convertListToDto(reservationsList, convertToDto::convertResaToDto);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Renseignez vehicleId ou userId");
    }


    @PostMapping("/reservations/add")
    @ResponseBody
    public ReservationsDto createReservations(@RequestBody ReservationsDto reservations) {
        Reservations newResa = new Reservations(reservations.getStart_Date(), reservations.getEnd_Date(), reservations.getReason());
        newResa.setVehicle(vehicleRepository.findById(reservations.getVehicle().getId()).get());
        newResa.setUser(userRepository.findById(reservations.getUser().getId()).get());
        reservationsRepository.save(newResa);
        return convertToDto.convertResaToDto(newResa);
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
    }
}