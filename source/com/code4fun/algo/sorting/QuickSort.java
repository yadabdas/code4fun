package com.code4fun.algo.sorting;

/**
 * Created by yadab on 2/8/2016.
 */
public class QuickSort<T extends Comparable> implements Sort<T> {
    @Override
    public void sort(T[] elements) {
        sort(elements, 0, elements.length-1);
    }

    private void sort(T[] elements, int lo, int hi) {
        if(lo >=hi){
            return;
        }
        int partition = partition(elements, lo, hi);
        sort(elements,lo, partition);
        sort(elements, partition+1, hi);
    }

    private int partition(T[] elements, int lo, int hi){
        int i = lo -1;
        int j = hi +1;
        T pivot = elements[lo];
        for(;;){
            do {
                i=i+1;
            }while (elements[i].compareTo(pivot) < 0 && i< hi);
            do {
                j=j-1;
            }while (elements[j].compareTo(pivot) > 0 && j > 0);
            if(i>=j){
                return j;
            }
            swap(elements, i, j);
        }
    }
    private void swap(T[] arr, int j, int i) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
