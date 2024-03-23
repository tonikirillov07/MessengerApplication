package com.ds.messengerapplication.util;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.ds.projecthelper.dialogs.ErrorDialog;

public abstract class AnotherActivity {
    public static void gotoAnotherActivity(AppCompatActivity appCompatActivity, Class activityClass, boolean destroyPreviousActivity){
        try {
            Intent intent = new Intent(appCompatActivity, activityClass);
            if(destroyPreviousActivity) intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            appCompatActivity.startActivity(intent);
            appCompatActivity.finish();
        }catch (Exception e){
            ErrorDialog.showDialog(appCompatActivity, e, true);
        }
    }
}
