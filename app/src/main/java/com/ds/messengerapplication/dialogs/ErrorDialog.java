package com.ds.messengerapplication.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.ds.messengerapplication.R;

public final class ErrorDialog {
    public static void showDialog(Context context, @NonNull Exception exception, boolean printFullInfo){
        makeErrorLog(exception);

        try {
            AlertDialog.Builder alert = new AlertDialog.Builder(context);
            alert.setTitle(context.getString(R.string.an_exception_has_occurred));
            alert.setMessage(printFullInfo ? exception.toString() : exception.getMessage());

            AlertDialog dialog = alert.create();
            dialog.show();
        }catch (Exception e){
            makeErrorLog(e);
        }
    }

    public static void makeErrorLog(@NonNull Exception e){
        Log.e(ErrorDialog.class.getSimpleName(), e.toString());

        e.printStackTrace();
    }
}
