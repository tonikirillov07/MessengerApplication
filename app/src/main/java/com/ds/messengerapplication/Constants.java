package com.ds.messengerapplication;

import android.graphics.Color;

public abstract class Constants {
    public static final int MIN_PERSONAL_CODE_LENGTH = 5;
    public static final int MIN_EMAIL_LENGTH = 3;
    public static final int MIN_LOGIN_LENGTH = 4;
    public static final int MIN_PASSWORD_LENGTH = 8;
    public static final int DEFAULT_PASSWORD_LENGTH = 6;
    public static final int ONLINE_STATUS_COLOR = Color.GREEN;
    public static final int OFFLINE_STATUS_COLOR = Color.LTGRAY;
    public static final String EXCEPTION_KEY = "exception";
    public static final String WHITE_COLOR_HEX = "#FFFFFF";
    public static final long DEFAULT_ANIMATION_TIME_IN_MILLIS = 200;
    public static final String USER_ID_PREFIX = "user_";
    public static final String USER_EMAIL_REFERENCE_PATH = "user_email";
    public static final String USER_PASSWORD_REFERENCE_PATH = "user_password";
    public static final String USER_AVATAR_COLOR_REFERENCE_PATH = "user_avatar_color";
    public static final String USER_CONTRAST_REFERENCE_PATH = "user_contrast";
    public static final String USER_BRIGHTNESS_REFERENCE_PATH = "user_brightness";
    public static final String USER_SATURATION_REFERENCE_PATH = "user_saturation";
    public static final String USER_THEME_REFERENCE_PATH = "user_theme";
    public static final String USER_USE_SOUNDS_REFERENCE_PATH = "user_use_sounds";
    public static final String USER_USING_PLS_REFERENCE_PATH = "user_using_pls";
    public static final String USER_USE_SCROLL_BARS_REFERENCE_PATH = "user_use_scroll_bars";
    public static final String USER_DATE_OF_REGISTRATION_REFERENCE_PATH = "user_date_of_registration";
    public static final String TEMP_PASSWORD_STORE_EXTRA_KEY = "password";
}
