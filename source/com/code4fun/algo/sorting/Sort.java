package com.code4fun.algo.sorting;

/**
 * Created by yadab on 2/8/2016.
 */
public interface Sort<T extends Comparable> {
    /**
     * sorts input element in ascending or descending order based on comparator.
     * @param elements
     */
    void sort(T[] elements);
}
