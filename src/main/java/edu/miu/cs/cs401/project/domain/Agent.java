package edu.miu.cs.cs401.project.domain;

import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

public class Agent {
    private final UUID uuid = UUID.randomUUID();
    private final List<Passenger> passengers = new ArrayList<>();
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
