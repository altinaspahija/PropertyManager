package net.codejava;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "property")
public class Property {
    private Integer property_id;
    private String country;
    private String address;
    private float price;
    private String description;
    private String propertytype;
    private Date available_from_date;
    private Date available_to_date;
    private Integer user_id;

    protected Property() {
    }

    @Id
    @Column(name = "property_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public Integer getId() {
        return property_id;
    }

    public void setId(Integer property_id) {
        this.property_id = property_id;
    }

    public String getCountry(){ return country;}

    public void setCountry(String country) { this.country = country; }

    public String getAddress() {return address;}

    public void setAddress(String address) { this.address = address;}

    public String getDescription(){return description;}

    public void setDescription(String description){this.description = description;}

    public String getPropertytype(){return propertytype;}

    public void setPropertytype(String property_type) {this.propertytype=property_type;}

    public Date getAvailable_from_date(){return available_from_date;}

    public void setAvailable_from_date(Date available_from_date) { this.available_from_date = available_from_date; }

    public Date getAvailable_to_date(){return available_to_date;}

    public void setAvailable_to_date(Date available_to_date){this.available_to_date = available_to_date;}

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Integer getUser_id() {return user_id;}

    public void setUser_id(Integer user_id) {this.user_id = user_id;}

    @ManyToOne(fetch=FetchType.LAZY)
    private User user;

}
