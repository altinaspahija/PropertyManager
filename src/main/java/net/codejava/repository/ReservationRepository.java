package net.codejava.repository;

import net.codejava.model.Reservation;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Integer>{

    @Query("SELECT r FROM Reservation r WHERE r.userId = :user_id")
    //The following is used considering that we are getting a list of all the user's reservations (past or present).
    public List<Reservation> getReservationsByUserId(@Param("user_id") Integer userId);

    @Query("SELECT r FROM Reservation r WHERE r.propertyId = :property_id")
    public List<Reservation> getReservationByPropertyId(@Param("property_id") Integer propertyId);
}
