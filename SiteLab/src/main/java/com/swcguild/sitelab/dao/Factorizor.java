package com.swcguild.sitelab.dao;

import java.util.ArrayList;

/**
 *
 * @author Winona Wixson & Solomon Kim
 */
public class Factorizor {

    private int num;
    private ArrayList<Integer> factors = new ArrayList<>();
    private boolean perfect;
    private boolean prime;
    private int factorSum = 0;

    public Factorizor(int number) {
        num = number;
        for (int i = 1; i <= num; i++) { //find factors and gets factorSum
            if (num % i == 0) {
                factors.add(i);
                factorSum += i; // + factorSum;
            }
        }

        if (factorSum == 2 * num) { //determines perfection
            perfect = true;
        } else {
            perfect = false;
        }

        if (factors.size() == 2) { //determines prime/not prime
            prime = true;
        } else {
            prime = false;
        }
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public ArrayList<Integer> getFactors() { //don't want to be able to set the factors 
        return factors;
    }

    public boolean isPerfect() { //don't want to be able to set perfection
        return perfect;
    }

    public boolean isPrime() { //don't want to be able to set is prime
        return prime;
    }
}
