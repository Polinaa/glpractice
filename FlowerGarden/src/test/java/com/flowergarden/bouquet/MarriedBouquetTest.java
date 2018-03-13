package com.flowergarden.bouquet;

import com.flowergarden.flowers.Chamomile;
import com.flowergarden.flowers.GeneralFlower;
import com.flowergarden.flowers.Rose;
import com.flowergarden.flowers.Tulip;
import com.flowergarden.properties.FreshnessInteger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MarriedBouquetTest {

    private MarriedBouquet bouquet;

    @Mock
    private Rose mockRose;

    @Mock
    private Chamomile mockChamomile;

    @Mock
    private Tulip mockTulip;

    @Mock
    private FreshnessInteger freshnessIntegerRose;

    @Mock
    private FreshnessInteger freshnessIntegerChamoline;

    @Mock
    private FreshnessInteger freshnessIntegerTulip;

    @Before
    public void createBouquet() {
        when(freshnessIntegerRose.getFreshness()).thenReturn(3);
        when(freshnessIntegerChamoline.getFreshness()).thenReturn(1);
        when(freshnessIntegerTulip.getFreshness()).thenReturn(2);

        when(mockRose.getFreshness()).thenReturn(freshnessIntegerRose);
        when(mockChamomile.getFreshness()).thenReturn(freshnessIntegerChamoline);
        when(mockTulip.getFreshness()).thenReturn(freshnessIntegerTulip);

        when(mockRose.getLenght()).thenReturn(2);
        when(mockChamomile.getLenght()).thenReturn(4);
        when(mockTulip.getLenght()).thenReturn(5);

        when(mockChamomile.compareTo(any(GeneralFlower.class))).thenCallRealMethod();
        when(mockTulip.compareTo(any(GeneralFlower.class))).thenCallRealMethod();

        bouquet = new MarriedBouquet();

        bouquet.addFlower(mockRose);
        bouquet.addFlower(mockChamomile);
        bouquet.addFlower(mockTulip);
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
        List flowers = new ArrayList<>(bouquet.getFlowers());

        Assert.assertEquals(mockChamomile, flowers.get(0));
        Assert.assertEquals(mockTulip, flowers.get(1));
    }
}
