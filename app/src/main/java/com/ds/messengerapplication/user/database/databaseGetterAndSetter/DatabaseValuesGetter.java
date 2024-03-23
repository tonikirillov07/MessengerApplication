package com.ds.messengerapplication.user.database.databaseGetterAndSetter;

import android.content.Context;
import android.util.Log;

import com.ds.messengerapplication.dialogs.ErrorDialog;
import com.ds.messengerapplication.user.database.Database;
import com.ds.messengerapplication.user.database.databaseInterfaces.IOnStringValueFoundInDatabase;
import com.ds.messengerapplication.user.database.databaseInterfaces.IOnValueDataSnapshotFoundInDatabase;
import com.google.firebase.database.DataSnapshot;

import java.util.HashMap;

public abstract class DatabaseValuesGetter {
    public static void findValue(String userId, String key, Context context, IOnStringValueFoundInDatabase iOnStringValueFoundInDatabase){
        Database.findDataSnapshotByKey(context, key, userId, dataSnapshot -> {
            try {
                Object objectValue = dataSnapshot.getValue();

                makeActionWithValue(objectValue, iOnStringValueFoundInDatabase, context);
            }catch (Exception e){
                ErrorDialog.showDialog(context, e, true);
            }
        });
    }

    private static void makeActionWithValue(Object objectValue, IOnStringValueFoundInDatabase iOnStringValueFoundInDatabase, Context context) {
        try{
            if(objectValue instanceof HashMap<?,?>) {
                iOnStringValueFoundInDatabase.onValueFound((String) ((HashMap) objectValue).values().toArray()[0]);
            }

            if(objectValue instanceof String){
                iOnStringValueFoundInDatabase.onValueFound(objectValue.toString());
            }
        }catch (Exception e){
            ErrorDialog.showDialog(context, e, true);
        }
    }
}
