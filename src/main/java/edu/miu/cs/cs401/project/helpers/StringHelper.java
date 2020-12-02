package edu.miu.cs.cs401.project.helpers;

import java.security.SecureRandom;

public class StringHelper {
    public static String getRandomAlphaString(int len) {
        String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(len);
        for(int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }
    public static String getRandomCityName() {
    	final String[] cities = {
    			"Fairfield",
    			"Chicago",
    			"New York City",
    			"Los Angeles",
    			"Tampa",
    			"Miami",
    			"Seattle",
    			"Sunnyvale"
    	};
    	return cities[(int)(Math.random() * cities.length)];
    }
}
