package net.codejava.model;

import lombok.Data;
import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "payment_details")
public class PaymentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="payment_id")
    private int paymentId;

    @Column(name="payment_date")
    private Date paymentDate;

    @Column(name="card_holder_name")
    private String cardHolderName;

    @Column(name="credit_card_no")
    private long creditCardNo;

    @Column(name="exp_date")
    private Date expiryDate;

    @Column(name="cvv")
    private int cvv;

    @OneToOne(mappedBy = "paymentDetails")
    private Reservation reservation;
}
