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
import com.ds.messengerapplication.user.UserController;
import com.ds.messengerapplication.util.EditTextChecker;
import com.ds.messengerapplication.util.Utils;
import com.ds.messengerapplication.util.sounds.SoundPlayer;
import com.ds.messengerapplication.util.sounds.SoundsConstants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class RestorePasswordActivity extends AppCompatActivity {
    private EditText mailTextField, currentPasswordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.password_restore_page);

            mailTextField = findViewById(R.id.emailAddressTextField);
            currentPasswordField = findViewById(R.id.currentPasswordEditText);
            Button buttonNextWithEmail = findViewById(R.id.buttonNextWithEmail);
            Button buttonNextWithCurrentPassword = findViewById(R.id.buttonNextWithCurrentPassword);

            buttonNextWithEmail.setOnClickListener(v -> onRestoreWithEmailButtonClick());
            buttonNextWithCurrentPassword.setOnClickListener(v -> onRestoreWithCurrentPasswordButtonClick());
        }catch (Exception e){
            ErrorDialog.showDialog(this, e, true);
        }
    }

    @NonNull
    private String getEmail(){
        return mailTextField.getText().toString().trim();
    }

    private void onRestoreWithEmailButtonClick(){
        SoundPlayer.create(this, SoundsConstants.CLICK_SOUND_PATH, true);

        FirebaseUser firebaseUser = Objects.requireNonNull(UserController.getInstance().getFirebaseAuth().getCurrentUser());

        if(EditTextChecker.checkField(mailTextField, MIN_EMAIL_LENGTH)) {
            if(Objects.equals(firebaseUser.getEmail(), mailTextField.getText().toString().trim())){
                firebaseUser.sendEmailVerification().addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Toast.makeText(this, "Sended verification email", Toast.LENGTH_SHORT).show();

                        if(firebaseUser.isEmailVerified()){
                            Toast.makeText(this, "Seccess", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }else EditTextChecker.checkField(mailTextField, "email", MIN_EMAIL_LENGTH);
    }

    private void onRestoreWithCurrentPasswordButtonClick(){
        SoundPlayer.create(this, SoundsConstants.CLICK_SOUND_PATH, true);

        if(EditTextChecker.checkField(currentPasswordField, MIN_EMAIL_LENGTH)) {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        }else EditTextChecker.checkField(currentPasswordField, "password", MIN_EMAIL_LENGTH);
    }
}
