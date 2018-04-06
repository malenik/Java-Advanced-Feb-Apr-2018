package com.flowergarden.classwork;

import com.flowergarden.flowers.Tulip;
import com.flowergarden.properties.FreshnessInteger;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MarriedBouquetTests {

    @Test
    public void testFlowerList(){

        //Given - vse sozdaem
        List list = mock(List.class);
        Tulip tulip = new Tulip(2, 5, 2.0f, new FreshnessInteger(4));


        //When
        list.add(tulip);

        //Then
        verify(list).add(tulip);


    }
}
