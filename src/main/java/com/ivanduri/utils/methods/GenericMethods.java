package com.ivanduri.utils.methods;

import java.util.Random;
import java.util.UUID;

public class GenericMethods {

    public static int getRandomNumber(int max, int min){
        Random rand = new Random();
        return rand.nextInt((max - min) + min) + min;
    }

    public static String getRandomEmail(){
        UUID uuid = UUID.randomUUID();
        return (uuid + "@gmail.com");
    }
}
