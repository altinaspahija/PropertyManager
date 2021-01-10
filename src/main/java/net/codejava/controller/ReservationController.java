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

    @GetMapping("/reservationsbypropertyid")
    public List<ReservationDto> getReservationsByPropertyId(@RequestParam Integer propertyId) {
        List<ReservationDto> reservationsByPropertyId = reservationService.getReservationsByPropertyId(propertyId);
        return reservationsByPropertyId;
    }

    @GetMapping("/getReservationsByUserId")
    public List<ReservationDto> getReservationsByUserId(@RequestParam Integer userId) {
        List<ReservationDto> reservationsByUserId = reservationService.getReservationsByUserId(userId);
        return reservationsByUserId;
    }

    @PostMapping("/addreservation")
    public ReservationDto addReservation(@RequestBody ReservationDto reservationDto) {
        return reservationService.addReservation(reservationDto);
    }

    @DeleteMapping("deletereservation")
    public boolean deleteProperty(@RequestParam Integer reservationId) {
        return reservationService.deleteReservation(reservationId);
    }

    @PutMapping("reservation")
    public ReservationDto updateReservation(@RequestParam int reservationId, @RequestBody ReservationDto reservationDto) {

        return reservationService.updateReservation(reservationDto, reservationId);
    }


    @GetMapping("/getReservationsByDates")
    public List<ReservationDto> getReservationsByDates(@RequestParam Date start, @RequestParam Date end ) {
        List<ReservationDto> reservationsByDates = reservationService.getReservationsByDates(start, end);
        return reservationsByDates;
    }

}
