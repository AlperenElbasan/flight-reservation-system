package edu.miu.cs.cs401.project.domain;

import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

public class Agent {
    private final UUID uuid;
    private final List<Passenger> passengers;

    public Agent() {
        this.uuid = UUID.randomUUID();;
        this.passengers = new ArrayList<>();;
    }

    public UUID getUuid() {
        return uuid;
    }
    public List<Passenger> getPassengers() {
        return passengers;
    }
    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }
}
