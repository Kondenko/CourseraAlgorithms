package com.kondenko.week3;

import org.hamcrest.comparator.ComparatorMatcherBuilder;
import org.junit.Assert;
import org.junit.Test;

import static com.kondenko.week3.NutsAndBolts.Bolt;
import static com.kondenko.week3.NutsAndBolts.Item;
import static com.kondenko.week3.NutsAndBolts.Nut;

public class NutsAndBoltsTest {

    @Test
    public void findPairs() {
        Bolt[] bolts =  new Bolt[]{
                new Bolt(1),
                new Bolt(2),
                new Bolt(3),
                new Bolt(4),
                new Bolt(5),
                new Bolt(6),
                new Bolt(7),
                new Bolt(8),
                new Bolt(9),
                new Bolt(10)
        };
        Nut[] nuts =  new Nut[]{
                new Nut(10),
                new Nut(2),
                new Nut(4),
                new Nut(1),
                new Nut(8),
                new Nut(7),
                new Nut(5),
                new Nut(6),
                new Nut(3),
                new Nut(9)
        };
        NutsAndBolts.findPairs(bolts, nuts);
        ComparatorMatcherBuilder<Item[]> builder = ComparatorMatcherBuilder.comparedBy((b, n) -> {
            int bolts1 = 0;
            int nuts1 = 0;
            for (int i = 0; i < b.length; i++) {
                bolts1 += b[i].compareTo(n[i]);
                nuts1 += n[i].compareTo(b[i]);
            }
            return bolts1 - nuts1;
        });
        Assert.assertThat(nuts, builder.comparesEqualTo(bolts));
    }

}