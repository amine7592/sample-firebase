package by.wink.firebasesampleapplication.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import by.wink.firebasesampleapplication.R;
import by.wink.firebasesampleapplication.activities.MainActivity;
import by.wink.firebasesampleapplication.controllers.AuthController;

import static com.google.android.gms.internal.zzs.TAG;

/**
 * Created by amine on 01/02/17.
 */

public class SignupFragment extends Fragment {
    EditText emailEt,passwordEt;
    Button signupBtn;
    AuthController authController;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_signup,container,false);
        emailEt = (EditText) v.findViewById(R.id.email_et);
        passwordEt = (EditText) v.findViewById(R.id.pwd_et);
        signupBtn = (Button)v.findViewById(R.id.signup_btn);
        authController = new AuthController(getActivity());

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
            }
        });
        return v;
    }


    public void signup(){
        authController.doSignup(emailEt.getText().toString(),emailEt.getText().toString(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                // If sign in fails, display a message to the user. If sign in succeeds
                // the auth state listener will be notified and logic to handle the
                // signed in user can be handled in the listener.
                if (!task.isSuccessful()) {
                    Log.w(TAG, "signInWithEmail:failed", task.getException());
                    Toast.makeText(getActivity(), R.string.auth_failed,
                            Toast.LENGTH_SHORT).show();
                }else {

                    Intent i = new Intent(getContext(), MainActivity.class);
                    startActivity(i);

                }
            }
        });


    }

    public static SignupFragment getInstance(){
        return new SignupFragment();

    }
}
