package com.flowergarden.classwork;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class MavenTestsManual {

    //mock creation list
    @Mock
    private List<String> mockedList;

    @Test
    public void test1(){

        mockedList = mock(List.class);
        //using mock object
        when(mockedList.get(anyInt())).thenReturn("first", "second");
        /*mockedList.add("one");
        mockedList.clear();
        */

        //when(mockedList.get(0)).thenReturn("first");
        //when(mockedList.get(0)).thenThrow(new RuntimeException());

        System.out.println(mockedList.get(0));
        System.out.println(mockedList.get(1));
        System.out.println(mockedList.get(999));

        //verification
        //verify(mockedList).get(1);
        //verify(mockedList, atLeast(1)).get(anyInt());

        InOrder inOrder = inOrder(mockedList);
        inOrder.verify(mockedList).get(0);

    }
}
