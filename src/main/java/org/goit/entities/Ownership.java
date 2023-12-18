package org.goit.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Ownership {

    @Transient
    private static final String sqlQuery = "SELECT o FROM Ownership o";
    @Id
    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @Id
    @ManyToOne
    @JoinColumn(name = "flat_id", referencedColumnName = "id")
    private Flat flat;

    @Override
    public String toString() {
        return "Ownership{" +
                "personId=" + person.getId() +
                ", flatId=" + flat.getId() +
                '}';
    }
}
