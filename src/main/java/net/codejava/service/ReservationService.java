package net.codejava.service;

import net.codejava.dto.ReservationDto;
import net.codejava.dto.UserDto;
import net.codejava.model.Reservation;

import java.sql.Date;
import java.util.List;

public interface ReservationService {
    List<ReservationDto> getReservationsByUserId(Integer userId);
    List<ReservationDto> getReservationsByPropertyId(Integer propertyId);
    ReservationDto getReservationByReservationId(Integer reservationId);
    boolean deleteReservation(Integer reservationId);
    ReservationDto updateReservation(ReservationDto reservationDto);
    ReservationDto addReservation(ReservationDto reservationDto);
    List<ReservationDto> getReservations();
    List<ReservationDto> getReservationsByDates(Date start, Date end);
}
