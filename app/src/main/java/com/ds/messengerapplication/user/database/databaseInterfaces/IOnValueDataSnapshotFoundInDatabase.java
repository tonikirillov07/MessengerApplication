package com.ds.messengerapplication.user.database.databaseInterfaces;

import com.ds.messengerapplication.util.Utils;
import com.google.firebase.database.DataSnapshot;

public interface IOnValueDataSnapshotFoundInDatabase {
    void onValueFound(DataSnapshot dataSnapshot);
    default void onFailed(){Utils.failedInExecutingTask();}
}
