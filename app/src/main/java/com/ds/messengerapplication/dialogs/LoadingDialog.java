package com.ds.messengerapplication.dialogs;

import android.app.ProgressDialog;
import android.content.Context;

public class LoadingDialog extends ProgressDialog {

    public LoadingDialog(Context context, String title) {
        super(context);

        setTitle(title);
        setCancelable(false);
    }
}
