package com.code4fun.algo.sorting;

/**
 * Created by yadab on 2/8/2016.
 */
public class InsertionSort<T extends Comparable> implements Sort<T>{
    @Override
    public void sort(T[] elements) {
        if(elements.length <=1){
            return;
        }
        for(int i=1; i<elements.length; i++){
            for(int j=i; j>0 && (elements[j-1].compareTo(elements[j]) > 0) ;j--){
                swap(elements, j-1, j);
            }
        }
    }
    private void swap(T[] arr, int j, int i) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
