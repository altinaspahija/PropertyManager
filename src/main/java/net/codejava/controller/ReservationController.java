package net.codejava.controller;


import net.codejava.dto.ReservationDto;
import net.codejava.model.Reservation;
import net.codejava.repository.PropertyRepository;
import net.codejava.repository.ReservationRepository;
import net.codejava.repository.UserRepository;
import net.codejava.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("reservation/{reservationId}")
    public ReservationDto getReservationByReservationId(@PathVariable Integer reservationId) {
        ReservationDto reservation = reservationService.getReservationByReservationId(reservationId);
        if (reservation == null){
            return null;
        }
        else{
            return reservation;
        }
    }

    @GetMapping("/reservations")
    public List<ReservationDto> getReservations() {
        return reservationService.getReservations();
    }

    @GetMapping("/reservationsbyproperty/{propertyId}")
    public List<ReservationDto> getReservationsByPropertyId(@PathVariable Integer propertyId) {
        List<ReservationDto> reservationsByPropertyId = reservationService.getReservationsByPropertyId(propertyId);
        return reservationsByPropertyId;
    }


}
