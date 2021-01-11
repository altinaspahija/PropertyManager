package net.codejava.service;

import net.codejava.dto.ReservationDto;
import net.codejava.dto.UserDto;
import net.codejava.exception.ReservationNotFoundException;
import net.codejava.model.Reservation;

import java.sql.Date;
import java.util.List;

public interface ReservationService {
    List<ReservationDto> getReservationsByUserId(Integer userId) throws ReservationNotFoundException;
    List<ReservationDto> getReservationsByPropertyId(Integer propertyId) throws ReservationNotFoundException;
    ReservationDto getReservationByReservationId(Integer reservationId);
    boolean deleteReservation(Integer reservationId) throws ReservationNotFoundException;
    ReservationDto updateReservation(int reservationId,ReservationDto reservationDto) throws ReservationNotFoundException;
    ReservationDto addReservation(ReservationDto reservationDto);
    List<ReservationDto> getReservations() throws ReservationNotFoundException;
    List<ReservationDto> getReservationsByDates(Date start, Date end);
}
