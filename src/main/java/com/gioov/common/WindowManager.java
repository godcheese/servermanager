package com.gioov.common;

import com.gioov.controller.AddProjectDialogController;
import com.gioov.controller.EditProjectDialogController;
import com.gioov.entity.ProjectEntity;
import com.gioov.util.ResourceUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Statement;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018/4/16 11:24
 */
public class WindowManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(WindowManager.class);

    private static final double PREF_HEIGHT = 700.0;
    private static final double PREF_WIDTH = 940.0;

    /**
     * title 高度
     */
    private final static int MAIN_TITLE_HEIGHT = 40;

    private static Stage primaryStage;

    public static void setPrimaryStage(Stage primaryStage) {
        WindowManager.primaryStage = primaryStage;
    }

    public static void mainWindow(){

        //设置窗口的图标.
//        primaryStage.getIcons().add(new Image(ResourceUtil.getResourceAsStream("img/login_left.png")));
        primaryStage.setTitle("Server Manager");

        try {
            Parent root = FXMLLoader.load(ResourceUtil.getFxml("window_main"));

            //隐藏标题栏
            primaryStage.initStyle(StageStyle.TRANSPARENT);

//            primaryStage.setScene(new Scene(root, 640, 400));
            primaryStage.setResizable(false);
            StageDecorate.setTitleBar(primaryStage, root,"Server Manager v1.0.0",null, MAIN_TITLE_HEIGHT);
            setStageOnCenter(primaryStage, PREF_WIDTH, PREF_HEIGHT);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void addProjectDialog(){
        //设置窗口的图标.
//        primaryStage.getIcons().add(new Image(ResourceUtil.getResourceAsStream("img/login_left.png")));
        try {

            FXMLLoader fxmlLoader = new FXMLLoader();

            fxmlLoader.setLocation(ResourceUtil.getFxml("dialog_add_project"));
            fxmlLoader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("新增项目");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(getPrimaryStage());
            Scene scene = new Scene((Parent) fxmlLoader.getRoot());
            dialogStage.setScene(scene);
            dialogStage.setResizable(false);

            AddProjectDialogController addProjectDialogController = fxmlLoader.getController();
            addProjectDialogController.setDialogStage(dialogStage);
//            setStageOnCenter(dialogStage);
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void editProjectDialog(ProjectEntity projectEntity){

        LOGGER.info("edit={}",projectEntity);
        //设置窗口的图标.
//        primaryStage.getIcons().add(new Image(ResourceUtil.getResourceAsStream("img/login_left.png")));
        try {

            FXMLLoader fxmlLoader = new FXMLLoader();

            fxmlLoader.setLocation(ResourceUtil.getFxml("dialog_edit_project"));
            fxmlLoader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("编辑项目");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(getPrimaryStage());
            Scene scene = new Scene((Parent) fxmlLoader.getRoot());
            dialogStage.setScene(scene);
            dialogStage.setResizable(false);

            EditProjectDialogController editProjectDialogController = fxmlLoader.getController();
            editProjectDialogController.setDialogStage(dialogStage);
            editProjectDialogController.setProjectEntity(projectEntity);
//            setStageOnCenter(dialogStage);
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void setStageOnCenter(Stage stage, double prefWidth, double prefHeight){
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primaryScreenBounds.getWidth() - prefWidth) / 2);
        stage.setY((primaryScreenBounds.getHeight() - prefHeight) / 2);
    }

}
