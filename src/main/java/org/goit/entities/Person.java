package org.goit.entities;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;


@Data
@Entity
public class Person {
    @Transient
    private static final String sqlQuery = "SELECT p FROM Person p";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "parking_right")
    private int parkingRight;
    @ManyToOne
    @JoinColumn(name = "residential_flat", referencedColumnName = "id")
    private Flat residentialFlat;

    @OneToMany(mappedBy = "person", cascade = CascadeType.REMOVE)
    private List<Ownership> ownerships;


    @Override
    public String toString() {
        String flatId = this.residentialFlat == null ? null : String.valueOf(residentialFlat.getId());
        String address = this.residentialFlat == null ? "not resident" : residentialFlat.getBuilding().getAddress();
        String area = this.residentialFlat == null ? null : String.valueOf(residentialFlat.getArea());
        return "Person{" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address= " + address +
                ", flat # " + flatId +
                ", area " + area +
                ", parkingRight=" + parkingRight +
                ", residentialFlatId=" + flatId +
                '}';
    }
}
