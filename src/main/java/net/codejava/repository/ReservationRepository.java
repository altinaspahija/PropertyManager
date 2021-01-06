package net.codejava.repository;

import net.codejava.model.Reservation;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Integer>{

    //@Query("SELECT r FROM Reservation r WHERE r.user_id = :user_id")
    //The following is used considering that we are getting a list of all the user's reservations (past or present).
    //public List<Reservation> getReservationsByUserId(@Param("user_id") Integer user_id);

    //@Query("SELECT r FROM Reservation r WHERE r.property_id = :property_id")
    //public Reservation getReservationByReservationId(@Param("property_id") Integer property_id);
}
