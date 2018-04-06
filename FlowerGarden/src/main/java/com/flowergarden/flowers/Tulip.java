package com.flowergarden.flowers;


import com.flowergarden.properties.FreshnessInteger;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
@XmlRootElement
public class Tulip extends AbstractPetalable {
    public Tulip(int petals, int lenght, float price, FreshnessInteger fresh) {
        super(fresh, price, lenght, petals);
    }

    public Tulip() {

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
