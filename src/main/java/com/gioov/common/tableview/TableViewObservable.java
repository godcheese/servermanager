package com.gioov.common.tableview;

import com.gioov.common.Observable;
import javafx.collections.ObservableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018/4/19 16:25
 */
public class TableViewObservable implements Observable<TableViewObserver> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TableViewObservable.class);

    private List<TableViewObserver> observerList;
    private ObservableList<? extends Object> objectObservableList;

    public TableViewObservable(){
        observerList = new ArrayList<>();
    }

    @Override
    public void registerObserver(TableViewObserver o) {
        observerList.add(o);
        LOGGER.info("注册observer");
    }

    @Override
    public void removeObserver(TableViewObserver o) {
        observerList.remove(o);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void notifyObserver() {
        for (TableViewObserver observer : observerList){
            observer.update(objectObservableList);
            LOGGER.info("已通知observer");
        }
    }

    public void setUpdate(ObservableList<? extends Object> objectObservableList){
        this.objectObservableList = objectObservableList;
        notifyObserver();
    }
}
