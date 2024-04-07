package com.ds.messengerapplication.util.interfaces;

import com.ds.messengerapplication.util.Utils;

public interface IOnAction {
    void onAction();
    default void onFailed(){
        Utils.failedInExecutingTask();}
}
