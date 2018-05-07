package com.flowergarden.bouquet;

import com.flowergarden.flowers.GeneralFlower;

import java.util.Collection;

public class MarriedBouquetLongerThan extends MarriedBouquet {
    public Collection<GeneralFlower> searchFlowerLongerThan(int start) {
        return searchFlowersByLength(start, Integer.MAX_VALUE);
    }
}