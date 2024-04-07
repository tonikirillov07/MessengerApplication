package com.ds.messengerapplication.activities.settings;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.ds.messengerapplication.Constants;
import com.ds.messengerapplication.R;
import com.ds.messengerapplication.dialogs.ErrorDialog;
import com.ds.messengerapplication.user.UserController;
import com.ds.messengerapplication.user.database.databaseGetterAndSetter.DatabaseValueSetter;
import com.ds.messengerapplication.user.database.databaseGetterAndSetter.DatabaseValuesGetter;
import com.ds.messengerapplication.util.ControlsBar;
import com.ds.messengerapplication.util.Utils;
import com.ds.messengerapplication.util.settings.SettingsActivitiesUtils;
import com.google.android.material.slider.Slider;

public class AppearanceSettingsBlock extends AppCompatActivity {
    private LinearLayout colorSettingsLinearLayout, themeSettingsLinearLayout, soundsSettingsLinearLayout, scrollBarsSettingsLinearLayout;
    private Slider contrastSlider, brightnessSlider, saturationSlider;
    private RadioGroup themesRadioButtonGroup;
    private CheckBox useSoundsCheckBox, useScrollBarsCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main_settings_appearance_block_page);

            colorSettingsLinearLayout = findViewById(R.id.colorSettingsLinearLayout);
            themeSettingsLinearLayout = findViewById(R.id.themeSettingsLinearLayout);
            soundsSettingsLinearLayout = findViewById(R.id.soundsSettingsLinearLayout);
            scrollBarsSettingsLinearLayout = findViewById(R.id.scrollBarSettingsLinearLayout);

            contrastSlider = findViewById(R.id.contrastSlider);
            brightnessSlider = findViewById(R.id.brightnessSlider);
            saturationSlider = findViewById(R.id.saturationSlider);

            themesRadioButtonGroup = findViewById(R.id.themesRadioGroup);

            useSoundsCheckBox = findViewById(R.id.useSoundsCheckBox);
            useScrollBarsCheckBox = findViewById(R.id.showScrollBarsCheckBox);

            SettingsActivitiesUtils.addActionToBackButton(this, findViewById(R.id.getBackImageButton), SettingsActivitiesUtils.MAIN_ACTIVITY_PAGE);
            ControlsBar.initActions(this, findViewById(R.id.mainButton), findViewById(R.id.messengerButton));

            initSettingsStates();
            initSliders();
            initThemeRadioButtonsGroup();
            initDisplayScrollBarsCheckBox();
            initSoundsCheckBox();
        }catch (Exception e){
            ErrorDialog.showDialog(this, e, true);
        }
    }

    private void initSoundsCheckBox() {
        try {
            useSoundsCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> DatabaseValueSetter.changeValue(Constants.USER_USE_SOUNDS_REFERENCE_PATH, String.valueOf(isChecked), UserController.getUserId(), this));
        }catch (Exception e){
            ErrorDialog.showDialog(this, e, true);
        }
    }

    private void initDisplayScrollBarsCheckBox() {
        try {
            useScrollBarsCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> DatabaseValueSetter.changeValue(Constants.USER_USE_SCROLL_BARS_REFERENCE_PATH, String.valueOf(isChecked), UserController.getUserId(), this));
        }catch (Exception e){
            ErrorDialog.showDialog(this, e, true);
        }
    }

    private void initThemeRadioButtonsGroup() {
        try {
            initThemeRadioButtonGroupValue();

            themesRadioButtonGroup.setOnCheckedChangeListener((group, checkedId) -> {
                try{
                    DatabaseValueSetter.changeValue(Constants.USER_THEME_REFERENCE_PATH, findViewById(checkedId).getTag().toString(), UserController.getUserId(), this);
                }catch (Exception e){
                    ErrorDialog.showDialog(this, e, true);
                }
            });
        }catch (Exception e){
            ErrorDialog.showDialog(this, e, true);
        }
    }

    private void initThemeRadioButtonGroupValue() {
        try {
            DatabaseValuesGetter.findValue(UserController.getUserId(), Constants.USER_THEME_REFERENCE_PATH, this, result -> {
                for (int i = 0; i < themesRadioButtonGroup.getChildCount(); i++) {
                    RadioButton currentThemeRadioButton = (RadioButton) themesRadioButtonGroup.getChildAt(i);
                    currentThemeRadioButton.setSelected(currentThemeRadioButton.getTag().toString().equals(result));
                }
            });
        }catch (Exception e){
            ErrorDialog.showDialog(this, e, true);
        }
    }

    private void initSettingsStates() {
        try {
            String userId = UserController.getUserId();

            DatabaseValuesGetter.findValue(userId, Constants.USER_CONTRAST_REFERENCE_PATH, this, result -> contrastSlider.setValue(Float.parseFloat(result)));
            DatabaseValuesGetter.findValue(userId, Constants.USER_SATURATION_REFERENCE_PATH, this, result -> saturationSlider.setValue(Float.parseFloat(result)));
            DatabaseValuesGetter.findValue(userId, Constants.USER_BRIGHTNESS_REFERENCE_PATH, this, result -> brightnessSlider.setValue(Float.parseFloat(result)));
            DatabaseValuesGetter.findValue(userId, Constants.USER_USE_SCROLL_BARS_REFERENCE_PATH, this, result -> useScrollBarsCheckBox.setChecked(Boolean.parseBoolean(result)));
            DatabaseValuesGetter.findValue(userId, Constants.USER_USE_SOUNDS_REFERENCE_PATH, this, result -> useSoundsCheckBox.setChecked(Boolean.parseBoolean(result)));
        }catch (Exception e){
            ErrorDialog.showDialog(this, e, true);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        Utils.addTranslateAnimationByUpOrByDown(colorSettingsLinearLayout, true);
        Utils.addTranslateAnimationByUpOrByDown(themeSettingsLinearLayout, true);
        Utils.addTranslateAnimationByUpOrByDown(soundsSettingsLinearLayout, true);
        Utils.addTranslateAnimationByUpOrByDown(scrollBarsSettingsLinearLayout, true);
    }

    private void initSliders() {
        try {
            contrastSlider.addOnChangeListener((slider, v, b) -> DatabaseValueSetter.changeValue(Constants.USER_CONTRAST_REFERENCE_PATH, String.valueOf(slider.getValue()), UserController.getUserId(), this));
            brightnessSlider.addOnChangeListener((slider, v, b) -> DatabaseValueSetter.changeValue(Constants.USER_BRIGHTNESS_REFERENCE_PATH, String.valueOf(slider.getValue()), UserController.getUserId(), this));
            saturationSlider.addOnChangeListener((slider, v, b) -> DatabaseValueSetter.changeValue(Constants.USER_SATURATION_REFERENCE_PATH, String.valueOf(slider.getValue()), UserController.getUserId(), this));
        }catch (Exception e){
            ErrorDialog.showDialog(this, e, true);
        }
    }
}
