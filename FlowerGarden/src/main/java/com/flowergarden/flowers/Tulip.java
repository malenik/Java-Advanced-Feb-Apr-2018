package com.flowergarden.flowers;


import com.flowergarden.properties.FreshnessInteger;

import java.util.Objects;

public class Tulip extends GeneralFlower {

    private int petals;

    public Tulip(int petals, int lenght, float price, FreshnessInteger fresh) {
        super(fresh, price, lenght);
        this.petals = petals;
    }

    public boolean getPetal() {
        if (petals <= 0) return false;
        petals = -1;
        return true;
    }

    public int getPetals() {
        return petals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tulip tulip = (Tulip) o;
        return petals == tulip.petals;
    }

    @Override
    public int hashCode() {

        return Objects.hash(petals);
    }

    @Override
    public String toString() {
        return "Tulip{" +
                "id=" + id +
                ", petals=" + petals +
                ", freshness=" + freshness +
                ", price=" + price +
                ", lenght=" + lenght +
                '}';
    }
}
