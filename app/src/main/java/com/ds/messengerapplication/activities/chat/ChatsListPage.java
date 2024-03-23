package com.ds.messengerapplication.activities.chat;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.ds.messengerapplication.R;
import com.ds.messengerapplication.activities.settings.MainSettingsPage;
import com.ds.messengerapplication.util.AnotherActivity;
import com.ds.messengerapplication.util.Utils;

public class ChatsListPage extends AppCompatActivity {
    private ImageButton settingsButton, addChatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats_list_page);

        settingsButton = findViewById(R.id.chatSettingsButton);
        addChatButton = findViewById(R.id.addChatButton);

        initButtons();
    }

    private void initButtons() {
        settingsButton.setOnClickListener(click -> Utils.onDefaultButtonClick(settingsButton, () -> {
            Utils.addRotateAnimation(settingsButton, 0, 360);

            AnotherActivity.gotoAnotherActivity(this, MainSettingsPage.class, false);
        }));

        addChatButton.setOnClickListener(click -> Utils.onDefaultButtonClick(addChatButton, () -> {
            Utils.addRotateAnimation(addChatButton, 0, 360);
        }));
    }
}