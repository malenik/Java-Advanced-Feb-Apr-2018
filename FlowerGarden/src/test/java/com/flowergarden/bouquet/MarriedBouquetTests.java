package com.flowergarden.bouquet;

import com.flowergarden.flowers.Chamomile;
import com.flowergarden.flowers.GeneralFlower;
import com.flowergarden.flowers.Rose;
import com.flowergarden.flowers.Tulip;
import com.flowergarden.properties.FreshnessInteger;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class MarriedBouquetTests {

    /**
     * The 1st homework task in which we had to create junit-tests, based on MarriedBouquet.class
     */

    private Random rand = new Random();

    /**
     * Test the method by sum of all prices of flowers in bouquet
     */

    @Test
    public void getPrice() {
        int assemblePriceTest = rand.nextInt();
        float price1 = rand.nextFloat();
        float price2 = rand.nextFloat();
        float price3 = rand.nextFloat();

        float expectedResult = assemblePriceTest + price1 + price2 + price3;

        List<GeneralFlower> generalFlowers = new ArrayList<>();
        generalFlowers.add(new Rose(true, 1, price1, new FreshnessInteger(1)));
        generalFlowers.add(new Tulip(5, 2, price2, new FreshnessInteger(3)));
        generalFlowers.add(new Chamomile(3, 3, price3, new FreshnessInteger(4)));

        MarriedBouquet underTest = new MarriedBouquet(assemblePriceTest, generalFlowers);

        float actualResult = underTest.getPrice();

        Assert.assertEquals(expectedResult, actualResult, 0.001);
    }

    /**
     * Test the method by filling multiple values into the list
     */

    @Test
    public void addFlower() {
        float price = rand.nextFloat();
        int assemblePriceTest = rand.nextInt();
        List<GeneralFlower> generalFlowers = new ArrayList<>();
        int expectedResult = 4;

        MarriedBouquet underTest = new MarriedBouquet(assemblePriceTest, generalFlowers);
        underTest.addFlower(new Rose(true, 1, price, new FreshnessInteger(1)));
        underTest.addFlower(new Rose(false, 4, price, new FreshnessInteger(5)));
        underTest.addFlower(new Chamomile(3, 3, price, new FreshnessInteger(4)));
        underTest.addFlower(new Tulip(5, 2, price, new FreshnessInteger(3)));

        int actualResult = generalFlowers.size();

        Assert.assertEquals(expectedResult, actualResult);

    }

    /**
     * Test the method of sorting flowers by the lengths of their stems
     */

    @Test
    public void sortByStemLength() {
        int assemblePriceTest = rand.nextInt();
        List<GeneralFlower> generalFlowers = new ArrayList<>();
        float price = rand.nextFloat();

        generalFlowers.add(new Chamomile(3, 3, price, new FreshnessInteger(4)));
        generalFlowers.add(new Rose(true, 1, price, new FreshnessInteger(1)));
        generalFlowers.add(new Tulip(5, 2, price, new FreshnessInteger(3)));

        MarriedBouquet underTest = new MarriedBouquet(assemblePriceTest, generalFlowers);
        Collection<GeneralFlower> actualResult = underTest.searchFlowersByLength(0, 1);


        List<GeneralFlower> expectedResult = new ArrayList<>();

        expectedResult.add(new Rose(true, 1, price, new FreshnessInteger(1)));
        //expectedResult.add(new Tulip(5, 2, price, new FreshnessInteger(3)));
        //expectedResult.add(new Chamomile(3, 3, price, new FreshnessInteger(4)));

        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Test the method of sorting flowers by their freshness level
     */

    @Test
    public void sortByFreshness() {
        int assemblePriceTest = rand.nextInt();
        List<GeneralFlower> generalFlowers = new ArrayList<>();
        float price = rand.nextFloat();

        generalFlowers.add(new Chamomile(3, 3, price, new FreshnessInteger(4)));
        generalFlowers.add(new Rose(true, 1, price, new FreshnessInteger(1)));
        generalFlowers.add(new Tulip(5, 2, price, new FreshnessInteger(3)));

        MarriedBouquet underTest = new MarriedBouquet(assemblePriceTest, generalFlowers);
        underTest.sortByFreshness();

        Collection<GeneralFlower> actualResult = underTest.getFlowers();

        List<GeneralFlower> expectedResult = new ArrayList<>();

        expectedResult.add(new Rose(true, 1, price, new FreshnessInteger(1)));
        expectedResult.add(new Tulip(5, 2, price, new FreshnessInteger(3)));
        expectedResult.add(new Chamomile(3, 3, price, new FreshnessInteger(4)));

        Assert.assertEquals(expectedResult, actualResult);
    }
}
