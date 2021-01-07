package net.codejava.service;

import net.codejava.dto.ReservationDto;
import net.codejava.dto.UserDto;
import net.codejava.model.Reservation;

import java.util.Date;
import java.util.List;

public interface ReservationService {
    List<ReservationDto> getReservationByUserId(Integer userId);

    List<ReservationDto> getReservationsByPropertyId(Integer propertyId);

    ReservationDto getReservationByReservationId(Integer reservationId);

    boolean deleteReservation(Integer reservationId);

    ReservationDto updateReservation(ReservationDto reservationDto,
                                     Integer reservationId);

    ReservationDto addReservationByUserId(Integer reservationId,
                                          Date checkIn,
                                          Date checkOut,
                                          float price,
                                          Integer propertyId,
                                          Integer paymentId,
                                          Integer userId);

    List<ReservationDto> getReservations();
}
