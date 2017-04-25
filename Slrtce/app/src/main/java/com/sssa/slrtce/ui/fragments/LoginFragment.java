package com.sssa.slrtce.ui.fragments;

import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sssa.slrtce.R;
import com.sssa.slrtce.base.BaseFragment;
import com.sssa.slrtce.data.network.extras;
import com.sssa.slrtce.data.network.inputview;
import com.sssa.slrtce.data.network.loginAcc;
import com.sssa.slrtce.misc.utils.Extras;
import com.sssa.slrtce.ui.activities.MainActivity;

/**
 * Created by Coolalien on 2/16/2017.
 */

public class LoginFragment extends BaseFragment {

    private TextInputEditText passwordInput, usernameInput, idInput;
    private AppCompatButton login;
    private String username, userpass, userid;
    private LinearLayout background;
    private Extras prefernces;
    private TextView forgot;
    private static boolean torf;
    private static boolean buttonAnim;

    /**
     * Instance of fragment
     * @return
     */
    public static LoginFragment getInstance(boolean torf, boolean buttonAnim){
        setTorf(torf);
        setButtonAnim(buttonAnim);
        return new LoginFragment();
    }


    @Override
    protected int layoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void ui(View rootview) {
        usernameInput = (TextInputEditText) rootview.findViewById(R.id.input_username);
        passwordInput = (TextInputEditText) rootview.findViewById(R.id.input_password);
        idInput = (TextInputEditText) rootview.findViewById(R.id.input_Id);
        login = (AppCompatButton) rootview.findViewById(R.id.btn_login);
        background = (LinearLayout) rootview.findViewById(R.id.login_layout);
        forgot = (TextView) rootview.findViewById(R.id.forgot_pass);
    }

    @Override
    protected void function() {
        login.setEnabled(true);
        if (torf){
            forgot.setVisibility(View.VISIBLE);
        }else {
            forgot.setVisibility(View.INVISIBLE);
        }
        if (buttonAnim){
            Animation animation= AnimationUtils.loadAnimation(getContext(),R.anim.rotate_right_to_left);
            login.setAnimation(animation);
        }
        login.setOnClickListener(onClick);
        forgot.setOnClickListener(onClick);
        prefernces = new Extras(getContext());
        usernameInput.setMaxLines(1);
        idInput.setMaxLines(1);
        forgot.setMaxLines(1);
        usernameInput.setSingleLine(true);
        idInput.setSingleLine(true);
        forgot.setSingleLine(true);
    }

    @Override
    protected Fragment setfragment() {
        return CommonFragment2.getInstance();
    }

    @Override
    protected int setContainerId() {
        return ((MainActivity) getActivity()).setContainerId();
    }

    @Override
    public void FrgamentLoader() {
        super.FrgamentLoader();
    }

    /**
     * OnCLickListerner
     */
    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
            switch (view.getId()){

                case R.id.forgot_pass:
                    FrgamentLoader();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (prefernces.studentTrack()){
                                prefernces.setStudent("9");
                            }
                            if (prefernces.teacherTrack()){
                                prefernces.setTeacher("10");
                            }
                            if (prefernces.nteacherTrack()){
                                prefernces.setNTeacher("11");
                            }
                        }
                    });
                    break;

                case R.id.btn_login:
                    Login(view);
                    break;
            }
        }
    };

    /**
     * Login
     * @param view
     */
    private void Login(View view){
        username = usernameInput.getText().toString().trim();
        userpass = passwordInput.getText().toString().trim();
        userid = idInput.getText().toString().trim();
        if (!username.isEmpty() && !userpass.isEmpty() && !userid.isEmpty()){
            loginAcc.LoginAccount(getContext(),view, LoginFragment.this, username, userpass, userid, new inputview() {
                @Override
                public TextInputEditText gettext() {
                    return usernameInput;
                }
            }, new inputview() {
                @Override
                public TextInputEditText gettext() {
                    return passwordInput;
                }
            }, new inputview() {
                @Override
                public TextInputEditText gettext() {
                    return idInput;
                }
            }, new extras() {
                @Override
                public Extras getExtras() {
                    return prefernces;
                }
            });
        }else{
            if (username.isEmpty() || usernameInput.length() < 4 || usernameInput.length() > 10) {
                usernameInput.setError("enter a valid UserName");
            }
            if (userpass.isEmpty() || passwordInput.length() < 4 || passwordInput.length() > 10) {
                passwordInput.setError("enter a valid password");
            }
            if (userid.isEmpty()){
                idInput.setError("enter a valid Unique-Id");
            }
        }
        prefernces.setfourthYear(true);
    }

    public static void setTorf(boolean tosrf) {
        torf = tosrf;
    }

    public static void setButtonAnim(boolean buttonAnims) {
        buttonAnim = buttonAnims;
    }
}
