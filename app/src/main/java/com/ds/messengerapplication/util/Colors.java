package com.ds.messengerapplication.util;

import android.graphics.Color;

import java.util.Random;

public abstract class Colors {
    public static int getRandomColor(){
        Random random = new Random();

        int red = random.nextInt(255);
        int green = random.nextInt(255);
        int blue = random.nextInt(255);

        return Color.rgb(red, green, blue);
    }
}
