package com.flowergarden.flowers;

import com.flowergarden.properties.FreshnessInteger;

import javax.xml.bind.annotation.XmlElement;
import java.util.Objects;

public class Chamomile extends GeneralFlower {

    @XmlElement
    private int petals;

    public Chamomile(int petals, int lenght, float price, FreshnessInteger fresh) {
        super(fresh, price, lenght);
        this.petals = petals;
    }

    public Chamomile() {

    }

    public boolean getPetal() {
        if (petals <= 0) return false;
        petals = -1;
        return true;
    }

    public int getPetals() {
        return petals;
    }

    public void setPetals(int petals) {
        this.petals = petals;
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
