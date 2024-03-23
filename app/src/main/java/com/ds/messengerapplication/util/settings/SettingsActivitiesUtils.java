package com.ds.messengerapplication.util.settings;

import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ds.messengerapplication.activities.settings.MainSettingsPage;
import com.ds.messengerapplication.util.AnotherActivity;
import com.ds.messengerapplication.util.Utils;

public abstract class SettingsActivitiesUtils {
    public static final Class<MainSettingsPage> MAIN_ACTIVITY_PAGE = MainSettingsPage.class;

    public static void addActionToBackButton(AppCompatActivity activity, @NonNull ImageButton imageButton, Class goToActivityClass){
        imageButton.setOnClickListener(v -> Utils.onDefaultButtonClick(imageButton, () -> AnotherActivity.gotoAnotherActivity(activity, goToActivityClass, false)));
    }
}
