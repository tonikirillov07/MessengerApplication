package com.ds.messengerapplication.user.database.databaseInterfaces;

import com.ds.messengerapplication.util.Utils;

public interface IOnStringValueFoundInDatabase {
    void onValueFound(String result);
    default void onFailed(){Utils.failedInExecutingTask();}
}
