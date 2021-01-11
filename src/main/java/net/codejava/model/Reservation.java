package net.codejava.model;

import lombok.Data;
import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="reservation_id")
    private int reservationId;

    @Column(name="payment_id")
    private int paymentId;

    @Column(name="checkin")
    private Date checkIn;

    @Column(name="checkout")
    private Date checkOut;

    @Column(name="price")
    private float price;

    @Column(name="property_id")
    private Integer propertyId;

    @Column(name="user_id")
    private Integer userId;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "property_id",insertable=false ,updatable = false)
    private Property property;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id",insertable=false ,updatable = false)
    private User client;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id", referencedColumnName = "payment_id",insertable=false ,updatable = false)
    private PaymentDetails paymentDetails;

}
