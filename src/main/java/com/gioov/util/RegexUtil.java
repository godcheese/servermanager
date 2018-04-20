package com.gioov.util;

import com.gioov.MainWindow;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import jdk.nashorn.internal.runtime.logging.DebugLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018/4/19 9:55
 */
public class RegexUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegexUtil.class);

    public static boolean isNumber(String str){
        String regex = "^[0-9]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }
}
