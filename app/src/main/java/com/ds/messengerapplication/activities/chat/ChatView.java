package com.ds.messengerapplication.activities.chat;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ds.messengerapplication.Constants;
import com.ds.messengerapplication.R;
import com.ds.messengerapplication.chatElement.ChatMessage;
import com.ds.messengerapplication.dialogs.ErrorDialog;
import com.ds.messengerapplication.util.settings.SettingsActivitiesUtils;
import com.ds.messengerapplication.util.sounds.SoundPlayer;
import com.ds.messengerapplication.util.sounds.SoundsConstants;

public class ChatView extends AppCompatActivity {
    private EditText messageEditText;
    private LinearLayout messagesLinearLayout;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_chat_view);

            TextView senderName = findViewById(R.id.userNameText);
            ImageButton sendButton = findViewById(R.id.sendButton);
            ImageButton getBackButton = findViewById(R.id.getBackImageButtonInChat);
            messagesLinearLayout = findViewById(R.id.messagesLinearLayout);
            messageEditText = findViewById(R.id.messageTextField);
            scrollView = findViewById(R.id.messagesScrollView);

            senderName.setText(getIntent().getStringExtra(Constants.SENDER_NAME_STORE_EXTRA_KEY));
            sendButton.setOnClickListener(click -> sendMessage());
            SettingsActivitiesUtils.addActionToBackButton(this, getBackButton, ChatsListPage.class);
        }catch (Exception e){
            ErrorDialog.showDialog(this, e, true);
        }
    }

    private void sendMessage(){
        try {
            if(!messageEditText.getText().toString().isEmpty()) {
                SoundPlayer.create(this, SoundsConstants.CLICK_SOUND_PATH, true);

                ChatMessage.create(messageEditText.getText().toString(), this, messagesLinearLayout);

                messageEditText.getText().clear();
                scrollView.smoothScrollTo(0, scrollView.getHeight() * 2);
            }else{
                messageEditText.setError(getString(R.string.fill_in_this_field));
            }
        }catch (Exception e){
            ErrorDialog.showDialog(this, e, true);
        }
    }
}