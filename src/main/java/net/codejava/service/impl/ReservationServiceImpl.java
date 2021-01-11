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
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public List<ReservationDto> getReservationsByUserId(Integer userId) {
        List<ReservationDto> retReservations = new ArrayList<>();
        List<Reservation> tempReservations = reservationRepository.findAll();

        tempReservations.forEach(reservation -> {
            if (reservation.getUserId()==userId) {
                retReservations.add(ReservationDto.getReservationDto(reservation));
            }
        });
        return retReservations;
    }

    @Override
    public List<ReservationDto> getReservationsByPropertyId(Integer propertyId) {

        List<ReservationDto> retReservations = new ArrayList<>();
        List<Reservation> tempReservations = reservationRepository.findAll();
        tempReservations.forEach(reservation -> {
            if (reservation.getPropertyId()==propertyId) {
                retReservations.add(ReservationDto.getReservationDto(reservation));
            }
        });
        return retReservations;

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
    public ReservationDto updateReservation(ReservationDto reservationDto) {
        Reservation reservation = ReservationDto.getReservation(reservationDto);
        Reservation retReservation = reservationRepository.save(reservation);
        return ReservationDto.getReservationDto(retReservation);
    }

    @Override
    public ReservationDto addReservation(ReservationDto reservationDto) {
        Reservation reservation = ReservationDto.getReservation(reservationDto);
        reservationRepository.save(reservation);
        return ReservationDto.getReservationDto(reservation);
    }

    @Override
    public List<ReservationDto> getReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        List<ReservationDto> reservationDtos = new ArrayList<>();

        reservations.forEach(reservation -> reservationDtos.add(ReservationDto.getReservationDto(reservation)));
        return reservationDtos;
    }

    public List<ReservationDto> getReservationsByDates(Date start, Date end){
        List<ReservationDto> retReservations = new ArrayList<>();
        List<Reservation> tempReservations = reservationRepository.findAll();


        tempReservations.forEach(reservation -> {
            if ((reservation.getCheckIn().after(start) || reservation.getCheckIn().equals(start))
                    && reservation.getCheckIn().before(end) || reservation.getCheckIn().equals(end)) {
                retReservations.add(ReservationDto.getReservationDto(reservation));
            }
        });
        return retReservations;
    }
}
