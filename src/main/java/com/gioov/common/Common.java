package com.gioov.common;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018/4/16 11:48
 */
public class Common {


    public enum DatabaseType{

        mysql("mysql"),
        oracle("oracle"),

        ;

        public String value;

        DatabaseType(String value) {
            this.value = value;
        }
    }

}
