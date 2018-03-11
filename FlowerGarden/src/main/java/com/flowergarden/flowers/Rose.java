package com.flowergarden.flowers;

import javax.xml.bind.annotation.XmlRootElement;

import com.flowergarden.properties.FreshnessInteger;

import java.util.Objects;

@XmlRootElement
public class Rose extends GeneralFlower {

    private boolean spike;

    public Rose(boolean spike, int lenght, float price, FreshnessInteger fresh) {
        this.spike = spike;
        this.lenght = lenght;
        this.price = price;
        this.freshness = fresh;
    }

    public Rose() {

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
}
