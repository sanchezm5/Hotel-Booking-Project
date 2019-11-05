package com.company.hoteledgeservice.model;

import java.util.Objects;

public class Room {

    private Integer id;
    private Integer number;
    private String occupant;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getOccupant() {
        return occupant;
    }

    public void setOccupant(String occupant) {
        this.occupant = occupant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(id, room.id) &&
                Objects.equals(number, room.number) &&
                Objects.equals(occupant, room.occupant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, occupant);
    }
}
