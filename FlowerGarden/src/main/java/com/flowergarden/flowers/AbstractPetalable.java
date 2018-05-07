package com.flowergarden.flowers;

import com.flowergarden.properties.FreshnessInteger;

import javax.xml.bind.annotation.XmlElement;

public abstract class AbstractPetalable extends GeneralFlower implements Petalalable {

    /**
     * This class was developed in order to eliminate duplication of code with Chamomile and Tulip flowers
     * in the method 'updateFlower' from FlowerDAOImpl.class because of their complete similarity.
     */

    @XmlElement
    private int petals;

    public AbstractPetalable() {
    }

    public AbstractPetalable(FreshnessInteger freshness, float price, int lenght, int petals) {
        super(freshness, price, lenght);
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


    public void setPetals(int petals) {
        this.petals = petals;
    }

}
