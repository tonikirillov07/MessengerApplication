package com.ds.messengerapplication.chatElement;

import static android.view.Gravity.CENTER_VERTICAL;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Icon;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.ds.messengerapplication.R;
import com.ds.messengerapplication.dialogs.ErrorDialog;
import com.ds.messengerapplication.util.interfaces.IOnActionWithParameter;
import com.ds.messengerapplication.util.Utils;
import com.ds.messengerapplication.util.sounds.SoundPlayer;
import com.ds.messengerapplication.util.sounds.SoundsConstants;

import io.getstream.avatarview.AvatarShape;
import io.getstream.avatarview.AvatarView;
import io.getstream.avatarview.IndicatorPosition;

public abstract class ChatItem {
    @Nullable
    public static LinearLayout createChatItem(String name, String message, Context context, IOnActionWithParameter onAction){
        try {
            LinearLayout itemLayout = new LinearLayout(context);
            itemLayout.setOrientation(LinearLayout.HORIZONTAL);
            itemLayout.setElevation(15f);
            itemLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.chat_item_background));
            itemLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, Utils.convertDpToPx(84, context)){{
                setMargins(Utils.convertDpToPx(20, context), Utils.convertDpToPx(15, context), Utils.convertDpToPx(20, context), 0);
            }});
            itemLayout.setOnClickListener(click -> {
                SoundPlayer.create(context, SoundsConstants.CLICK_SOUND_PATH, true);
                Utils.addAlphaAnimation(itemLayout, 0.5f, 1f);

                onAction.onAction(name);
            });

            createAvatarView(itemLayout, context);

            LinearLayout nameAndLastMessageLayout = createNameAndLastMessageLinearLayout(itemLayout, context);

            assert nameAndLastMessageLayout != null;
            createNameTextView(name, context, nameAndLastMessageLayout);
            createLastMessageTextView(message, context, nameAndLastMessageLayout);

            Utils.addTranslateAnimationByUpOrByDown(itemLayout, true);

            return itemLayout;
        }catch (Exception e){
            ErrorDialog.showDialog(context, e, true);
        }

        return null;
    }

    @Nullable
    private static LinearLayout createNameAndLastMessageLinearLayout(LinearLayout itemLayout, Context context) {
        try {
            LinearLayout nameAndMessageLayout = new LinearLayout(context);
            nameAndMessageLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            nameAndMessageLayout.setOrientation(LinearLayout.VERTICAL);
            itemLayout.addView(nameAndMessageLayout);

            return nameAndMessageLayout;
        }catch (Exception e){
            ErrorDialog.showDialog(context, e, true);
        }

        return null;
    }

    private static void createLastMessageTextView(String message, Context context, @NonNull LinearLayout nameAndMessageLayout) {
        try {
            TextView lastMessageTextView = createChatItemTextView(message, 14, context);
            assert lastMessageTextView != null;
            lastMessageTextView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT) {{
                setMargins(Utils.convertDpToPx(15, context), Utils.convertDpToPx(10, context), 0, 0);
            }});
            nameAndMessageLayout.addView(lastMessageTextView);
        }catch (Exception e){
            ErrorDialog.showDialog(context, e, true);
        }
    }

    private static void createNameTextView(String name, Context context, @NonNull LinearLayout nameAndMessageLayout) {
        try {
            TextView nameTextView = createChatItemTextView(name, 16, context);
            assert nameTextView != null;

            nameTextView.getPaint().setUnderlineText(true);
            nameTextView.setGravity(CENTER_VERTICAL);
            nameTextView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT) {{
                setMargins(Utils.convertDpToPx(15, context), Utils.convertDpToPx(10, context), 0, 0);
            }});
            nameAndMessageLayout.addView(nameTextView);
        }catch (Exception e){
            ErrorDialog.showDialog(context, e, true);
        }
    }

    private static void createAvatarView(@NonNull LinearLayout itemLayout, Context context) {
        try {
            AvatarView avatarView = new AvatarView(context);
            avatarView.setLayoutParams(new LinearLayout.LayoutParams(Utils.convertDpToPx(62, context), Utils.convertDpToPx(55, context)) {{
                setMargins(Utils.convertDpToPx(20, context), 0, 0, 0);
                gravity = CENTER_VERTICAL;
            }});
            avatarView.setAvatarShape(AvatarShape.CIRCLE);
            avatarView.setIndicatorEnabled(true);
            avatarView.setIndicatorPosition(IndicatorPosition.BOTTOM_RIGHT);
            avatarView.setImageIcon(Icon.createWithResource(context, R.drawable.avatar));
            avatarView.setScaleType(ImageView.ScaleType.FIT_XY);
            itemLayout.addView(avatarView);
        }catch (Exception e){
            ErrorDialog.showDialog(context, e, true);
        }
    }

    @Nullable
    private static TextView createChatItemTextView(String text, float size, Context context){
        try {
            TextView textView = new TextView(context);
            textView.setText(text);
            textView.setTextColor(ContextCompat.getColor(context, R.color.white));
            textView.setTypeface(Typeface.create("Montserrat", Typeface.BOLD));
            textView.setTextSize(size);

            return textView;
        }catch (Exception e){
            ErrorDialog.showDialog(context, e, true);
        }

        return null;
    }
}
