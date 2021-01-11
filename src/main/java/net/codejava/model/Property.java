package net.codejava.model;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "property")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="property_id", insertable = true)
    private int propertyId;

    @Column(name="country")
    private String country;

    @Column(name="address")
    private String address;

    @Column(name="price")
    private float price;

    @Column(name="description")
    private String description;

    @Column(name="property_type")
    private String propertyType;

    @Column(name="user_id")
    private Integer userId;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id",insertable=false ,updatable = false)
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "property")
    private List<Reservation> reservation;
}
