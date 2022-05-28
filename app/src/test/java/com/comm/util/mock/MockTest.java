package com.comm.util.mock;

import java.util.List;

import com.comm.util.test.Trojan;
import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
//https://medium.com/androiddevelopers/mock-final-and-static-methods-on-android-devices-b383da1363ad
public class MockTest {

    @Test
    public void testStubbig(){
        Trojan mockT = mock(Trojan.class);
        // mock objects return a default value when not stubbed
        assertNull(mockT.open());
        when(mockT.open()).thenReturn("soldiers");
        assertEquals("soldiers",mockT.open());
    }

    @Test
    public void testList(){
// mock creation
        List mockedList = mock(List.class);

// using mock object - it does not throw any "unexpected interaction" exception
        mockedList.add("one");
        mockedList.clear();

// selective, explicit, highly readable verification
//        verify(mockedList).add("one");
//        verify(mockedList).clear();
    }

}
