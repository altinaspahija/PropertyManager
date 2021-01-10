package net.codejava.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "photo")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="photo_id")
    private int photoId;

    @Column(name="photo_description")
    private String photoDescription;

    @Column(name="photo")
    private byte photo;

    /*@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="property_id",insertable=false ,updatable = false)
    private Property property;*/

}
