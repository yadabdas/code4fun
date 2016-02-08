package com.code4fun.algo.dynamic;

import java.util.*;
import java.math.BigInteger;

public class Fibonacci {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int first = sc.nextInt();
        int second = sc.nextInt();
        int n = sc.nextInt();
        System.out.println(fibonacciModifiedDynamic2(n, first, second));
        System.out.println("Fibo of "+n+":"+fibonacciDynamic1(n));
    }

    /**
     * Given the nth and (n+1)th terms, the (n+2)th can be computed by the following relation
     * Tn+2 = (Tn+1)2 + Tn
     * <p>
     * So, if the first two terms of the series are 0 and 1:
     * the third term = 12 + 0 = 1
     * fourth term = 12 + 1 = 2
     * fifth term = 22 + 1 = 5
     * ... And so on.
     * <p>
     * Given three integers A, B and N, such that the first two terms of the series (1st and 2nd terms) are A and B respectively, compute the Nth term of the series.
     */
    public static BigInteger fibonacciModifiedDynamic2(int n, int first, int second) {
        BigInteger fibo[] = new BigInteger[n + 1];
        fibo[1] = BigInteger.valueOf(first);
        fibo[2] = BigInteger.valueOf(second);
        for (int i = 3; i <= n; i++) {
            fibo[i] = fibo[i - 1].pow(2).add(fibo[i - 2]);
        }
        return fibo[n];
    }

    public static int fibonacciDynamic1(int n) {
        int fibo[] = new int[n + 1];
        fibo[1] = 1;
        fibo[2] = 1;
        for (int i = 3; i <= n; i++) {
            fibo[i] = fibo[i - 1] + fibo[i - 2];
        }
        return fibo[n];
    }
}
