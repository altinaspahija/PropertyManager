package net.codejava.controller;


import net.codejava.dto.PropertyDto;
import net.codejava.dto.ReservationDto;
import net.codejava.model.Reservation;
import net.codejava.repository.PropertyRepository;
import net.codejava.repository.ReservationRepository;
import net.codejava.repository.UserRepository;
import net.codejava.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
public class ReservationController {
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private ReservationRepository repo;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PropertyRepository propertyRepository;

    @GetMapping("reservation")
    public ReservationDto getReservationByReservationId(@RequestParam Integer reservationId) {
        return reservationService.getReservationByReservationId(reservationId);
    }

    @GetMapping("reservations")
    public List<ReservationDto> getReservations() {
        return reservationService.getReservations();
    }

    @GetMapping("reservationsByPropertyId")
    public List<ReservationDto> getReservationsByPropertyId(@RequestParam Integer propertyId) {
        return reservationService.getReservationsByPropertyId(propertyId);
    }

    @GetMapping("reservationsByUserId")
    public List<ReservationDto> getReservationsByUserId(@RequestParam Integer userId) {
        return reservationService.getReservationsByUserId(userId);
    }

    @PostMapping("reservation")
    public ReservationDto addReservation(@RequestBody ReservationDto reservationDto) {
        return reservationService.addReservation(reservationDto);
    }

    @DeleteMapping("reservation")
    public boolean deleteProperty(@RequestParam Integer reservationId) {
        return reservationService.deleteReservation(reservationId);
    }

    @PutMapping("reservation")
    public ReservationDto updateReservation(@RequestBody ReservationDto reservationDto) {
        return reservationService.updateReservation(reservationDto);
    }

    @GetMapping("reservationsByDates")
    public List<ReservationDto> getReservationsByDates(@RequestParam Date start, @RequestParam Date end ) {
        return reservationService.getReservationsByDates(start, end);
    }

}
