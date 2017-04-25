package com.sssa.slrtce.ui.fragments;

import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.sssa.slrtce.R;
import com.sssa.slrtce.base.BaseFragment;
import com.sssa.slrtce.data.network.inputview;
import com.sssa.slrtce.data.network.registerAcc;

/**
 * Created by Coolalien on 2/16/2017.
 */

public class SignupFragement extends BaseFragment{

    private TextInputEditText emailInput, passwordInput, usernameInput, confirmpasswordInput, fullnameInput;
    private AppCompatButton signUp;
    private String userfullname, usermail, userpass, username, userconfirmpass;
    private static boolean buttonAnim;

    /**
     * Instance of fragment
     * @return
     */
    public static SignupFragement getInstance(boolean buttonAnim){
        setButtonAnim(buttonAnim);
        return new SignupFragement();
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_signup;
    }

    @Override
    protected void ui(View rootview) {
        emailInput = (TextInputEditText) rootview.findViewById(R.id.input_email);
        passwordInput = (TextInputEditText) rootview.findViewById(R.id.input_password);
        signUp = (AppCompatButton) rootview.findViewById(R.id.btn_signup);
        usernameInput = (TextInputEditText) rootview.findViewById(R.id.input_username);
        confirmpasswordInput = (TextInputEditText) rootview.findViewById(R.id.input_passwordr);
        fullnameInput = (TextInputEditText) rootview.findViewById(R.id.input_fullname);
    }

    @Override
    protected void function() {
        signUp.setEnabled(true);
        signUp.setOnClickListener(onClick);
        if (buttonAnim){
            Animation animation= AnimationUtils.loadAnimation(getContext(),R.anim.rotate_left_to_right);
            signUp.setAnimation(animation);
        }
        emailInput.setMaxLines(1);
        usernameInput.setMaxLines(1);
        fullnameInput.setMaxLines(1);
        usernameInput.setSingleLine(true);
        emailInput.setSingleLine(true);
        fullnameInput.setSingleLine(true);
    }

    @Override
    protected Fragment setfragment() {
        return null;
    }

    @Override
    protected int setContainerId() {
        return 0;
    }

    @Override
    public void FrgamentLoader() {
        super.FrgamentLoader();
    }



    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
            switch (view.getId()){

                case R.id.btn_signup:
                    registerAcc(view);
                    break;
            }
        }
    };


    /**
     * Register account
     * @param view
     */
    private void registerAcc(View view){
        usermail = emailInput.getText().toString().trim();
        userpass = passwordInput.getText().toString().trim();
        username = usernameInput.getText().toString().trim();
        userfullname = fullnameInput.getText().toString().trim();
        userconfirmpass = confirmpasswordInput.getText().toString().trim();
        if (!userfullname.isEmpty() && !usermail.isEmpty() && !userpass.isEmpty() && !username.isEmpty()){
            /**
             * register account
             */
            registerAcc.RegisterAccount(getContext(),view, userfullname, username, usermail, userpass, userconfirmpass, new inputview() {
                @Override
                public TextInputEditText gettext() {
                    return emailInput;
                }
            }, new inputview() {
                @Override
                public TextInputEditText gettext() {
                    return passwordInput;
                }
            }, new inputview() {
                @Override
                public TextInputEditText gettext() {
                    return usernameInput;
                }
            }, new inputview() {
                @Override
                public TextInputEditText gettext() {
                    return fullnameInput;
                }
            }, new inputview() {
                @Override
                public TextInputEditText gettext() {
                    return confirmpasswordInput;
                }
            });
        }else {
            if (usermail.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(usermail).matches()) {
                emailInput.setError("enter a valid email address");
            }
            if (username.isEmpty() || usernameInput.length() < 4 || usernameInput.length() > 10){
                usernameInput.setError("enter valid username");
            }
            if (userpass.isEmpty() || passwordInput.length() < 4 || passwordInput.length() > 10) {
                passwordInput.setError("between 4 and 10 alphanumeric characters");
            }
            if (userconfirmpass.isEmpty() || confirmpasswordInput.length() < 4 || confirmpasswordInput.length() > 10) {
                confirmpasswordInput.setError("confirm password");
            }
            if (userfullname.isEmpty()){
                fullnameInput.setError("enter valid full name");
            }
        }
    }

    public static void setButtonAnim(boolean buttonAnims) {
        buttonAnim = buttonAnims;
    }
}
