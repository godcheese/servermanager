package com.gioov.controller;

import com.gioov.MainWindow;
import com.gioov.common.Observable;
import com.gioov.common.WindowManager;
import com.gioov.common.tableview.MainWindowTableViewObserver;
import com.gioov.common.tableview.TableViewObservable;
import com.gioov.common.tableview.TableViewObserver;
import com.gioov.dao.ProjectDao;
import com.gioov.entity.ProjectEntity;
import com.gioov.util.*;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author godcheese [godcheese@outlook.com]
 */
public class MainWindowController implements Initializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainWindowController.class);

    private static ObservableList<ProjectEntity> projectEntityObservableList = FXCollections.observableArrayList();

    @FXML
    public Button addButton;

    @FXML
    public Button editButton;

    @FXML
    public Button deleteButton;

    @FXML
    public TableView<ProjectEntity> projectTableView;
    @FXML
    public TableColumn<ProjectEntity, String> idTableColumn;
    @FXML
    public TableColumn<ProjectEntity, String> nameTableColumn;
    @FXML
    public TableColumn<ProjectEntity, String> deployPathTableColumn;
    @FXML
    public TableColumn<ProjectEntity, String> databaseTypeTableColumn;
    public TableColumn<ProjectEntity, String> databaseHostTableColumn;
    @FXML
    public TableColumn<ProjectEntity, String> databasePortTableColumn;
    @FXML
    public TableColumn<ProjectEntity, String> databaseNameTableColumn;
    @FXML
    public TableColumn<ProjectEntity, String> databaseUsernameTableColumn;
    @FXML
    public TableColumn<ProjectEntity, String> databasePasswordTableColumn;
    @FXML
    public TableColumn<ProjectEntity, String> backupNameRuleTableColumn;

    @FXML
    public TextArea logTextArea;

    @FXML
    public TableColumn<ProjectEntity, String> backupPathTableColumn;

    public static ObservableList<ProjectEntity> getProjectEntityObservableList() {
        return projectEntityObservableList;
    }

    @Override
    public void initialize(final URL location, ResourceBundle resources) {

        logTextArea.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                logTextArea.setScrollTop(Double.MAX_VALUE);
            }
        });

        projectTableView.itemsProperty().addListener(new ChangeListener<ObservableList<ProjectEntity>>() {
            @Override
            public void changed(ObservableValue<? extends ObservableList<ProjectEntity>> observable, ObservableList<ProjectEntity> oldValue, ObservableList<ProjectEntity> newValue) {
                projectTableView.setItems(newValue);
            }
        });

        logTextArea.appendText("程序启动完毕...\n");
        logTextArea.appendText("开始加载项目表...\n");

//        String sourceFilename = ResourceUtil.getResource("servermanager.db").getFile();
//        File sourceFile = new File(sourceFilename);
//        String targetFilename = FileUtil.getCurrentRootPath()+ File.separator + "data" + File.separator + "servermanager.db";
//        File targetFile = new File(targetFilename);
//        if(!targetFile.exists()){
//            FileUtil.copyFile(sourceFile,targetFile);
//        }


        idTableColumn.setCellValueFactory(new PropertyValueFactory<ProjectEntity, String>("id"));
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<ProjectEntity, String>("name"));
        deployPathTableColumn.setCellValueFactory(new PropertyValueFactory<ProjectEntity, String>("deployPath"));
        databaseTypeTableColumn.setCellValueFactory(new PropertyValueFactory<ProjectEntity, String>("databaseType"));
        databaseHostTableColumn.setCellValueFactory(new PropertyValueFactory<ProjectEntity, String>("databaseHost"));
        databasePortTableColumn.setCellValueFactory(new PropertyValueFactory<ProjectEntity, String>("databasePort"));
        databaseNameTableColumn.setCellValueFactory(new PropertyValueFactory<ProjectEntity, String>("databaseName"));
        databaseUsernameTableColumn.setCellValueFactory(new PropertyValueFactory<ProjectEntity, String>("databaseUsername"));
        databasePasswordTableColumn.setCellValueFactory(new PropertyValueFactory<ProjectEntity, String>("databasePassword"));
        backupNameRuleTableColumn.setCellValueFactory(new PropertyValueFactory<ProjectEntity, String>("backupNameRule"));
        backupPathTableColumn.setCellValueFactory(new PropertyValueFactory<ProjectEntity, String>("backupPath"));

        initProjectTableView();
        logTextArea.appendText("项目表加载完毕...\n");

    }

    public void initProjectTableView(){

        projectEntityObservableList.addAll(ProjectDao.listAll());

        projectTableView.setItems(projectEntityObservableList);
    }

    @FXML
    public void addButtonOnMouseClickedAction(MouseEvent mouseEvent) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                WindowManager.addProjectDialog();
            }
        });
    }

    @FXML
    public void backupButtonOnMouseClickedAction(MouseEvent mouseEvent) {
        backupProject(projectTableView.getSelectionModel().getSelectedItem());
    }

    private void backupProject(ProjectEntity projectEntity) {

        String projectName = projectEntity.getName();
        logTextArea.appendText("开始备份项目["+ projectName +"]...\n");
        logTextArea.appendText("开始备份项目["+ projectName +"]应用数据...\n");
        String backupFilename = parseDateTime(projectEntity.getBackupNameRule());

        String zipName = parseCount(backupFilename + ".zip", projectEntity.getBackupPath());
        String sqlName = zipName.substring(0,zipName.lastIndexOf(".")) + ".sql";

        ZipUtil.compress(new File(projectEntity.getDeployPath()), projectEntity.getBackupPath()+ File.separator + zipName , null);

        switch (projectEntity.getDatabaseType()){
            case mysql:
                DatabaseUtil.mysqlExportDatabase(projectEntity.getDatabaseHost(), projectEntity.getDatabasePort(),projectEntity.getDatabaseName(), "utf8", projectEntity.getDatabaseUsername(), projectEntity.getDatabasePassword(), sqlName, projectEntity.getBackupPath());
                break;
            case oracle:
                break;
            default:
                break;
        }

        logTextArea.appendText("项目["+ projectName +"]应用数据备份完毕...\n");
        logTextArea.appendText("开始备份项目["+ projectName +"]数据库数据...\n");
        logTextArea.appendText("项目["+ projectName +"]数据库数据备份完毕...\n");
        logTextArea.appendText("项目["+ projectName +"]备份完毕...\n");

    }

    private String parseDateTime(String nameRule){
        List<String> timeList = new ArrayList();
        timeList.add("yyyyMMdd");
        timeList.add("YYYY");
        timeList.add("yyyy");
        timeList.add("MM");
        timeList.add("mm");
        timeList.add("DD");
        timeList.add("HH");
        timeList.add("hh");
        timeList.add("dd");
        timeList.add("ss");
        timeList.add("S");

        timeList.add("hhmmss");
        timeList.add("HHmmss");
        timeList.add("hhmmssS");
        timeList.add("HHmmssS");

        for (String time: timeList){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(time);
            String timeRegex = "\\$\\{" + time+ "\\}";
            nameRule = nameRule.replaceAll(timeRegex, simpleDateFormat.format(new Date()));
        }

        return nameRule;
    }

    private String parseCount(String nameRule, String targetPath){
        return nameRule.replaceAll("\\$\\{count\\}",String.valueOf(forEachIsExist(targetPath, nameRule)));
    }

    private int forEachIsExist(String targetPath, String name){

        List<Integer> integerList = new ArrayList<>();
//        String name = "test_20180419_${count}.zip";
        String regex = name.replaceAll("\\$\\{count\\}","([0-9]*)");
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;

        for(File file : new File(targetPath).listFiles()) {
            matcher = pattern.matcher(file.getName());
            if (matcher.find()) {
                if (matcher.groupCount() > 0) {
                    if (matcher.groupCount() > 1) {
                        String number = matcher.group(matcher.groupCount() - 1);
                        if (RegexUtil.isNumber(number)) {
                            integerList.add(Integer.valueOf(number));
                        }
                    } else {
                        String number = matcher.group(1);
                        if (RegexUtil.isNumber(number)) {
                            integerList.add(Integer.valueOf(number));
                        }
                    }
                }
            }

        }

        if(!integerList.isEmpty()) {
            Collections.sort(integerList, new Comparator<Integer>() {

                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2-o1;
                }
            });

            LOGGER.info("array{}",integerList);
            LOGGER.info("get0={}",integerList.get(0));
            return integerList.get(0) + 1;
        } else{
            return 1;
        }
    }

    @FXML
    public void deleteButtonOnMouseClickedAction(MouseEvent mouseEvent) {
        ProjectDao.deleteOne(projectTableView.getSelectionModel().getSelectedItem().getId());
        MainWindowController.getProjectEntityObservableList().setAll(ProjectDao.listAll());
    }

    @FXML
    public void editButtonOnMouseClickedAction(MouseEvent mouseEvent) {

        LOGGER.info("{}",ProjectDao.getOne(projectTableView.getSelectionModel().getSelectedItem().getId()));

        WindowManager.editProjectDialog(ProjectDao.getOne(projectTableView.getSelectionModel().getSelectedItem().getId()));
    }
}
