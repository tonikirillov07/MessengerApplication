package com.ds.messengerapplication.chatElement;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ds.messengerapplication.R;
import com.ds.messengerapplication.dialogs.ErrorDialog;
import com.ds.messengerapplication.util.Utils;

public class ChatMessage {
    @SuppressLint("UseCompatLoadingForDrawables")
    public static void create(String message, Context context, LinearLayout messagesLinearLayout){
        try {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View messageView = layoutInflater.inflate(R.layout.message_view, null);

            TextView messageTextView = messageView.findViewById(R.id.messageTextView);
            messageTextView.setText(message);

            TextView timeTextView = messageView.findViewById(R.id.timeTextView);
            timeTextView.setText(Utils.getCurrentTime());

            messagesLinearLayout.addView(messageView);

            Utils.addAlphaAnimation(messageView, 0.4f, 1);
        }catch (Exception e){
            ErrorDialog.showDialog(context, e, true);
        }
    }
}
