package net.codejava.dto;

import net.codejava.model.Property;
import net.codejava.model.Reservation;
import lombok.Data;
import net.codejava.model.User;

import java.util.Date;

@Data
public class ReservationDto {
    private int reservationId;
    private Date checkIn;
    private Date checkOut;
    private float price;
    private Property property;
    private User client;

    public static Reservation getReservation(ReservationDto reservationDto){
        if (reservationDto==null) return null;
        Reservation reservation = new Reservation();
        reservation.setReservationId(reservationDto.getReservationId());
        reservation.setCheckIn(reservationDto.getCheckIn());
        reservation.setCheckOut(reservationDto.getCheckOut());
        reservation.setPrice(reservationDto.getPrice());
        reservation.setProperty(reservationDto.getProperty());
        reservation.setClient(reservationDto.getClient());

        return reservation;
    }

    public static ReservationDto getReservationDto(Reservation reservation){
        if (reservation==null) return null;
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setReservationId(reservation.getReservationId());
        reservationDto.setCheckIn(reservation.getCheckIn());
        reservationDto.setCheckOut(reservation.getCheckOut());
        reservationDto.setPrice(reservation.getPrice());
        reservationDto.setProperty(reservation.getProperty());
        reservationDto.setClient(reservation.getClient());

        return reservationDto;
    }
}
