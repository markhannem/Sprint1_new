package com.sprint.FlightManagementSystem.aircraft;

import com.sprint.FlightManagementSystem.passenger.Passenger;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "aircraft")
public class Aircraft {
    @Id
    @SequenceGenerator(name = "aircraft_sequence", sequenceName = "aircraft_sequence", allocationSize = 1, initialValue= 1)
    @GeneratedValue(generator = "aircraft_sequence")
    private Long id;

    @Column(name="type")
    private String type;

    @Column(name = "airlineName")
    private String airlineName;

    @Column(name = "numberOfPassengers")
    private int numberOfPassengers;


    @ManyToMany
    @JoinTable(name = "aircraft_passenger",
            joinColumns = @JoinColumn(name = "aircraft_id"),
            inverseJoinColumns = @JoinColumn(name = "passenger_id"))
    private List<Passenger> passengers;

    public Aircraft() {
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }
    public String getAirlineName() {
        return airlineName;
    }
    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }
    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

}
