package com.ds.messengerapplication.activities.settings;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ds.messengerapplication.R;
import com.ds.messengerapplication.activities.ThanksForFeedbackActivity;
import com.ds.messengerapplication.util.AnotherActivity;
import com.ds.messengerapplication.util.settings.SettingsActivitiesUtils;
import com.ds.projecthelper.dialogs.ErrorDialog;
import com.google.android.material.slider.Slider;

public class FeedbacksBlock extends AppCompatActivity {
    private EditText summarizeFeedbackTextField, explainFeedbackTextField;
    private Button buttonNext, buttonRate;
    private Slider sliderRate;
    private ImageButton backButton;
    private boolean isSliderRateWasInteract = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main_settings_feedbacks_block_page);

            summarizeFeedbackTextField = findViewById(R.id.summarizeYourFeedbackTextField);
            explainFeedbackTextField = findViewById(R.id.explainFeedbackTextField);
            buttonNext = findViewById(R.id.buttonNext);
            buttonRate = findViewById(R.id.buttonRate);
            sliderRate = findViewById(R.id.ratingSlider);
            backButton = findViewById(R.id.backImageButton);

            initButtons();
        }catch (Exception e){
            ErrorDialog.showDialog(this, e, true);
        }
    }

    private void initButtons() {
        try {
            SettingsActivitiesUtils.addActionToBackButton(this, backButton, SettingsActivitiesUtils.MAIN_ACTIVITY_PAGE);
            buttonNext.setOnClickListener(click -> sendFeedbackButtonAction());
            buttonRate.setOnClickListener(click -> onRateButton());
            sliderRate.addOnChangeListener((slider, value, fromUser) -> isSliderRateWasInteract = fromUser);
        }catch (Exception e){
            ErrorDialog.showDialog(this, e, true);
        }
    }

    private void sendFeedbackButtonAction(){
        try {
            if (!checkFeedback()) return;

            AnotherActivity.gotoAnotherActivity(this, ThanksForFeedbackActivity.class, false);

            summarizeFeedbackTextField.getText().clear();
            explainFeedbackTextField.getText().clear();
        }catch (Exception e){
            ErrorDialog.showDialog(this, e, true);
        }
    }

    private boolean checkFeedback(){
        try {
            boolean isSummarizeFeedbackFieldIsEmpty = summarizeFeedbackTextField.getText().toString().isEmpty();
            boolean isExplainFeedbackFieldIsEmpty = explainFeedbackTextField.getText().toString().isEmpty();
            boolean result = !isSummarizeFeedbackFieldIsEmpty & !isExplainFeedbackFieldIsEmpty;

            if (!result) {
                if (isSummarizeFeedbackFieldIsEmpty) {
                    fillThisFieldAlert(summarizeFeedbackTextField);
                }

                if (isExplainFeedbackFieldIsEmpty) {
                    fillThisFieldAlert(explainFeedbackTextField);
                }
            }

            return result;
        }catch (Exception e){
            ErrorDialog.showDialog(this, e, true);
        }

        return false;
    }

    private void fillThisFieldAlert(@NonNull EditText editText){
        editText.setError(getResources().getString(R.string.fill_in_this_field));
    }

    private void onRateButton(){
        if(isSliderRateWasInteract) AnotherActivity.gotoAnotherActivity(this, ThanksForFeedbackActivity.class, false);
        else ErrorDialog.showDialog(this, new Exception(getResources().getString(R.string.rate_this_app_using_slider)), false);
    }
}
