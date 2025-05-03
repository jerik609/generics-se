package org.example;

public class Covariance {

    public static void boom() {

        Integer[] integers = new Integer[] {1, 2, 3};
        Number[] numbers = integers;

        //numbers[0] = 3.14; // EXCEPTION!

    }

}
