package com.flowergarden.model;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement
public class Tulip extends AbstractPetalable {

    @XmlElement
    private int petals;

    public Tulip(int petals, int lenght, float price, FreshnessInteger fresh) {
        super(fresh, price, lenght, petals);
    }

    public Tulip() {

    }

    @Override
    public int getPetals() {
        return petals;
    }

    @Override
    public void setPetals(int petals) {
        this.petals = petals;
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
                ", length=" + length +
                '}';
    }
}
