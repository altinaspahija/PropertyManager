package net.codejava.dto;

import net.codejava.model.PaymentDetails;
import net.codejava.model.Property;
import net.codejava.model.Reservation;
import lombok.Data;
import net.codejava.model.User;

import java.util.Date;

@Data
public class ReservationDto {
    private Integer reservationId;
    private Date checkIn;
    private Date checkOut;
    private float price;
    private Integer propertyId;
    private Integer userId;
    private Integer paymentId;

    public static Reservation getReservation(ReservationDto reservationDto){
        if (reservationDto==null) return null;
        Reservation reservation = new Reservation();
        reservation.setReservationId(reservationDto.getReservationId());
        reservation.setCheckIn(reservationDto.getCheckIn());
        reservation.setCheckOut(reservationDto.getCheckOut());
        reservation.setPrice(reservationDto.getPrice());
        reservation.setPropertyId(reservationDto.getPropertyId());
        reservation.setUserId(reservationDto.getUserId());
        reservation.setPaymentId(reservationDto.getPaymentId());

        return reservation;
    }

    public static ReservationDto getReservationDto(Reservation reservation){
        if (reservation==null) return null;
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setReservationId(reservation.getReservationId());
        reservationDto.setCheckIn(reservation.getCheckIn());
        reservationDto.setCheckOut(reservation.getCheckOut());
        reservationDto.setPrice(reservation.getPrice());
        reservationDto.setPropertyId(reservation.getPropertyId());
        reservationDto.setUserId(reservation.getUserId());
        reservationDto.setPaymentId(reservation.getPaymentId());

        return reservationDto;
    }
}
