package com.flowergarden.bouquet;

import com.flowergarden.flowers.Chamomile;
import com.flowergarden.flowers.GeneralFlower;
import com.flowergarden.flowers.Rose;
import com.flowergarden.flowers.Tulip;
import com.flowergarden.properties.Freshness;
import com.flowergarden.properties.FreshnessInteger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collection;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MarriedBouquetTest {

    private MarriedBouquet bouquet;

    @Mock
    private Rose mockRose;

    @Mock
    private Chamomile mockChamomile;

    @Before
    public void createBouquet() {
        when(mockRose.getLenght()).thenReturn(2);
        when(mockChamomile.getLenght()).thenReturn(4);
        when(mockChamomile.compareTo(mockRose)).thenReturn(1);
        bouquet = new MarriedBouquet();
        bouquet.addFlower(mockRose);
        bouquet.addFlower(mockChamomile);
    }

    @Test
    public void findByLengthTest() {
        Collection<GeneralFlower> flowers = bouquet.searchFlowersByLenght(1, 3);
        Assert.assertEquals(1, flowers.size());
    }

    @Test
    public void findByLengthSameStartAndEndTest() {
        Collection<GeneralFlower> flowers = bouquet.searchFlowersByLenght(2, 2);
        Assert.assertEquals(1, flowers.size());
    }

    @Test
    public void findByLengthStartIsBiggerThanEndTest() {
        Collection<GeneralFlower> flowers = bouquet.searchFlowersByLenght(4, 3);
        Assert.assertEquals(0, flowers.size());
    }

    @Test
    public void sortByFreshnessTest() {
        bouquet.sortByFreshness();
        Assert.assertEquals(bouquet.getFlowers().iterator().next(), mockRose);
    }
}
