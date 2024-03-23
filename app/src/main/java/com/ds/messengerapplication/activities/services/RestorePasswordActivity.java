package com.ds.messengerapplication.activities.services;

import static com.ds.messengerapplication.Constants.MIN_EMAIL_LENGTH;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ds.messengerapplication.R;
import com.ds.messengerapplication.dialogs.ErrorDialog;
import com.ds.messengerapplication.util.EditTextChecker;

public class RestorePasswordActivity extends AppCompatActivity {
    private EditText mailTextField;
    private Button buttonNextWithEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.password_restore_page);

            mailTextField = findViewById(R.id.emailAddressTextField);
            buttonNextWithEmail = findViewById(R.id.buttonNextWithEmail);

            buttonNextWithEmail.setOnClickListener(v -> onRestoreWithEmailButtonClick());
        }catch (Exception e){
            ErrorDialog.showDialog(this, e, true);
        }
    }

    @NonNull
    private String getEmail(){
        return mailTextField.getText().toString().trim();
    }

    private void onRestoreWithEmailButtonClick(){
        if(EditTextChecker.checkField(mailTextField, MIN_EMAIL_LENGTH)) {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        }else EditTextChecker.checkField(mailTextField, "email", MIN_EMAIL_LENGTH);
    }
}
