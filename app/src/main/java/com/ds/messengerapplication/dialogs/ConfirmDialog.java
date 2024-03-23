package com.ds.messengerapplication.dialogs;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

import com.ds.messengerapplication.util.IOnAction;

public final class ConfirmDialog {
    public static void showDialog(Context context, String message, IOnAction onPositiveAction, int iconId){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setMessage(message);
        alertDialog.setIcon(iconId);
        alertDialog.setPositiveButton(android.R.string.yes, (dialog, whichButton) -> onPositiveAction.onAction());
        alertDialog.setNegativeButton(android.R.string.no, null);
        alertDialog.show();
    }
}
