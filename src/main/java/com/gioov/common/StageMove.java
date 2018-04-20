package com.gioov.common;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018/4/17 10:39
 */

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * 拖拽监听器
 * @author Light
 */
public class StageMove implements EventHandler<MouseEvent> {

    private double xOffset = 0;
    private double yOffset = 0;
    private final Stage stage;

    public StageMove(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void handle(MouseEvent event) {
        event.consume();
        if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        } else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
            stage.setX(event.getScreenX() - xOffset);
            if(event.getScreenY() - yOffset < 0) {
                stage.setY(0);
            }else {
                stage.setY(event.getScreenY() - yOffset);
            }
        }
    }

    public void enableDrag(Node node) {
        node.setOnMousePressed(this);
        node.setOnMouseDragged(this);
    }
}