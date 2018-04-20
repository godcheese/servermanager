package com.gioov.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018/4/16 20:32
 */
public class ConfigUtil {

    private static ConfigUtil instance = null;

    public static ConfigUtil getInstance() {
        if(instance==null){
            instance = new ConfigUtil();
        }
        return instance;
    }

    private Properties properties;

    private ConfigUtil(){
        getProperties();
    }

    private void getProperties(){
        Properties properties = new Properties();
        InputStream inputStream = ResourceUtil.getResourceAsStream("config.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.properties = properties;
    }

    public String getProperty(String key){
        return properties.getProperty(key);
    }

    public void setProperty(String key,String value){
        properties.setProperty(key, value);
    }
}
