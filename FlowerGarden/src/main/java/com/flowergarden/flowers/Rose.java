package com.flowergarden.flowers;

import javax.xml.bind.annotation.XmlRootElement;

import com.flowergarden.properties.FreshnessInteger;

import java.util.Objects;

@XmlRootElement
public class Rose extends GeneralFlower {

    private boolean spike;

    public Rose(boolean spike, int lenght, float price, FreshnessInteger fresh) {
        super(fresh, price, lenght);
        this.spike = spike;
    }

    public boolean getSpike() {
        return spike;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rose rose = (Rose) o;
        return spike == rose.spike;
    }

    @Override
    public int hashCode() {

        return Objects.hash(spike);
    }

    @Override
    public String toString() {
        return "Rose{" +
                "id=" + id +
                ", spike=" + spike +
                ", freshness=" + freshness +
                ", price=" + price +
                ", lenght=" + lenght +
                '}';
    }
}
