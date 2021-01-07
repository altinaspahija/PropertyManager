package net.codejava.repository;

import net.codejava.model.PaymentDetails;
import net.codejava.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails, Integer> {

    @Query("SELECT p FROM PaymentDetails p WHERE p.reservationId = :reservation_id")
    public List<PaymentDetails> getPaymentDetailsByReservationId(@Param("reservation_id") Integer reservationId);

    @Query("SELECT p FROM PaymentDetails p JOIN Reservation r " +
            "ON p.reservationId = r.reservationId " +
            "WHERE r.userId = :user_id")
    public List<PaymentDetails> getPaymentDetailsByUserId(@Param("user_id") Integer userId);

    @Modifying
    @Query(value = "INSERT INTO PaymentDetails(paymentId,reservationId,paymentDate," +
            "cardHolderName,creditCardNo,expDate,csv) " +
            "VALUES (:payment_id,:reservation_id,:payment_date,:card_holder_name," +
            ":credit_card_no,:exp_date,:csv) ", nativeQuery = true)
    PaymentDetails addPaymentDetails(@Param("payment_id")Integer paymentId,
                                 @Param("reservation_id")Integer reservationId,
                                 @Param("payment_date")Date paymentDate,
                                 @Param("card_holder_name")String cardHolderName,
                                 @Param("credit_card_no")long creditCardNo,
                                 @Param("exp_date")Date expDate,
                                 @Param("csv") Integer csv);
}



