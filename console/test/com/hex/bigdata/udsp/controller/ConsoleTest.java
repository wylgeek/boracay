package com.hex.bigdata.udsp.controller;

public class ConsoleTest {
    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }
}
