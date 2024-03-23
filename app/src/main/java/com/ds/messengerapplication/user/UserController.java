package com.ds.messengerapplication.user;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.ds.messengerapplication.util.IOnAction;
import com.ds.projecthelper.dialogs.ErrorDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class UserController {
    private final FirebaseAuth firebaseAuth;
    private static UserController instance = null;

    private UserController() {
        firebaseAuth = FirebaseAuth.getInstance();

        initAuthStateListener();
    }

    public static synchronized UserController getInstance(){
        if(instance == null){
            instance = new UserController();
        }

        return instance;
    }

    public static boolean isSignedIn(){
        return getInstance().getFirebaseAuth().getCurrentUser() != null;
    }

    public static void createUser(String email, String password, IOnAction onSuccessfulAction, Context context){
        getInstance().getFirebaseAuth().createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                onSuccessfulAction.onAction();

                Log.i(UserController.class.getName(), "User with email " + email + " created successfully");
            } else {
                ErrorDialog.showDialog(context, Objects.requireNonNull(task.getException()), true);
            }
        });
    }

    private void initAuthStateListener(){
        getFirebaseAuth().addAuthStateListener(firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if(user != null){
                user.sendEmailVerification().addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Log.i("AUTH_STATE_LISTENER", "Verification success");
                    }else{
                        Log.e("AUTH_STATE_LISTENER", Objects.requireNonNull(task.getException()).toString());
                    }
                });
            }
        });
    }

    public static void logIn(String email, String password, IOnAction onSuccessfulAction, Context context){
        getInstance().getFirebaseAuth().signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                onSuccessfulAction.onAction();

                Log.i(UserController.class.getName(), "User with email " + email + " logged in successfully");
            }else{
                ErrorDialog.showDialog(context, Objects.requireNonNull(task.getException()), true);
            }
        });

    }
    public static void logOut(){
        getInstance().getFirebaseAuth().signOut();

        Log.i(UserController.class.getName(), "Account logged out");
    }

    @NonNull
    public static String getUserId(){
        return Objects.requireNonNull(getInstance().getFirebaseAuth().getCurrentUser()).getUid();
    }

    public FirebaseAuth getFirebaseAuth() {
        return firebaseAuth;
    }
}
