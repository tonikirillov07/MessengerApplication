package com.ds.messengerapplication.activities.settings;

import static com.ds.messengerapplication.util.Utils.failedInExecutingTask;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ds.messengerapplication.R;
import com.ds.messengerapplication.dialogs.ErrorDialog;
import com.ds.messengerapplication.user.database.Database;
import com.ds.messengerapplication.util.AnotherActivity;
import com.ds.messengerapplication.util.ControlsBar;
import com.ds.messengerapplication.util.IOnAction;
import com.ds.messengerapplication.util.Utils;

import io.getstream.avatarview.AvatarView;

public class MainSettingsPage extends AppCompatActivity {
    private EditText searchView;
    private TextView userMailText, dateOfRegistrationText, goToUserSettings;
    private AvatarView avatarView;
    private LinearLayout sendFeedbackButton, resetSettingsButton, appearanceButton, aboutButton, moreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main_settings_page);

            searchView = findViewById(R.id.searchField);
            userMailText = findViewById(R.id.userName);
            dateOfRegistrationText = findViewById(R.id.dateOfRegistration);
            avatarView = findViewById(R.id.avatarView);
            sendFeedbackButton = findViewById(R.id.sendFeedBackButton);
            resetSettingsButton = findViewById(R.id.resetSettingsButton);
            appearanceButton = findViewById(R.id.appearanceButton);
            aboutButton = findViewById(R.id.aboutButton);
            moreButton = findViewById(R.id.moreButton);
            goToUserSettings = findViewById(R.id.gotoUserSettings);

            initButtons(this);
            initSearch(getResources().getStringArray(R.array.search_requests));
            loadUserData();
        }catch (Exception e){
            ErrorDialog.showDialog(this, e, true);
        }
    }

    private void initButtons(AppCompatActivity activity) {
        try{
            sendFeedbackButton.setOnClickListener(click -> Utils.onDefaultButtonClick(sendFeedbackButton, () -> AnotherActivity.gotoAnotherActivity(activity, FeedbacksBlock.class, false)));

            resetSettingsButton.setOnClickListener(click -> Utils.onDefaultButtonClick(resetSettingsButton, () -> {

            }));
            appearanceButton.setOnClickListener(click -> Utils.onDefaultButtonClick(appearanceButton, () -> AnotherActivity.gotoAnotherActivity(activity, AppearanceSettingsBlock.class, false)));
            aboutButton.setOnClickListener(click -> Utils.onDefaultButtonClick(aboutButton, () -> AnotherActivity.gotoAnotherActivity(this, AboutSettingsBlock.class, false)));
            moreButton.setOnClickListener(click -> Utils.onDefaultButtonClick(moreButton, () -> {
                Toast.makeText(this, Database.allRecords().toString(), Toast.LENGTH_LONG).show();
            }));

            goToUserSettings.setOnClickListener(click -> AnotherActivity.gotoAnotherActivity(this, MainSettingsUserBlock.class, false));

            ControlsBar.initActions(this, findViewById(R.id.mainButton), findViewById(R.id.messengerButton));
        }catch (Exception e){
            ErrorDialog.showDialog(this, e, true);
        }
    }

    private void initSearch(String[] searchRequests) {
        ArrayAdapter<String> searchRequestsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, searchRequests);

    }

    @SuppressLint("SetTextI18n")
    public void loadUserData(){
        Utils.displayUserDate(this, avatarView, dateOfRegistrationText, userMailText);
    }
}
