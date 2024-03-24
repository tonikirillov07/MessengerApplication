package com.ds.messengerapplication.activities.chat;

import static com.ds.messengerapplication.chatElement.ChatItem.createChatItem;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ds.messengerapplication.R;
import com.ds.messengerapplication.activities.settings.MainSettingsPage;
import com.ds.messengerapplication.util.AnotherActivity;
import com.ds.messengerapplication.util.Utils;

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
                    LinearLayout chatItem = createChatItem("Strange man " + finalI, "I'm waiting for you", this, () -> {
                        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                    });

                    scrollViewContent.addView(chatItem);
                });

                try {
                    Thread.sleep(150);
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


}