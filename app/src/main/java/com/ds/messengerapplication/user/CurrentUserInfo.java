package com.ds.messengerapplication.user;

import android.content.Context;

import com.ds.messengerapplication.user.database.databaseGetterAndSetter.DatabaseValuesGetter;
import com.ds.projecthelper.Constants;

import java.util.Objects;

public class CurrentUserInfo extends Constants {
    private static CurrentUserInfo instance;
    private final UserAdditionalInfo userAdditionalInfo;

    private CurrentUserInfo(){
        userAdditionalInfo = new UserAdditionalInfo();
    }

    private void setValues(Context context) {
        String userId = Objects.requireNonNull(UserController.getInstance().getFirebaseAuth().getCurrentUser()).getUid();

        DatabaseValuesGetter.findValue(userId, USER_SATURATION_REFERENCE_PATH, context, result -> userAdditionalInfo.setSaturation(Float.parseFloat(result)));
        DatabaseValuesGetter.findValue(userId, USER_CONTRAST_REFERENCE_PATH, context, result -> userAdditionalInfo.setContrast(Float.parseFloat(result)));
        DatabaseValuesGetter.findValue(userId, USER_BRIGHTNESS_REFERENCE_PATH, context, result -> userAdditionalInfo.setBrightness(Float.parseFloat(result)));
        DatabaseValuesGetter.findValue(userId, USER_USE_SCROLL_BARS_REFERENCE_PATH, context, result -> userAdditionalInfo.setUseScrollBars(Boolean.parseBoolean(result)));
        DatabaseValuesGetter.findValue(userId, USER_USE_SOUNDS_REFERENCE_PATH, context, result -> userAdditionalInfo.setUseSounds(Boolean.parseBoolean(result)));
        DatabaseValuesGetter.findValue(userId, USER_THEME_REFERENCE_PATH, context, userAdditionalInfo::setTheme);
        DatabaseValuesGetter.findValue(userId, USER_AVATAR_COLOR_REFERENCE_PATH, context, result -> userAdditionalInfo.setUserAvatarColor(Integer.parseInt(result)));
        DatabaseValuesGetter.findValue(userId, USER_DATE_OF_REGISTRATION_REFERENCE_PATH, context, userAdditionalInfo::setDateOfRegistration);
    }

    public static CurrentUserInfo getInstance() {
        if(instance == null){
            instance = new CurrentUserInfo();
        }

        return instance;
    }
}
