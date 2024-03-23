package com.ds.messengerapplication.activities.settings;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutSettingsBlock extends AppCompatActivity {
    private TextView appNameTextView, versionTextView, buildTextView, developerTextView;
    private LinearLayout appInfoLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_settings_about_block_page);

        appNameTextView = findViewById(R.id.appName);
        versionTextView = findViewById(R.id.version);
        buildTextView = findViewById(R.id.build);
        developerTextView = findViewById(R.id.developer);
        appInfoLinearLayout = findViewById(R.id.appInfoLinearLayout);
        ImageButton backButton = findViewById(R.id.getBackToMainSettingsButton);

        SettingsActivitiesUtils.addActionToBackButton(this, backButton, SettingsActivitiesUtils.MAIN_ACTIVITY_PAGE);
        loadAppInfo();
    }

    @Override
    protected void onStart() {
        super.onStart();

        Utils.addTranslateAnimationByUpOrByDown(appInfoLinearLayout, true);
    }

    @SuppressLint("SetTextI18n")
    private void loadAppInfo() {
        appNameTextView.setText(getResources().getString(R.string.settings_app_name));
    }
}
