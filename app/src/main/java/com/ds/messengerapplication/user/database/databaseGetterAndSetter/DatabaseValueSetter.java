package com.ds.messengerapplication.user.database.databaseGetterAndSetter;

import android.content.Context;
import android.util.Log;

import com.ds.messengerapplication.user.database.Database;

public abstract class DatabaseValueSetter {
    public static void changeValue(String key, String newValue, String userId, Context context){
        Database.findDataSnapshotByKey(context, key, userId, dataSnapshot -> {
            dataSnapshot.getRef().setValue(newValue);

            Log.i(DatabaseValueSetter.class.getName(), "Key " + key + " changed to " + newValue);
        });
    }
}
