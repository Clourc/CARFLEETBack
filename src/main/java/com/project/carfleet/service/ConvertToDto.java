package com.project.carfleet.service;

import com.project.carfleet.dto.*;
import com.project.carfleet.entity.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
public class ConvertToDto {

    public ModelDto convertModelToDto(Model model) {
        return new ModelDto(model.getId(), model.getBrand(), model.getImage(), model.getEnergy(), model.getType(), model.getModelName(), model.getNbDoors(), model.getNbSeats());
    }

    public FleetDto convertFleetToDto(Fleet fleet) {
        return new FleetDto(fleet.getId(), fleet.getPlace());
    }

    public UserDto convertUserToDto(UserEntity user) {
        return new UserDto(user.getId(), user.getCp(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getPhone(), user.getNbLicence(), user.getRole(), convertFleetToDto(user.getFleet()));
    }

    public ReservationsDto convertResaToDto(Reservations resa) {
        return new ReservationsDto(resa.getId(), resa.getStart_Date(), resa.getEnd_Date(), resa.getReason(), convertVehicleToDto(resa.getVehicle()), convertUserToDto(resa.getUser()));
    }

    public VehicleDto convertVehicleToDto(Vehicle vehicle) {
        return new VehicleDto(vehicle.getId(), vehicle.getLicencePlate(), convertFleetToDto(vehicle.getFleet()), convertModelToDto(vehicle.getModel()));
    }

    public <D, T> List<D> convertListToDto(List<T> elements, Function<T, D> convertMethod) {
        List<D> elementsDto = new ArrayList<>();
        for (T element : elements) {
            elementsDto.add(convertMethod.apply(element));
        }
        return elementsDto;
    }
}