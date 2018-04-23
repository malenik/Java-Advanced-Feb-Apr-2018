package com.flowergarden.bouquet;

import com.flowergarden.flowers.Chamomile;
import com.flowergarden.flowers.GeneralFlower;
import com.flowergarden.flowers.Rose;
import com.flowergarden.flowers.Tulip;
import com.flowergarden.properties.FreshnessInteger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MarriedBouquetTest {

    /**
     * The 2nd homework in which we had to create tests based on mock-objects from MarriedBouquet.class
     */
    private Random rand = new Random();

    @Mock
    private Rose mockRose;

    @Mock
    private Chamomile mockChamomile;

    @Mock
    private Tulip mockTulip;


    /**
     * Use mock for testing getPrice method
     */

    @Test
    public void getPriceMock() {
        float price = rand.nextFloat();
        int assemblePriceTest = rand.nextInt();
        float expectedResult = assemblePriceTest + price + price + price;

        when(mockRose.getPrice()).thenReturn(price);

        List<GeneralFlower> generalFlowers = new ArrayList<>();
        generalFlowers.add(mockRose);
        generalFlowers.add(mockRose);
        generalFlowers.add(mockRose);

        MarriedBouquet underTest = new MarriedBouquet(assemblePriceTest, generalFlowers);

        float actualResult = underTest.getPrice();

        Assert.assertEquals(expectedResult, actualResult, 0.001);
    }

    /**
     * Use mocks for testing 'addFlower' method
     */

    @Test
    public void addMockFlower() {

        int assemblePrice = rand.nextInt();
        List<GeneralFlower> mockedFlowers = mock(List.class);

        MarriedBouquet underTest = new MarriedBouquet(assemblePrice, mockedFlowers);

        underTest.addFlower(mockTulip);
        underTest.addFlower(mockTulip);
        underTest.addFlower(mockTulip);

        verify(mockedFlowers, times(3)).add(mockTulip);

    }

    /**
     * Use mocks for testing 'addFlowers' method
     */

    @Test
    public void addMockFlowers() {

        int assemblePrice = rand.nextInt();
        List<GeneralFlower> mockedFlowers = mock(List.class);
        List<GeneralFlower> addFlowersList = new ArrayList<>();

        MarriedBouquet underTest = new MarriedBouquet(assemblePrice, mockedFlowers);

        underTest.addFlowers(addFlowersList);

        verify(mockedFlowers, times(1)).addAll(addFlowersList);

    }

    /**
     * Use mocks for testing 'sortMocksByStemLength' method
     */

    @Test
    public void sortMocksByStemLength() {
        int assemblePrice = rand.nextInt();

        mockRose = mock(Rose.class);
        mockChamomile = mock(Chamomile.class);
        mockTulip = mock(Tulip.class);

        List<GeneralFlower> mockedFlowers = Arrays.asList(mockRose, mockChamomile, mockTulip);

        when(mockRose.getLength()).thenReturn(90);
        when(mockChamomile.getLength()).thenReturn(70);
        when(mockTulip.getLength()).thenReturn(80);

        Collection<GeneralFlower> expectedResult = Arrays.asList(mockChamomile, mockTulip);

        MarriedBouquet underTest = new MarriedBouquet(assemblePrice, mockedFlowers);

        Collection<GeneralFlower> actualResult = underTest.searchFlowersByLength(50, 80);

        Assert.assertEquals(actualResult, expectedResult);
    }

    /**
     * Use mocks for testing 'sortMocksByFreshness' method
     */

    @Test
    public void sortMocksByFreshness() {
        Integer expectedFirstFreshness = 90;
        Integer expectedSecondFreshness = 80;
        Integer expectedThirdFreshness = 70;

        int assemblePrice = rand.nextInt();

        mockRose = mock(Rose.class);
        mockChamomile = mock(Chamomile.class);
        mockTulip = mock(Tulip.class);

        List<GeneralFlower> mockedFlowers = new ArrayList<>();
        mockedFlowers.add(mockRose);
        mockedFlowers.add(mockChamomile);
        mockedFlowers.add(mockTulip);

        FreshnessInteger freshnessRoseMocked = mock(FreshnessInteger.class);
        FreshnessInteger freshnessChamomilleMocked = mock(FreshnessInteger.class);
        FreshnessInteger freshnessTulipMocked = mock(FreshnessInteger.class);

        when(mockRose.getFreshness()).thenReturn(freshnessRoseMocked);
        when(mockChamomile.getFreshness()).thenReturn(freshnessChamomilleMocked);
        when(mockTulip.getFreshness()).thenReturn(freshnessTulipMocked);

        when(freshnessChamomilleMocked.getFreshness()).thenReturn(expectedSecondFreshness);
        when(freshnessRoseMocked.getFreshness()).thenReturn(expectedFirstFreshness);
        when(freshnessTulipMocked.getFreshness()).thenReturn(expectedThirdFreshness);

        MarriedBouquet underTest = new MarriedBouquet(assemblePrice, mockedFlowers);

        underTest.sortByFreshness();

        Assert.assertEquals(underTest.flowerList.get(0).getFreshness().getFreshness(), expectedFirstFreshness);
        Assert.assertEquals(underTest.flowerList.get(1).getFreshness().getFreshness(), expectedSecondFreshness);
        Assert.assertEquals(underTest.flowerList.get(2).getFreshness().getFreshness(), expectedThirdFreshness);

    }
}
