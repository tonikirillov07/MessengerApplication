package com.ds.messengerapplication.activities.initialSetup;

import static com.ds.messengerapplication.Constants.MIN_LOGIN_LENGTH;
import static com.ds.messengerapplication.Constants.MIN_PASSWORD_LENGTH;
import static com.ds.messengerapplication.Constants.TEMP_PASSWORD_STORE_EXTRA_KEY;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ds.messengerapplication.R;
import com.ds.messengerapplication.activities.chat.ChatsListPage;
import com.ds.messengerapplication.activities.services.RestorePasswordActivity;
import com.ds.messengerapplication.dialogs.ErrorDialog;
import com.ds.messengerapplication.user.User;
import com.ds.messengerapplication.user.UserAdditionalInfo;
import com.ds.messengerapplication.user.UserController;
import com.ds.messengerapplication.user.database.Database;
import com.ds.messengerapplication.util.AnotherActivity;
import com.ds.messengerapplication.util.EditTextChecker;
import com.ds.messengerapplication.util.IOnAction;
import com.ds.messengerapplication.util.Utils;

public class InitialSetupSecondActivity extends AppCompatActivity {
    private Button buttonNext;
    private EditText passwordField, loginField;
    private TextView title, iHaveAccount, iForgotMyPassword;
    private boolean logInIsOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.initial_setup_second);

            loginField = findViewById(R.id.loginOrEmailTextField);
            passwordField = findViewById(R.id.passwordField);
            buttonNext = findViewById(R.id.buttonNext);
            title = findViewById(R.id.title);
            iHaveAccount = findViewById(R.id.iHaveAccount);
            iForgotMyPassword = findViewById(R.id.iForgotPassword);

            buttonNext.setOnClickListener(v -> onNextButtonAction(this));
            iHaveAccount.setOnClickListener(v -> onHaveAccountButtonAction());
            iForgotMyPassword.setOnClickListener(v -> AnotherActivity.gotoAnotherActivity(this, RestorePasswordActivity.class, false));

            addTextFieldsTextChangeListener(loginField);
            addTextFieldsTextChangeListener(passwordField);
        }catch (Exception e){
            ErrorDialog.showDialog(this, e, true);
        }
    }

    private void onNextButtonAction(AppCompatActivity activity){
        try {
            buttonNext.setEnabled(false);

            if (checkForEnteredData()) {
                if(!logInIsOpen) {
                    UserController.createUser(getLoginText(), getPasswordText(), new IOnAction() {
                        @Override
                        public void onAction() {
                            Database.writeNewUser(new User(getLoginText(), getPasswordText()), UserAdditionalInfo.getWithDefaultValues(), activity);
                            AnotherActivity.gotoAnotherActivity(activity, ChatsListPage.class, true);
                            buttonNext.setEnabled(true);
                        }

                        @Override
                        public void onFailed() {
                            buttonNext.setEnabled(true);
                        }
                    }, this);
                }else
                    UserController.logIn(getLoginText(), getPasswordText(), new IOnAction() {
                        @Override
                        public void onAction() {
                            AnotherActivity.gotoAnotherActivity(activity, ChatsListPage.class, true);
                            buttonNext.setEnabled(true);
                        }

                        @Override
                        public void onFailed() {
                            IOnAction.super.onFailed();

                            buttonNext.setEnabled(true);
                        }
                    }, this);
            } else findErrorReason();
        }catch (Exception e){
            ErrorDialog.showDialog(this, e, true);
        }
    }

    private void onHaveAccountButtonAction(){
        try {
            iHaveAccount.setText(!logInIsOpen ? getResources().getString(R.string.i_have_not_account) : getResources().getString(R.string.i_have_account));
            iForgotMyPassword.setVisibility(!logInIsOpen ? View.VISIBLE : View.INVISIBLE);
            title.setText(!logInIsOpen ? getResources().getString(R.string.well_lets_log_in) : getResources().getString(R.string.first_let_s_create_account));
            logInIsOpen = !logInIsOpen;

            Utils.clearEditText(loginField);
            Utils.clearEditText(passwordField);
        }catch (Exception e){
            ErrorDialog.showDialog(this, e, true);
        }
    }

    private void findErrorReason() {
        try {
            EditTextChecker.checkField(loginField, "login", MIN_LOGIN_LENGTH);
            EditTextChecker.checkField(passwordField, "password", MIN_PASSWORD_LENGTH);
        }catch (Exception e){
            ErrorDialog.showDialog(this, e, true);
        }
    }

    private boolean checkForEnteredData(){
        String loginEnteredData = getLoginText();
        String passwordEnteredData = getPasswordText();

        return (!loginEnteredData.isEmpty() & !passwordEnteredData.isEmpty()) & (loginEnteredData.length() >= 4 & passwordEnteredData.length() >= 8);
    }

    @NonNull
    private String getLoginText(){
        return loginField.getText().toString().trim();
    }

    @NonNull
    private String getPasswordText(){
        return passwordField.getText().toString().trim();
    }

    private void addTextFieldsTextChangeListener(@NonNull EditText editText){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!buttonNext.isEnabled()) buttonNext.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
