package net.codejava.repository;

import net.codejava.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query("SELECT r FROM Reservation r WHERE r.userId = :user_id")
    //The following is used considering that we are getting a list of all the user's reservations (past or present).
    public List<Reservation> getReservationsByUserId(@Param("user_id") Integer userId);

    @Query("SELECT r FROM Reservation r WHERE r.propertyId = :property_id")
    public List<Reservation> getReservationsByPropertyId(@Param("property_id") Integer propertyId);

    @Query("SELECT r FROM Reservation r WHERE r.reservationId = :reservation_id")
    public Reservation getReservationByReservationId(@Param("reservation_id") Integer reservationId);

    @Modifying
    @Transactional
    @Query("DELETE Reservation r WHERE r.propertyId = : property_id")
    public Reservation deleteReservationbyProperty(@Param("property_id") Integer property_id);

    @Query("DELETE Reservation r WHERE r.reservationId = : reservation_id")
    public Reservation deleteReservation(@Param("reservation_id") Integer reservationId);

    /*@Query("UPDATE Reservation r SET " +
            "r.checkIn = :checkin, " +
            "r.checkOut = :checkout," +
            "r.price = :price," +
            "r.propertyId = :property_id," +
            "r.userId = :user_id " +
            "r.paymentId = :payment_id"+
            "WHERE r.reservationId = :reservation_id")
    public Reservation updateReservation(@Param("checkin")Date checkIn,
                                         @Param("checkout")Date checkOut,
                                         @Param("price") float price,
                                         @Param("property_id") Integer propertyId,
                                         @Param("user_id") Integer userId,
                                         @Param("payment_id") Integer paymentId,
                                         @Param("reservation_id") Integer reservationId);

    @Query(value = "INSERT INTO Reservartion(reservationId,checkIn,checkOut,price,propertyId,userId,paymentId) " +
            "VALUES (:reservation_id,:checkin,:checkout,:price,:property_id,:user_id,:payment_id)" +
            "WHERE r.userId = :user_id", nativeQuery = true)
    Reservation addReservationByUserId(@Param("reservation_id") Integer reservationId,
                                       @Param("checkin")Date checkIn,
                                       @Param("checkout")Date checkOut,
                                       @Param("price") float price,
                                       @Param("property_id") Integer propertyId,
                                       @Param("payment_id") Integer paymentId,
                                       @Param("user_id") Integer userId);*/

    @Query("SELECT r FROM Reservation r")
    public List<Reservation> getReservations();
}
