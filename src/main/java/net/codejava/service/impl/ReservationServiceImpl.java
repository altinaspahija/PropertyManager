package net.codejava.service.impl;

import net.codejava.dto.PropertyDto;
import net.codejava.dto.ReservationDto;
import net.codejava.dto.UserDto;
import net.codejava.model.Property;
import net.codejava.model.Reservation;
import net.codejava.repository.ReservationRepository;
import net.codejava.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public List<ReservationDto> getReservationByUserId(Integer userId) {
        List<Reservation> reservationsByUserId = reservationRepository.getReservationsByUserId(userId);
        List<ReservationDto> reservationDtos = new ArrayList<>();

        reservationsByUserId.forEach(reservation -> reservationDtos.add(ReservationDto.getReservationDto(reservation)));
        return reservationDtos;
    }

    @Override
    public List<ReservationDto> getReservationsByPropertyId(Integer propertyId) {
        List<Reservation> reservationsByPropertyId = reservationRepository.getReservationsByPropertyId(propertyId);
        List<ReservationDto> reservationDtos = new ArrayList<>();

        reservationsByPropertyId.forEach(reservation -> reservationDtos.add(ReservationDto.getReservationDto(reservation)));
        return reservationDtos;
    }

    @Override
    public ReservationDto getReservationByReservationId(Integer reservationId) {
        Optional<Reservation> optionReservation = reservationRepository.findById(reservationId);
        if (optionReservation.isEmpty()) return null;
        return ReservationDto.getReservationDto(optionReservation.get());
    }

    @Override
    public boolean deleteReservation(Integer reservationId) {
        Optional<Reservation> reservationOpt = reservationRepository.findById(reservationId);
        if (reservationOpt.isEmpty()) return false;
        reservationRepository.delete(reservationOpt.get());
        return true;
    }


    @Override
    public ReservationDto updateReservation(ReservationDto reservationDto, Integer reservationId) {
        Reservation reservation = ReservationDto.getReservation(reservationDto);
        Reservation retReservation = reservationRepository.save(reservation);
        return ReservationDto.getReservationDto(retReservation);
    }

    @Override
    public ReservationDto addReservationByUserId(Integer reservationId, Date checkIn, Date checkOut, float price, Integer propertyId, Integer paymentId, Integer userId) {
        Reservation reservationById = reservationRepository.getReservationByReservationId(reservationId);
        Reservation reservation = ReservationDto.getReservation(ReservationDto.getReservationDto(reservationById));
        return ReservationDto.getReservationDto(reservationRepository.save(reservation));
    }

    @Override
    public List<ReservationDto> getReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        List<ReservationDto> reservationDtos = new ArrayList<>();

        reservations.forEach(reservation -> reservationDtos.add(ReservationDto.getReservationDto(reservation)));
        return reservationDtos;
    }
}
