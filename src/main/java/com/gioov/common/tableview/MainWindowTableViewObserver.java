package com.gioov.common.tableview;

import com.gioov.entity.ProjectEntity;
import javafx.collections.ObservableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018/4/19 16:33
 */
public class MainWindowTableViewObserver implements TableViewObserver<ProjectEntity> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainWindowTableViewObserver.class);
    private ObservableList<ProjectEntity> projectEntityObservableList;

    @Override
    public void update(ObservableList<ProjectEntity> observableList) {
        this.projectEntityObservableList = observableList;
        LOGGER.info("更新数据");
    }

    public ObservableList<ProjectEntity> getUpdate() {
        return projectEntityObservableList;
    }

}
