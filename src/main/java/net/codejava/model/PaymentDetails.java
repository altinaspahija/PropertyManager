package net.codejava.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "paymentDetails")
public class PaymentDetails {

    //Generating property Id.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="payment_id")
    private int paymentId;

    //I do not think we need reservationId here since we have established a relation below.
    //private int reservationId;
    @Column(name="payment_date")
    private Date paymentDate;

    @Column(name="card_holder_name")
    private String cardHolderName;

    @Column(name="credit_card_no")
    private long creditCardNo;

    @Column(name="exp_date")
    private Date expDate;

    @Column(name="csv")
    private int csv;

    @OneToOne(mappedBy = "paymentDetails")
    private Reservation reservation;
}
