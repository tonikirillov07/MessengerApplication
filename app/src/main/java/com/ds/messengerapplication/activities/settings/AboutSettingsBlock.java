package com.ds.messengerapplication.activities.settings;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ds.messengerapplication.R;
import com.ds.messengerapplication.dialogs.ErrorDialog;
import com.ds.messengerapplication.util.ControlsBar;
import com.ds.messengerapplication.util.Utils;
import com.ds.messengerapplication.util.settings.SettingsActivitiesUtils;

public class AboutSettingsBlock extends AppCompatActivity {
    private TextView appNameTextView, versionTextView, buildTextView, developerTextView;
    private LinearLayout appInfoLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main_settings_about_block_page);

            appNameTextView = findViewById(R.id.appName);
            versionTextView = findViewById(R.id.version);
            buildTextView = findViewById(R.id.build);
            developerTextView = findViewById(R.id.developer);
            appInfoLinearLayout = findViewById(R.id.appInfoLinearLayout);
            ImageButton backButton = findViewById(R.id.getBackToMainSettingsButton);

            SettingsActivitiesUtils.addActionToBackButton(this, backButton, SettingsActivitiesUtils.MAIN_ACTIVITY_PAGE);
            ControlsBar.initActions(this, findViewById(R.id.mainButton), findViewById(R.id.messengerButton));

            loadAppInfo();
        }catch (Exception e){
            ErrorDialog.showDialog(this, e, true);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        Utils.addTranslateAnimationByUpOrByDown(appInfoLinearLayout, true);
    }

    @SuppressLint("SetTextI18n")
    private void loadAppInfo() {
        try {
            appNameTextView.setText(getString(R.string.settings_app_name) + " " + getString(R.string.app_name));
            versionTextView.setText(getString(R.string.app_version) + " " + getString(R.string.app_current_version));
            buildTextView.setText(getString(R.string.app_build) + " " + getString(R.string.app_current_build));
            developerTextView.setText(getString(R.string.app_developer) + " " + getString(R.string.app_developer_name));
        }catch (Exception e){
            ErrorDialog.showDialog(this, e, true);
        }
    }
}
