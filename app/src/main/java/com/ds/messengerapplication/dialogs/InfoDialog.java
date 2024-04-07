package com.ds.messengerapplication.dialogs;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

import com.ds.messengerapplication.R;

public class InfoDialog {
    public static void showDialog(Context context, String message, int iconId){
        try {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
            alertDialog.setTitle(context.getString(R.string.info));
            alertDialog.setMessage(message);
            alertDialog.setIcon(iconId);
            alertDialog.show();
        }catch (Exception e){
            ErrorDialog.showDialog(context, e, true);
        }
    }
}
