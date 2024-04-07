package com.ds.messengerapplication.activities.chat;

import static com.ds.messengerapplication.chatElement.ChatItem.createChatItem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.ds.messengerapplication.Constants;
import com.ds.messengerapplication.R;
import com.ds.messengerapplication.activities.settings.MainSettingsPage;
import com.ds.messengerapplication.dialogs.InfoDialog;
import com.ds.messengerapplication.user.UserController;
import com.ds.messengerapplication.util.AnotherActivity;
import com.ds.messengerapplication.util.Utils;

import java.util.Objects;

public class ChatsListPage extends AppCompatActivity {
    private ImageButton settingsButton, addChatButton;
    private LinearLayout scrollViewContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats_list_page);

        settingsButton = findViewById(R.id.chatSettingsButton);
        addChatButton = findViewById(R.id.addChatButton);
        scrollViewContent = findViewById(R.id.scrollViewContent);

        initButtons();
        initChatsList();
    }

    private void initChatsList() {
        new Thread(() -> {
            for (int i = 1; i < 21; i++) {
                int finalI = i;
                runOnUiThread(() -> {
                    LinearLayout chatItem = createChatItem("Strange man " + finalI, "I'm waiting for you", this, result -> {
                        Intent intent = new Intent(this, ChatView.class);
                        intent.putExtra(Constants.SENDER_NAME_STORE_EXTRA_KEY, (String) result);
                        startActivity(intent);
                        finish();
                    });

                    scrollViewContent.addView(chatItem);
                });

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

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

    @Override
    protected void onStart() {
        super.onStart();

        Utils.addTranslateAnimationByUpOrByDown(findViewById(R.id.headerLinearLayout), true);
    }
}