package com.ds.messengerapplication.activities.settings;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ds.messengerapplication.Constants;
import com.ds.messengerapplication.R;
import com.ds.messengerapplication.activities.initialSetup.InitialSetupSecondActivity;
import com.ds.messengerapplication.activities.services.RestorePasswordActivity;
import com.ds.messengerapplication.dialogs.ConfirmDialog;
import com.ds.messengerapplication.dialogs.ErrorDialog;
import com.ds.messengerapplication.user.UserController;
import com.ds.messengerapplication.util.AnotherActivity;
import com.ds.messengerapplication.util.ControlsBar;
import com.ds.messengerapplication.util.Utils;
import com.ds.messengerapplication.util.sounds.SoundPlayer;
import com.ds.messengerapplication.util.sounds.SoundsConstants;

import java.util.Objects;

import io.getstream.avatarview.AvatarView;

public class MainSettingsUserBlock extends AppCompatActivity {
    private TextView userMailInMailBlock, userMailTextView, dateOfRegTextView, switchStatus;
    private LinearLayout deleteAccountButton, logOutAccountButton;
    private AvatarView avatarView;
    private ImageButton backButton;
    private Button changeEmailButton, changePasswordButton;
    private boolean isOnline = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main_settings_user_block_page);

            userMailInMailBlock = findViewById(R.id.userEmail);
            switchStatus = findViewById(R.id.switchStatus);
            userMailTextView = findViewById(R.id.userNameTextView);
            dateOfRegTextView = findViewById(R.id.dateOfRegTextView);
            deleteAccountButton = findViewById(R.id.deleteAccountButton);
            logOutAccountButton = findViewById(R.id.logOutAccountButton);
            avatarView = findViewById(R.id.userAvatar);
            backButton = findViewById(R.id.backButton);
            changeEmailButton = findViewById(R.id.changeEmailButton);
            changePasswordButton = findViewById(R.id.changePasswordButton);

            initButtons();
            initUserData();
        }catch (Exception e){
            ErrorDialog.showDialog(this, e, true);
        }
    }

    private void initUserData() {
        Utils.displayUserDate(this, avatarView, dateOfRegTextView, userMailTextView);
        Utils.displayEmail(userMailInMailBlock);
    }

    private void initButtons() {
        try{
            deleteAccountButton.setOnClickListener(click -> Utils.onDefaultButtonClick(deleteAccountButton, () -> ConfirmDialog.showDialog(this, getResources().getString(R.string.account_deleting_confirm), () -> {
                try {
                    Objects.requireNonNull(UserController.getInstance().getFirebaseAuth().getCurrentUser()).delete();
                    UserController.getInstance().getFirebaseAuth().getCurrentUser().delete();
                    goToLogInActivity();
                }catch (Exception e){
                    ErrorDialog.showDialog(this, e, true);
                }
            }, android.R.drawable.ic_dialog_alert)));

            logOutAccountButton.setOnClickListener(click -> Utils.onDefaultButtonClick(logOutAccountButton, () -> {
                try {
                    UserController.logOut();
                    goToLogInActivity();
                }catch (Exception e){
                    ErrorDialog.showDialog(this, e, true);
                }
            }));

            backButton.setOnClickListener(click -> {
                SoundPlayer.create(this, SoundsConstants.CLICK_SOUND_PATH, true);

                AnotherActivity.gotoAnotherActivity(this, MainSettingsPage.class, false);
            });
            switchStatus.setOnClickListener(click -> switchStatus());
            changeEmailButton.setOnClickListener(click -> {
                SoundPlayer.create(this, SoundsConstants.CLICK_SOUND_PATH, true);
            });
            changePasswordButton.setOnClickListener(click -> {
                SoundPlayer.create(this, SoundsConstants.CLICK_SOUND_PATH, true);

                AnotherActivity.gotoAnotherActivity(this, RestorePasswordActivity.class, false);
            });

            ControlsBar.initActions(this, findViewById(R.id.mainButton), findViewById(R.id.messengerButton));
        }catch (Exception e){
            ErrorDialog.showDialog(this, e, true);
        }
    }

    private void goToLogInActivity(){
        openLogInActivity();
        finishAffinity();
    }

    private void switchStatus(){
        SoundPlayer.create(this, SoundsConstants.CLICK_SOUND_PATH, true);

        isOnline = !isOnline;

        avatarView.setIndicatorColor(isOnline ? Constants.ONLINE_STATUS_COLOR: Constants.OFFLINE_STATUS_COLOR);
        switchStatus.setText(!isOnline ? getResources().getString(R.string.set_online_status): getResources().getString(R.string.set_offline_status));
    }

    private void openLogInActivity(){
        AnotherActivity.gotoAnotherActivity(this, InitialSetupSecondActivity.class, true);
    }
}
