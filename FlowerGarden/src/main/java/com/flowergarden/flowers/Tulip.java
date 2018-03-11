package com.flowergarden.flowers;


import com.flowergarden.properties.FreshnessInteger;

import java.util.Objects;

public class Tulip extends GeneralFlower {

    private int petals;

    public Tulip(int petals, int lenght, float price, FreshnessInteger fresh) {
        this.petals = petals;
        this.lenght = lenght;
        this.price = price;
        this.freshness = fresh;
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
}