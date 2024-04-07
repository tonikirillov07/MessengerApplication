package com.ds.messengerapplication.util.interfaces;

public interface IOnActionWithParameter {
    void onAction(Object value);

    default void onAction(boolean value) {}
}
