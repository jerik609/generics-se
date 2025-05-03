package org.example;

import java.util.ArrayList;
import java.util.List;

public class Invariance {

    public static void happy() {

        // =========================
        // ? extends = read only :-)
        // =========================

        List<Integer> integers = new ArrayList<>();
        List<? extends Integer> ofExtendsInteger = integers;

        // compiler does not know what exactly is in the list, so I'm prevented from
        // adding anything - since all must we one type and compiler would not be able to
        // guarantee that

        //ofExtendsInteger.add(Integer.parseInt("10")); // DOES NOT COMPILE

        // but I can read integers - only those are surely inside
        Integer readInteger = ofExtendsInteger.getFirst();

        // =========================
        // ? super = white only :-)
        // =========================

        List<Number> numbers = new ArrayList<>();
        List<? super Number> ofSuperNumbers = numbers;

        // compiler does not know how to cast the contents, really anything can be inside
        // not just Number
        // I can only get objects (which is a super of number)

        Object readNumber = ofSuperNumbers.getFirst(); // DOES NOT COMPILE

        // but I can put stuff in
        ofSuperNumbers.add(10);
        ofSuperNumbers.add(3.14f);
    }

    // https://docs.oracle.com/javase/tutorial/java/generics/wildcardGuidelines.html

}
