package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {


        System.out.println("hello, world!");


        Generika<Long> longGenerika = new Generika<>(10L);

        System.out.println(longGenerika.getData());


        WeirdosSon son = new WeirdosSon();

        WeirdosSon2 son2 = new WeirdosSon2();

        Covariance.boom();


        // ====================================
        System.out.println("generics testing!!!");

        Generika<String> generika = new Generika<>("hello");


        generika.putItems(List.of("a", "b", "c"));
        generika.getItems().forEach(System.out::println);






    }
}