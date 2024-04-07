package com.ds.messengerapplication.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class PermissionsUtils {
    private static ActivityResultLauncher<String[]> activityResultLauncher;

    public static void registerPermission(@NonNull AppCompatActivity activity){
        activityResultLauncher = activity.registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> result.forEach((permission, granted) -> {
            if(granted){
                Toast.makeText(activity, "Permission granted", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(activity, "Permission " + permission + " NOT granted", Toast.LENGTH_LONG).show();
            }
        }));
    }

    public static void checkPermission(Context context, String permission, IOnAction onAction){
        if(ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED){
            onAction.onAction();
        }else{
            activityResultLauncher.launch(new String[]{permission});

            onAction.onFailed();
        }
    }
}
