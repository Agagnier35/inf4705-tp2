package com.inf4705.tp2.model;

import java.util.Objects;

public class Dynamite {

    private int id;
    private int power;

    public Dynamite(int id, int power) {
        this.id = id;
        this.power = power;
    }

    public int getId() {
        return id;
    }

    public int getPower() {
        return power;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dynamite dynamite = (Dynamite) o;
        return id == dynamite.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
