package net.codejava.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data
@Entity
@Table(name = "property")
public class Property {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="property_id")
    private Integer propertyId;

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

    @Column(name="available_from_date")
    private Date availableFrom;

    @Column(name="available_to_date")
    private Date availableTo;

    @Column(name="user_id")
    private Integer userId;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id",insertable=false ,updatable = false)
    private User user;

    @OneToMany
    @JoinColumn(name="reservation_id")
    private List<Reservation> reservation;

    @OneToMany
    @JoinColumn(name="photo_id")
    private List<Photo> photo;

    /*public Integer getId() {
        return propertyId;
    }

    public Property() {
    }

    public void setId(Integer property_id) {
        this.propertyId = property_id;
    }

    public String getCountry(){ return country;}

    public void setCountry(String country) { this.country = country; }

    public String getAddress() {return address;}

    public void setAddress(String address) { this.address = address;}

    public String getDescription(){return description;}

    public void setDescription(String description){this.description = description;}

    public String getPropertyType(){return propertyType;}

    public void setPropertyType(String property_type) {this.propertyType =property_type;}

    public Date getAvailableFrom(){return availableFrom;}

    public void setAvailableFrom(Date availableFrom) { this.availableFrom = availableFrom; }

    public Date getAvailableTo(){return availableTo;}

    public void setAvailableTo(Date availableTo){this.availableTo = availableTo;}

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Integer getUserId() {return userId;}

    public void setUserId(Integer userId) {this.userId = userId;}*/


}
