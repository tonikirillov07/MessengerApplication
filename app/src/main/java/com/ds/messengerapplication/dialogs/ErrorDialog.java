package com.ds.messengerapplication.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

public final class ErrorDialog {
    public static void showDialog(Context context, @NonNull Exception exception, boolean printFullInfo){
        makeErrorLog(exception);

        try {
            AlertDialog.Builder alert = new AlertDialog.Builder(context);
            alert.setTitle("An exception has occurred");
            alert.setMessage(printFullInfo ? exception.toString() : exception.getMessage());

            AlertDialog dialog = alert.create();
            dialog.show();
        }catch (Exception e){
            makeErrorLog(e);
        }
    }

    private static void makeErrorLog(@NonNull Exception e){
        Log.e(ErrorDialog.class.getSimpleName(), e.toString());

        e.printStackTrace();
    }
}
