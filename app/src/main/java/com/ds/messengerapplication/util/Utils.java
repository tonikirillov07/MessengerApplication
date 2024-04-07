package com.ds.messengerapplication.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ds.messengerapplication.Constants;
import com.ds.messengerapplication.R;
import com.ds.messengerapplication.dialogs.ErrorDialog;
import com.ds.messengerapplication.user.UserController;
import com.ds.messengerapplication.user.database.databaseGetterAndSetter.DatabaseValuesGetter;
import com.ds.messengerapplication.util.interfaces.IOnAction;
import com.ds.messengerapplication.util.sounds.SoundPlayer;
import com.ds.messengerapplication.util.sounds.SoundsConstants;
import com.google.android.gms.tasks.Task;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import io.getstream.avatarview.AvatarView;

public abstract class Utils extends Constants {
    public static void clearEditText(@NonNull EditText editText){
        editText.getText().clear();
    }

    public static void addRotateAnimation(@NonNull View view, float fromAngle, float toAngle){
        try {
            RotateAnimation rotateAnimation = new RotateAnimation(fromAngle, toAngle, (float) view.getWidth() / 2, (float) view.getHeight() / 2);
            rotateAnimation.setRepeatMode(Animation.REVERSE);
            rotateAnimation.setDuration(200);
            view.startAnimation(rotateAnimation);

            Log.i(Utils.class.getName(), "Player rotate animation for " + view + ". From angle/To angle: " + fromAngle + "/" + toAngle);
        }catch (Exception e){
            ErrorDialog.showDialog(view.getContext(), e, true);
        }
    }

    public static void addTranslateAnimationByUpOrByDown(@NonNull View view, boolean byUp){
        try {
            TranslateAnimation translateAnimation = new TranslateAnimation(view.getTranslationX(), 0, 200 * (byUp ? -1: 1), view.getTranslationY());
            translateAnimation.setDuration(DEFAULT_ANIMATION_TIME_IN_MILLIS * 2);
            translateAnimation.setRepeatMode(Animation.REVERSE);
            view.startAnimation(translateAnimation);

            Log.i(Utils.class.getName(), "Player translate animation for " + view + ". By up: " + byUp);
        }catch (Exception e){
            ErrorDialog.showDialog(view.getContext(), e, true);
        }
    }

    public static void addAlphaAnimation(@NonNull View view, float fromAlpha, float toAlpha){
        try {
            AlphaAnimation alphaAnimation = new AlphaAnimation(fromAlpha, toAlpha);
            alphaAnimation.setRepeatMode(Animation.REVERSE);
            alphaAnimation.setDuration(DEFAULT_ANIMATION_TIME_IN_MILLIS);
            view.startAnimation(alphaAnimation);

            Log.i(Utils.class.getName(), "Player alpha animation for " + view + ". From/To: " + fromAlpha + "/" + toAlpha);
        }catch (Exception e){
            ErrorDialog.showDialog(view.getContext(), e, true);
        }
    }

   public static void onDefaultButtonClick(View view, @NonNull IOnAction onAction){
        try {
            SoundPlayer.create(view.getContext(), SoundsConstants.CLICK_SOUND_PATH, true);

            Utils.addAlphaAnimation(view, 0.5f, 1);

            onAction.onAction();
        }catch (Exception e){
            ErrorDialog.showDialog(view.getContext(), e, true);
            onAction.onFailed();
        }
    }

    public static void failedInExecutingTask(){
        ErrorDialog.makeErrorLog(new RuntimeException("Failed in executing task"));
    }

    public static @NotNull String getDate(){
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        return dateFormat.format(new Date());
    }

    @SuppressLint("SetTextI18n")
    public static void displayUserDate(Context context, AvatarView avatarView, TextView dateOfRegistration, @NonNull TextView userMailTextView){
        try{
            String userId = UserController.getUserId();

            DatabaseValuesGetter.findValue(userId, Constants.USER_AVATAR_COLOR_REFERENCE_PATH, context, result -> avatarView.setAvatarInitialsBackgroundColor(Integer.parseInt(result)));
            DatabaseValuesGetter.findValue(userId, Constants.USER_DATE_OF_REGISTRATION_REFERENCE_PATH, context, result -> dateOfRegistration.setText(context.getResources().getString(R.string.registered_at) + " " + result));

            displayEmail(userMailTextView);
        }catch (Exception e){
            ErrorDialog.showDialog(context, e, true);
        }
    }

    public static void displayEmail(@NonNull TextView userMailTextView) {
        String userMail = Objects.requireNonNull(UserController.getInstance().getFirebaseAuth().getCurrentUser()).getEmail();

        userMailTextView.setText(userMail);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            userMailTextView.setTooltipText(userMail);
        }
    }

    public static void checkIsTaskIsNotSuccessfulOrCanceled(@NonNull Task task, Context context){
        if(!task.isSuccessful() | task.isCanceled()){
            ErrorDialog.showDialog(context, Objects.requireNonNull(task.getException()), true);
        }
    }

    public static int convertDpToPx(int dpValue, @NonNull Context context){
        try {
            DisplayMetrics metrics = context.getResources().getDisplayMetrics();
            int dpi = metrics.densityDpi;

            return dpValue * (dpi / 160);
        }catch (Exception e){
            ErrorDialog.showDialog(context, e, true);
        }

        return 0;
    }

    @Nullable
    public static String getCurrentTime(){
        try {
            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.US);
            Date date = new Date();

            return dateFormat.format(date);
        }catch (Exception e){
            Log.e(Utils.class.getName(), e.toString());
        }

        return null;
    }
}
