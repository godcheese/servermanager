package com.gioov.common.tableview;

import javafx.collections.ObservableList;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018/4/19 16:23
 */
public interface TableViewObserver<T extends Object> {
    void update(ObservableList<T> observableList);
}
