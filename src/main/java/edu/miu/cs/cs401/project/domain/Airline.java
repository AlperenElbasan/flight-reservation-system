package edu.miu.cs.cs401.project.domain;

import java.util.UUID;

public class Airline {
    private final UUID uuid = UUID.randomUUID();
    private String code;
    private String name;
    private String history;
    public Airline(String code, String name, String history) {
        this.code = code;
        this.name = name;
        this.history = history;
    }
    public UUID getUuid() {
        return uuid;
    }
    public String getCode() {
        return code;
    }
    public String getName() {
        return name;
    }
    public String getHistory() {
        return history;
    }
    @Override
    public String toString() {
        return "Airline [code=" + code + ", name=" + name + ", history=" + history + "]";
    }
}
