package com.sssa.slrtce.ui.fragments;

import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.sssa.slrtce.R;
import com.sssa.slrtce.base.BaseFragment;
import com.sssa.slrtce.data.network.ForgotPass;
import com.sssa.slrtce.data.network.inputview;
import com.sssa.slrtce.ui.activities.MainActivity;

import static com.sssa.slrtce.R.id.reset_pass;

/**
 * Created by Coolalien on 2/17/2017.
 */

public class ForgotFragment extends BaseFragment {

    private TextInputEditText passwordInput, usernameInput, idInput, confirmpassInput;
    private AppCompatButton resetBtn;
    private String username, userpass, userid, userconfirmpass;
    private static boolean buttonAnim;

    /**
     * Instance of this class
     * @return
     */
    public static ForgotFragment getInstance(boolean torf){
        setButtonAnim(torf);
        return new ForgotFragment();
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_forgotpass;
    }

    @Override
    protected void ui(View rootview) {
        usernameInput = (TextInputEditText) rootview.findViewById(R.id.input_fusername);
        passwordInput = (TextInputEditText) rootview.findViewById(R.id.input_fpassword);
        idInput = (TextInputEditText) rootview.findViewById(R.id.input_fId);
        confirmpassInput = (TextInputEditText) rootview.findViewById(R.id.input_fconfirmpass);
        resetBtn = (AppCompatButton) rootview.findViewById(reset_pass);
    }

    @Override
    protected void function() {
        resetBtn.setEnabled(true);
        resetBtn.setOnClickListener(onClick);
        if (buttonAnim){
            Animation animation= AnimationUtils.loadAnimation(getContext(),R.anim.rotate_left_to_right);
            resetBtn.setAnimation(animation);
        }
        usernameInput.setMaxLines(1);
        idInput.setMaxLines(1);
        usernameInput.setSingleLine(true);
        idInput.setSingleLine(true);
    }

    @Override
    protected Fragment setfragment() {
        return null;
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
     * ClickListerner
     */
    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()){

                /*case R.id.link_login:
                    FrgamentLoader();
                    break;*/

                case R.id.reset_pass:
                    forgotPass(view);
                    break;
            }
        }
    };

    /**
     * Forgot Password
     */
    private void forgotPass(View view){
        username = usernameInput.getText().toString().trim();
        userpass = passwordInput.getText().toString().trim();
        userid = idInput.getText().toString().trim();
        userconfirmpass = confirmpassInput.getText().toString().trim();

        if (!username.isEmpty() && !userpass.isEmpty() && !userid.isEmpty() && !userconfirmpass.isEmpty()){

            ForgotPass.ForgotPassAcc(getContext(), view, username, userid, userpass, userconfirmpass, new inputview() {
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
            }, new inputview() {
                @Override
                public TextInputEditText gettext() {
                    return confirmpassInput;
                }
            });
        }else{
            if (username.isEmpty() || usernameInput.length() < 4 || usernameInput.length() > 10) {
                usernameInput.setError("enter a valid username");
            }
            if (userpass.isEmpty() || passwordInput.length() < 4 || passwordInput.length() > 10) {
                passwordInput.setError("enter a valid password");
            }
            if (userconfirmpass.isEmpty() || confirmpassInput.length() < 4 || confirmpassInput.length() > 10) {
                confirmpassInput.setError("enter a valid confirm password");
            }
            if (userid.isEmpty()){
                idInput.setError("enter a valid unique-Id");
            }
        }
    }

    public static void setButtonAnim(boolean buttonAnims) {
        buttonAnim = buttonAnims;
    }

}
