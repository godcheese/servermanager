package com.gioov;

import com.gioov.common.WindowManager;

import com.gioov.util.FileUtil;
import com.gioov.util.ResourceUtil;
import javafx.application.Application;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;


/**
 * @author godcheese [godcheese@outlook.com]
 */
public class MainWindow extends Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainWindow.class);

    @Override
    public void start(Stage primaryStage){

        WindowManager.setPrimaryStage(primaryStage);
        WindowManager.mainWindow();
    }

    public static void main(String[] args) {
        launch(args);
    }

//    public static void connectDatabase(){
//        Properties properties = new Properties();
//        try {
//            properties.load(ResourceUtil.getResourceAsStream("config.properties"));
//            String datasourceDriver = properties.getProperty("datasource.driver");
//            String datasourceUrl = properties.getProperty("datasource.url");
//            Connection connection = null;
//            try {
//                try {
//                    Class.forName(datasourceDriver);
//
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                }
//                connection = DriverManager.getConnection(datasourceUrl);
//
//                Statement statement = connection.createStatement();
//                statement.executeUpdate("create table people (name, occupation);");
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    if(connection!=null) {
//                        connection.close();
//                    }
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//    }
}
