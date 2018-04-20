package com.gioov.common;


/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018/4/19 16:21
 */
public interface Observable<T extends Object> {
    void registerObserver(T o);
    void removeObserver(T o);
    void notifyObserver();
}

