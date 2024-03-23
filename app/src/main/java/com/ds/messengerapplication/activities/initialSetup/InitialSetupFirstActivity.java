package com.ds.messengerapplication.activities.initialSetup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.ds.messengerapplication.R;
import com.ds.messengerapplication.activities.chat.ChatsListPage;
import com.ds.messengerapplication.dialogs.ErrorDialog;
import com.ds.messengerapplication.user.UserController;
import com.ds.messengerapplication.util.AnotherActivity;
import com.google.firebase.FirebaseApp;


public class InitialSetupFirstActivity extends AppCompatActivity {
    private Button buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_initial_setup_first);

            buttonNext = findViewById(R.id.buttonNext);

            buttonNext.setOnClickListener(v -> AnotherActivity.gotoAnotherActivity(this, InitialSetupSecondActivity.class, true));
        }catch (Exception e){
            ErrorDialog.showDialog(this, e, true);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        checkAuthorization();
    }

    private void checkAuthorization(){
        if(UserController.isSignedIn()) AnotherActivity.gotoAnotherActivity(this, ChatsListPage.class, true);
    }

}