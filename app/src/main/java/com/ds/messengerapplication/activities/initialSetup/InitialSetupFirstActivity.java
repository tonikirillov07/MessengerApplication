package com.ds.messengerapplication.activities.initialSetup;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ds.messengerapplication.R;
import com.ds.messengerapplication.activities.chat.ChatsListPage;
import com.ds.messengerapplication.dialogs.ErrorDialog;
import com.ds.messengerapplication.user.UserController;
import com.ds.messengerapplication.util.AnotherActivity;
import com.ds.messengerapplication.util.Utils;
import com.ds.messengerapplication.util.sounds.SoundPlayer;
import com.ds.messengerapplication.util.sounds.SoundsConstants;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;


public class InitialSetupFirstActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_initial_setup_first);

            Button buttonNext = findViewById(R.id.buttonNext);
            TextView developerName = findViewById(R.id.developerName);

            developerName.setText(getString(R.string.app_developer_name) + " (2023-24)");

            buttonNext.setOnClickListener(v -> {
                SoundPlayer.create(this, SoundsConstants.CLICK_SOUND_PATH, false);

                AnotherActivity.gotoAnotherActivity(this, InitialSetupSecondActivity.class, true);
            });
        }catch (Exception e){
            ErrorDialog.showDialog(this, e, true);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        checkAuthorization();
    }

    private void checkAuthorization(){
        if(UserController.isSignedIn()) {
            AnotherActivity.gotoAnotherActivity(this, ChatsListPage.class, true);
        }
    }

}