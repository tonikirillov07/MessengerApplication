package com.ds.messengerapplication.user;

import android.content.Context;

import com.ds.messengerapplication.Constants;
import com.ds.messengerapplication.user.database.databaseGetterAndSetter.DatabaseValuesGetter;
import com.ds.messengerapplication.util.interfaces.IOnActionWithParameter;

public class UserCurrentSettings extends Constants {
    public static void onSoundsEnabled(Context context, IOnActionWithParameter onActionWithParameter){
        DatabaseValuesGetter.findValue(UserController.getUserId(), USER_USE_SOUNDS_REFERENCE_PATH, context, result -> onActionWithParameter.onAction(Boolean.parseBoolean(result)));
    }
}
