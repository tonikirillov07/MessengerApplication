package com.ds.messengerapplication.util;

public interface IOnAction {
    void onAction();
    default void onFailed(){Utils.failedInExecutingTask();}
}
