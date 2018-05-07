package com.flowergarden.bouquet;

import java.util.Collection;

public interface Bouquet<T> extends IPrice {

    float getPrice();

    void addFlower(T flower);

    void addFlowers(Collection<T> flowers);

    Collection<T> searchFlowersByLength(int start, int end);

    void sortByFreshness();

    Collection<T> getFlowers();

    float getAssemblePrice();

}
