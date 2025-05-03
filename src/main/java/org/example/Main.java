package org.example;

public class Main {
    public static void main(String[] args) {


        System.out.println("hello, world!");


        Generika<Long> longGenerika = new Generika<>(10L);

        System.out.println(longGenerika.getData());





    }
}