package com.comm.util.resume;

import android.util.ArrayMap;
import android.util.SparseArray;
import androidx.test.runner.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class HashMapTest {

    @Test
    public void SparseArray() {
        SparseArray<Integer> integerSparseArray = new SparseArray<>();
        integerSparseArray.put(10, 9);
        integerSparseArray.put(4, 2);
        integerSparseArray.put(1, 1);
        integerSparseArray.put(8, 5);
    }


    @Test
    public void arrayMap() {
        ArrayMap<Integer, Integer> integerSparseArray = new ArrayMap<>();
        integerSparseArray.put(10, 9);
        integerSparseArray.put(4, 2);
        integerSparseArray.put(1, 1);
        integerSparseArray.put(8, 5);


    }
}
