package com.flowergarden.flowers;

import com.flowergarden.properties.FreshnessInteger;

import java.util.Objects;

public class Chamomile extends AbstractPetalable {

    public Chamomile(int petals, int lenght, float price, FreshnessInteger fresh) {
        super(fresh, price, lenght, petals);
    }

    public Chamomile() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chamomile chamomile = (Chamomile) o;
        return petals == chamomile.petals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(petals);
    }

    @Override
    public String toString() {
        return "Chamomile{" +
                "id=" + id +
                ", petals=" + petals +
                ", freshness=" + freshness +
                ", price=" + price +
                ", lenght=" + lenght +
                '}';
    }
}
