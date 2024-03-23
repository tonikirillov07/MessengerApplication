package com.ds.messengerapplication.activities.settings;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.ds.messengerapplication.R;
import com.ds.messengerapplication.dialogs.ErrorDialog;
import com.ds.messengerapplication.user.database.Database;
import com.ds.messengerapplication.util.AnotherActivity;
import com.ds.messengerapplication.util.Utils;

import io.getstream.avatarview.AvatarView;

public class MainSettingsPage extends AppCompatActivity {
    private SearchView searchView;
    private TextView userMailText, dateOfRegistrationText, goToUserSettings;
    private AvatarView avatarView;
    private LinearLayout sendFeedbackButton, resetSettingsButton, appearanceButton, aboutButton, moreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main_settings_page);

            searchView = findViewById(R.id.searchView);
            userMailText = findViewById(R.id.userName);
            dateOfRegistrationText = findViewById(R.id.dateOfRegistration);
            avatarView = findViewById(R.id.avatarView);
            sendFeedbackButton = findViewById(R.id.sendFeedBackButton);
            resetSettingsButton = findViewById(R.id.resetSettingsButton);
            appearanceButton = findViewById(R.id.appearanceButton);
            aboutButton = findViewById(R.id.aboutButton);
            moreButton = findViewById(R.id.moreButton);
            goToUserSettings = findViewById(R.id.gotoUserSettings);

            initButtons();
            initSearch(getResources().getStringArray(R.array.search_requests));
            loadUserData();
        }catch (Exception e){
            ErrorDialog.showDialog(this, e, true);
        }
    }

    private void initButtons() {
        try{
            sendFeedbackButton.setOnClickListener(click -> Utils.onSettingButtonClick(sendFeedbackButton, () -> AnotherActivity.gotoAnotherActivity(this, FeedbacksBlock.class, false)));
            resetSettingsButton.setOnClickListener(click -> Utils.onSettingButtonClick(resetSettingsButton, () -> {

            }));
            appearanceButton.setOnClickListener(click -> Utils.onSettingButtonClick(appearanceButton, () -> AnotherActivity.gotoAnotherActivity(this, AppearanceSettingsBlock.class, false)));
            aboutButton.setOnClickListener(click -> Utils.onSettingButtonClick(aboutButton, () -> AnotherActivity.gotoAnotherActivity(this, AboutSettingsBlock.class, false)));
            moreButton.setOnClickListener(click -> Utils.onSettingButtonClick(moreButton, () -> {
                Toast.makeText(this, Database.allRecords().toString(), Toast.LENGTH_LONG).show();
            }));

            goToUserSettings.setOnClickListener(click -> AnotherActivity.gotoAnotherActivity(this, MainSettingsUserBlock.class, false));
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
