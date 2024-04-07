package com.ds.messengerapplication.util.sounds;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;

import com.ds.messengerapplication.dialogs.ErrorDialog;
import com.ds.messengerapplication.user.UserCurrentSettings;
import com.ds.messengerapplication.util.IOnActionWithParameter;

public abstract class SoundPlayer {
    private static MediaPlayer mediaPlayer;

    public static void create(Context context, String soundPath, boolean playAccordingToSettings) {
        if(playAccordingToSettings) {
            UserCurrentSettings.onSoundsEnabled(context, new IOnActionWithParameter() {
                @Override
                public void onAction(Object value) {
                }

                @Override
                public void onAction(boolean value) {
                    if (value)
                        createAndPlay(context, soundPath);
                    else
                        Log.e(getClass().getName(), "Sounds disabled by user");
                }
            });
        }else
            createAndPlay(context, soundPath);
    }

    private static void createAndPlay(Context context, String soundPath){
        try{
            mediaPlayer = new MediaPlayer();

            AssetFileDescriptor assetFileDescriptor = context.getAssets().openFd(soundPath);

            mediaPlayer.setDataSource(assetFileDescriptor);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

            play(context);
        }catch (Exception e){
            ErrorDialog.showDialog(context, e, true);
        }
    }

    private static void play(Context context){
        try {
            if (mediaPlayer == null) {
                ErrorDialog.showDialog(context, new NullPointerException("Media player was not created"), true);

                return;
            }

            mediaPlayer.prepare();
            mediaPlayer.start();
        }catch (Exception e){
            ErrorDialog.showDialog(context, e, true);
        }
    }
}
