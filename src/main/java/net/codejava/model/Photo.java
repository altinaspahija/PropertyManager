package net.codejava.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "photo")
public class Photo {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String photo_id;

    private String name;

    private String type;

    private byte[] data;

    public Photo() {
    }

    public Photo(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }

}
