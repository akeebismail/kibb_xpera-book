package com.example.kibb.xpera.damiz.biz;

import com.example.kibb.xpera.damiz.config.Constants;

/**
 * Created by KIBB on 8/30/2017.
 */

public class BookBiz {

    public String switchKeyWord(){
        int index= (int) (Math.random() *8);
        return Constants.MATE_KEY_WORD[index];
    }
}
