package com.ds.messengerapplication.user.database.databaseInterfaces;

import com.google.firebase.database.DataSnapshot;

public interface IOnValueDataSnapshotFoundInDatabase {
    void onValueFound(DataSnapshot dataSnapshot);
}
