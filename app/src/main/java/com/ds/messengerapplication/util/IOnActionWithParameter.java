package com.ds.messengerapplication.util;

public interface IOnActionWithParameter {
    void onAction(Object value);

    default void onAction(boolean value) {}
}
