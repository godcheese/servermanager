package com.gioov.controller;

import com.gioov.common.GlobalMenu;
import com.gioov.common.StageMove;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018/4/16 14:58
 */
public class TitleBarController {

    private static final int DOUBLE_CLICK = 2;


    private Stage stage;

    @FXML
    public VBox rootVBox;

    @FXML
    public HBox titleBarHBox;

    @FXML
    public Label titleLabel;

    @FXML
    public Button menuButton;

    public void setContent(Parent content){
        this.rootVBox.getChildren().add(content);
        VBox.setVgrow(content, Priority.ALWAYS);
    }

    public void setStage(Stage stage, int height) {
        this.stage = stage;
//        bannerClickAction();
        titleBarHBox.setPrefHeight(height);
        new StageMove(this.stage).enableDrag(titleBarHBox);
    }

//    private void bannerClickAction() {
//        banner.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                if(event.getButton().equals(MouseButton.PRIMARY)){
//                    if(event.getClickCount() == DOUBLE_CLICK){
//                        maxButtonClickAction();
//                    }
//                }
//            }
//
//        });
//    }


    /**
     * 关闭按钮点击事件
     */
    @FXML
    public void closeButtonClickAction(){
        stage.close();
    }

//    /**
//     * 窗口最大化事件
//     */
//    @FXML
//    private void maxButtonClickAction() {
//        stage.setMaximized(!stage.isMaximized());
//    }


    /**
     * 窗口最小化事件
     */
    @FXML
    public void minButtonClickAction(){
        stage.setIconified(true);
    }


    /**
     * 窗口菜单按钮点击事件
     */
    @FXML
    public void menuButtonClickAction(){
        GlobalMenu.getInstance().show(menuButton, Side.BOTTOM, 0, 0);
    }

    public void setTitle(String title) {
        titleLabel.setText(title);
    }

}
