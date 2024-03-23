package com.ds.messengerapplication.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ds.projecthelper.Constants;
import com.ds.projecthelper.R;
import com.ds.projecthelper.dialogs.ErrorDialog;
import com.ds.projecthelper.user.UserController;
import com.ds.projecthelper.user.database.databaseGetterAndSetter.DatabaseValuesGetter;
import com.google.android.gms.tasks.Task;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

import io.getstream.avatarview.AvatarView;

public abstract class Utils extends Constants {
    @Nullable
    public static byte[] createRandomPassword(){
        String characters = "1234567890QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklmnbvcxz=-/()|";
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < DEFAULT_PASSWORD_LENGTH; i++) {
            stringBuilder.append(characters.toCharArray()[new Random().nextInt(characters.length())]);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return Base64.getEncoder().encode(stringBuilder.toString().getBytes());
        }

        return null;
    }

    public static void clearEditText(@NonNull EditText editText){
        editText.getText().clear();
    }

    public static void addRotateAnimation(@NonNull View view, float fromAngle, float toAngle){
        try {
            RotateAnimation rotateAnimation = new RotateAnimation(fromAngle, toAngle, (float) view.getWidth() / 2, (float) view.getHeight() / 2);
            rotateAnimation.setRepeatMode(Animation.REVERSE);
            rotateAnimation.setDuration(200);
            view.startAnimation(rotateAnimation);
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
        }catch (Exception e){
            ErrorDialog.showDialog(view.getContext(), e, true);
        }
    }

   public static void onSettingButtonClick(View view, @NonNull IOnAction onAction){
        Utils.addAlphaAnimation(view, 0.5f,1);

        onAction.onAction();
    }

    public static @NotNull String getDate(){
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        return dateFormat.format(new Date());
    }

    @SuppressLint("SetTextI18n")
    public static void displayUserDate(Context context, AvatarView avatarView, TextView dateOfRegistration, @NonNull TextView userMailTextView){
        try{
            String userId = UserController.getUserId();

            DatabaseValuesGetter.findValue(userId, Constants.USER_AVATAR_COLOR_REFERENCE_PATH, context, result ->  avatarView.setAvatarInitialsBackgroundColor(Integer.parseInt(result)));
            DatabaseValuesGetter.findValue(userId, Constants.USER_DATE_OF_REGISTRATION_REFERENCE_PATH, context, result -> dateOfRegistration.setText(context.getResources().getString(R.string.registered_at) + " " + result));

            displayEmail(userMailTextView);
        }catch (Exception e){
            ErrorDialog.showDialog(context, e, true);
        }
    }

    public static void displayEmail(@NonNull TextView userMailTextView) {
        userMailTextView.setText(Objects.requireNonNull(UserController.getInstance().getFirebaseAuth().getCurrentUser()).getEmail());
    }

    public static void checkIsTaskIsNotSuccessfulOrCanceled(@NonNull Task task, Context context){
        if(!task.isSuccessful() | task.isCanceled()){
            ErrorDialog.showDialog(context, Objects.requireNonNull(task.getException()), true);
        }
    }
}
