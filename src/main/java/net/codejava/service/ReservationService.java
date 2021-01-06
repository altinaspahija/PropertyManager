package net.codejava.service;

import net.codejava.dto.ReservationDto;
import net.codejava.dto.UserDto;

import java.util.List;

public interface ReservationService {
    ReservationDto createReservation(ReservationDto reservationDto);
    ReservationDto getReservation(Integer reservationId);
    List<ReservationDto> getReservations(UserDto userDto);
    ReservationDto addReservation(Integer reservationId, Integer propertyId);
}
