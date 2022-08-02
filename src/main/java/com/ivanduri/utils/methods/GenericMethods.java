package com.ivanduri.utils.methods;

import java.util.Random;

public class GenericMethods {

    public static int getRandomNumber(int max, int min){
        Random rand = new Random();
        return rand.nextInt((max - min) + min) + min;
    }
}
