package com.ds.messengerapplication.user.database;

import static com.ds.messengerapplication.Constants.USER_ID_PREFIX;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.ds.messengerapplication.Constants;
import com.ds.messengerapplication.dialogs.ErrorDialog;
import com.ds.messengerapplication.user.User;
import com.ds.messengerapplication.user.UserAdditionalInfo;
import com.ds.messengerapplication.user.database.databaseInterfaces.IOnValueDataSnapshotFoundInDatabase;
import com.ds.messengerapplication.util.Utils;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Database {
    private static FirebaseDatabase database;
    private static DatabaseReference reference;
    private static Database instance;

    private Database(){
        createDatabase();
        createReference();
    }

    private static void createReference() {
        reference = getDatabase().getReference();
    }

    private static void createDatabase() {
        database = FirebaseDatabase.getInstance();
    }

    public static Database getInstance() {
        if(instance == null){
            instance = new Database();
        }

        return instance;
    }

    public static FirebaseDatabase getDatabase() {
        if(database == null) createDatabase();

        return database;
    }

    static DatabaseReference getDatabaseReference(){
        if(reference == null) createReference();

        return reference;
    }

    public static void writeNewUser(@NonNull User user, @NonNull UserAdditionalInfo userAdditionalInfo, Context context) {
        try{
            DatabaseReference reference = getDatabase().getReference().child(USER_ID_PREFIX + user.getUserId()).push();

            addRecord(reference, Constants.USER_EMAIL_REFERENCE_PATH, user.getMail(), context);
            addRecord(reference, Constants.USER_PASSWORD_REFERENCE_PATH, user.getPassword(), context);
            addRecord(reference, Constants.USER_AVATAR_COLOR_REFERENCE_PATH, userAdditionalInfo.getUserAvatarColor(), context);
            addRecord(reference, Constants.USER_SATURATION_REFERENCE_PATH, userAdditionalInfo.getSaturation(), context);
            addRecord(reference, Constants.USER_BRIGHTNESS_REFERENCE_PATH, userAdditionalInfo.getBrightness(), context);
            addRecord(reference, Constants.USER_CONTRAST_REFERENCE_PATH, userAdditionalInfo.getContrast(), context);
            addRecord(reference, Constants.USER_USE_SCROLL_BARS_REFERENCE_PATH, userAdditionalInfo.isUseScrollBars(), context);
            addRecord(reference, Constants.USER_USE_SOUNDS_REFERENCE_PATH, userAdditionalInfo.isUseSounds(), context);
            addRecord(reference, Constants.USER_THEME_REFERENCE_PATH, userAdditionalInfo.getTheme(), context);
            addRecord(reference, Constants.USER_DATE_OF_REGISTRATION_REFERENCE_PATH, userAdditionalInfo.getDateOfRegistration(), context);
        }catch (Exception e){
            ErrorDialog.showDialog(context, e, true);
        }
    }

    private static void addRecord(DatabaseReference databaseReference, String referencePath, Object value, Context context){
        try {
            databaseReference.child(referencePath).push().setValue(value.toString()).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    Log.i(Database.class.getName(), "Added record " + referencePath + " with value : " + value);
                }else{
                    Log.e(Database.class.getName(), "Failed to add record " + referencePath + " with value : " + value + ". Exception: " + task.getException());
                }
            });
        }catch (Exception e){
            ErrorDialog.showDialog(context, e, true);
        }
    }

    public static void findDataSnapshotByKey(Context context, String key, String userId, IOnValueDataSnapshotFoundInDatabase iOnValueDataSnapshotFoundInDatabase){
        try {
            Task<DataSnapshot> rootDataSnapshot = getDatabaseReference().child(USER_ID_PREFIX + userId).get();
            rootDataSnapshot.addOnCompleteListener(task -> {
                Utils.checkIsTaskIsNotSuccessfulOrCanceled(task, context);

                if (task.isSuccessful()) {
                    task.getResult().getChildren().forEach(dataSnapshot -> dataSnapshot.getChildren().forEach(dataSnapshot1 -> {
                        if (Objects.equals(dataSnapshot1.getKey(), key)) iOnValueDataSnapshotFoundInDatabase.onValueFound(dataSnapshot1); else iOnValueDataSnapshotFoundInDatabase.onFailed();
                    }));
                }
            });
        }catch (Exception e){
            ErrorDialog.showDialog(context, e, true);
            iOnValueDataSnapshotFoundInDatabase.onFailed();
        }
    }
}
