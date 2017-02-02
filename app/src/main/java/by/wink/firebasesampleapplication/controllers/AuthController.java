package by.wink.firebasesampleapplication.controllers;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserInfo;

/**
 * Created by amine on 01/02/17.
 */

public class AuthController {
    Activity context;
    FirebaseAuth firebaseAuth;


    public static UserInfo getCurrentUser(){
        return FirebaseAuth.getInstance().getCurrentUser();
    }


    public AuthController(Activity context) {
        this.context = context;
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public static boolean isLogginIn(){
        return FirebaseAuth.getInstance().getCurrentUser() != null;
    }


    public void doLogin(String email, String password, OnCompleteListener<AuthResult> callback) {
        if (firebaseAuth != null) {
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(callback);
        }
    }

    public void doSignup(String email, String password,OnCompleteListener<AuthResult> callback){
        if (firebaseAuth != null) {
            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(callback);
        }
    }

    public static void logout() {
        FirebaseAuth.getInstance().signOut();

    }
}
