package com.example.kibb.xpera.damiz.util;

/**
 * Created by KIBB on 8/30/2017.
 */

public class FormatUtil {
    public static String subText(String title, int subLength){
        if (title.length() > subLength){
            return title.substring(0, subLength) + "...";
        }else {
            return title;
        }
    }
}
