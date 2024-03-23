package com.ds.messengerapplication.util;

import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ds.messengerapplication.activities.chat.ChatsListPage;

public class ControlsBar {
    public static void initActions(AppCompatActivity activity, @NonNull ImageButton mainPageButton, ImageButton chatButton){
        mainPageButton.setOnClickListener(click -> Utils.onDefaultButtonClick(mainPageButton, () -> AnotherActivity.gotoAnotherActivity(activity, ChatsListPage.class, false)));
    }
}
