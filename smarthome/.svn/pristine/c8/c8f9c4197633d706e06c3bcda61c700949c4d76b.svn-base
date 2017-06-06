package com.homecoo.smarthome.util.ym;

import java.util.Random;

public class RandomNumeric {
	 
    public static String random() {
        int count = 6;
        char start = '0';
        char end = '9';
 
        Random rnd = new Random();
 
        char[] result = new char[count];
        int len = end - start + 1;
 
        while (count-- > 0) {
            result[count] = (char) (rnd.nextInt(len) + start);
        }
 
        return new String(result);
    }
}
