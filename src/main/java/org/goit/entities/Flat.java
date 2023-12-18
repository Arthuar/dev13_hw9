package org.goit.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Flat {

    @Transient
    private static final String sqlQuery = "SELECT f FROM Flat f";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "building_id", referencedColumnName = "id")
    private Building building;

    @Column(name = "apartment_number")
    private int apartmentNumber;

    @Column(name = "area")
    private int area;

    @Column(name ="floor")
    private int floor;

    @Column(name = "num_of_rooms")
    private int numOfRooms;

    @OneToMany(mappedBy = "residentialFlat", cascade = CascadeType.DETACH)
    List<Person> persons;

    @OneToMany(mappedBy = "flat", cascade = CascadeType.REMOVE)
    private List<Ownership> ownerships;

    @Override
    public String toString() {
        return "Flat{" +
                "id=" + id +
                ", building=" + building +
                ", apartmentNumber=" + apartmentNumber +
                ", area=" + area +
                ", floor=" + floor +
                ", numOfRooms=" + numOfRooms +
                '}';
    }
}
