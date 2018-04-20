package com.gioov.common;

import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018/4/16 15:14
 */
public class GlobalMenu extends ContextMenu {

    private static GlobalMenu instance = null;

    /**
     * 私有构造函数
     */
    private GlobalMenu() {

        MenuItem settingMenuItem = new MenuItem("设置");
        MenuItem checkUpdateMenuItem = new MenuItem("检查更新");
        MenuItem feedbackMenuItem = new MenuItem("问题与建议");
        MenuItem aboutMenuItem = new MenuItem("关于");

        getItems().add(settingMenuItem);
        getItems().add(checkUpdateMenuItem);
        getItems().add(feedbackMenuItem);
        getItems().add(aboutMenuItem);
    }

    public static GlobalMenu getInstance(){
        if(instance==null){
            instance = new GlobalMenu();
        }
        return instance;
    }
}
