package net.codejava.controller;

import net.codejava.dto.ReservationDto;
import net.codejava.exception.ReservationNotFoundException;
import net.codejava.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.sql.Date;
import java.util.List;

@RestController
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    //http://localhost:9090/reservation/reservationId GET
    @GetMapping("reservation/{reservationId}")
    public ReservationDto getReservationByReservationId(@PathVariable Integer reservationId) {
        return reservationService.getReservationByReservationId(reservationId);
    }

    //http://localhost:9090/reservations GET
    @GetMapping("reservations")
    public List<ReservationDto> getReservations() throws ReservationNotFoundException {
        return reservationService.getReservations();
    }

    //http://localhost:9090/reservations/reservationId GET
    @GetMapping("reservations/{reservationId}")
    public List<ReservationDto> getReservationsByPropertyId(@PathVariable Integer propertyId) throws ReservationNotFoundException {
        return reservationService.getReservationsByPropertyId(propertyId);
    }

    //http://localhost:9090/reservations/userId GET
    @GetMapping("reservations/{userId}")
    public List<ReservationDto> getReservationsByUserId(@PathVariable Integer userId) throws ReservationNotFoundException {
        return reservationService.getReservationsByUserId(userId);
    }

    //http://localhost:9090/reservation POST
    @PostMapping("reservation/{propertyId}")
    public ReservationDto addReservation(@RequestBody ReservationDto reservationDto) {
        return reservationService.addReservation(reservationDto);
    }

    //http://localhost:9090/reservation DELETE
    @DeleteMapping("reservation")
    public boolean deleteProperty(@RequestParam Integer reservationId) throws ReservationNotFoundException {
        return reservationService.deleteReservation(reservationId);
    }

    //http://localhost:9090/reservation PUT
    @PutMapping("reservation")
    public ReservationDto updateReservation(@RequestParam int reservationId,
                                            @RequestBody ReservationDto reservationDto) throws ReservationNotFoundException {
        return reservationService.updateReservation(reservationId,reservationDto);
    }

    //http://localhost:9090/reservationsByDates GET
    @GetMapping("reservationsByDates")
    public List<ReservationDto> getReservationsByDates(@RequestParam Date start, @RequestParam Date end ) {
        return reservationService.getReservationsByDates(start, end);
    }

}
