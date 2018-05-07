package com.flowergarden.flowers;

import com.flowergarden.properties.Freshness;

public class FlowerDecorator implements Flower<Integer> {

    private GeneralFlower flower;

    public FlowerDecorator(GeneralFlower f) {
        flower = f;
    }

    public Freshness<Integer> getFreshness() {
        return flower.getFreshness();
    }

    public float getPrice() {
        return flower.getPrice();
    }

    public int getLength() {
        return flower.getLength();
    }


}
