package com.code4fun.algo.sorting;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by yadab on 2/8/2016.
 */
public class QuickSortTest {
    private Sort<Integer> sorter = new QuickSort<>();
    @Test
    public void testSort() throws Exception {
        Integer[] array = {2, 3, 5, 6, 4, 10, 12, 17, 16, 1};
        Integer[] expected = {1, 2, 3, 4, 5, 6, 10, 12, 16, 17};
        sorter.sort(array);
        Assert.assertTrue(Arrays.equals(array, expected));
    }
}