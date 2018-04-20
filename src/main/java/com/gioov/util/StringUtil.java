package com.gioov.util;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018/4/17 17:12
 */
public class StringUtil {


    public static String isNullReturnEmpty(String str){
        if(str!=null){
            return str;
        }
        return "";
    }

}
