package com.gioov.controller;

import com.gioov.common.Common;
import com.gioov.common.WindowManager;
import com.gioov.dao.ProjectDao;
import com.gioov.entity.ProjectEntity;
import com.gioov.util.ResourceUtil;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018/4/17 15:39
 */
public class AddProjectDialogController implements Initializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddProjectDialogController.class);

    private Stage dialogStage;

    @FXML
    public TextField nameTextField;
    @FXML
    public TextField deployPathTextField;
    @FXML
    public ComboBox<String> databaseTypeComboBox;
    @FXML
    public TextField databaseHostTextField;
    @FXML
    public TextField databasePortTextField;
    @FXML
    public TextField databaseNameTextField;
    @FXML
    public TextField databaseUsernameTextField;
    @FXML
    public TextField databasePasswordTextField;
    @FXML
    public TextField backupNameRuleTextField;
    @FXML
    public TextField backupPathTextField;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    private ObservableList<String> databaseTypeList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        for(Common.DatabaseType databaseType : Common.DatabaseType.values()){
            databaseTypeList.add(databaseType.value);
        }
        databaseTypeComboBox.setItems(databaseTypeList);
    }

    @FXML
    public void saveButtonOnMouseClickedAction(MouseEvent mouseEvent) {

        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setName(nameTextField.getText());
        projectEntity.setDeployPath(deployPathTextField.getText());
        projectEntity.setDatabaseType(Common.DatabaseType.valueOf(databaseTypeComboBox.getValue()));
        projectEntity.setDatabaseHost(databaseHostTextField.getText());
        projectEntity.setDatabasePort(databasePortTextField.getText());
        projectEntity.setDatabaseName(databaseNameTextField.getText());
        projectEntity.setDatabaseUsername(databaseUsernameTextField.getText());
        projectEntity.setDatabasePassword(databasePasswordTextField.getText());
        projectEntity.setBackupNameRule(backupNameRuleTextField.getText());
        projectEntity.setBackupPath(backupPathTextField.getText());
        ProjectDao.insertOne(projectEntity);

//        ObservableList<ProjectEntity> projectEntityObservableList = FXCollections.observableArrayList();
//        projectEntityObservableList.addAll(ProjectDao.listAll());
//        MainWindowController.setProjectEntityObservableList(projectEntityObservableList);

        MainWindowController.getProjectEntityObservableList().setAll(ProjectDao.listAll());
        dialogStage.close();
    }

    @FXML
    public void cancelButtonOnMouseClickedAction(MouseEvent mouseEvent) {
        dialogStage.close();
    }

}
