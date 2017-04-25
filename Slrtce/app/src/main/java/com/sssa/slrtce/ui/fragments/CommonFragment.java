package com.sssa.slrtce.ui.fragments;

import android.support.percent.PercentLayoutHelper;
import android.support.percent.PercentRelativeLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.sssa.slrtce.R;
import com.sssa.slrtce.base.BaseFragment;
import com.sssa.slrtce.misc.utils.Extras;
import com.sssa.slrtce.misc.widgets.VerticalTextView;

/**
 * Created by Coolalien on 3/3/2017.
 */

public class CommonFragment extends BaseFragment {

    private VerticalTextView Login, Signup;
    private LinearLayout showSignup, showLogin;
    private Extras prefernces;

    /**
     * Instance of this class
     * @return
     */

    public static CommonFragment getInstance(){
        return new CommonFragment();
    }

    @Override
    protected int layoutId() {
        return R.layout.loginprocess_view;
    }

    @Override
    protected void ui(View rootview) {
        Signup = (VerticalTextView) rootview.findViewById(R.id.SignUp);
        Login = (VerticalTextView) rootview.findViewById(R.id.Login);
        showSignup = (LinearLayout) rootview.findViewById(R.id.showsignup);
        showLogin = (LinearLayout) rootview.findViewById(R.id.showSignin);
    }

    @Override
    protected void function() {
        showSigninForm();
        Signup.setOnClickListener(onClick);
        Login.setOnClickListener(onClick);
        prefernces = new Extras(getContext());
    }

    @Override
    protected Fragment setfragment() {
        return null;
    }

    @Override
    protected int setContainerId() {
       return 0;
    }

    /**
     * ClickListerner
     */
    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){

                case R.id.SignUp :
                    showSignupForm();
                    prefernces.setStudent("2");
                    prefernces.setTeacher("5");
                    prefernces.setNTeacher("8");
                    break;

                case R.id.Login:
                    showSigninForm();
                    break;
            }
        }
    };

    /**
     * signup show
     */
    private void showSignupForm() {
        PercentRelativeLayout.LayoutParams paramsLogin = (PercentRelativeLayout.LayoutParams) showLogin.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoLogin = paramsLogin.getPercentLayoutInfo();
        infoLogin.widthPercent = 0.15f;
        showLogin.requestLayout();

        PercentRelativeLayout.LayoutParams paramsSignup = (PercentRelativeLayout.LayoutParams) showSignup.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoSignup = paramsSignup.getPercentLayoutInfo();
        infoSignup.widthPercent = 0.85f;
        showSignup.requestLayout();

        Signup.setVisibility(View.GONE);
        Login.setVisibility(View.VISIBLE);
        Animation translate= AnimationUtils.loadAnimation(getContext(),R.anim.translate_right_to_left);
        showSignup.startAnimation(translate);
        signupFragmentLoader();
    }


    /**
     * signin show
     */
    private void showSigninForm() {
        PercentRelativeLayout.LayoutParams paramsLogin = (PercentRelativeLayout.LayoutParams) showLogin.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoLogin = paramsLogin.getPercentLayoutInfo();
        infoLogin.widthPercent = 0.85f;
        showLogin.requestLayout();


        PercentRelativeLayout.LayoutParams paramsSignup = (PercentRelativeLayout.LayoutParams) showSignup.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoSignup = paramsSignup.getPercentLayoutInfo();
        infoSignup.widthPercent = 0.15f;
        showSignup.requestLayout();

        Animation translate= AnimationUtils.loadAnimation(getContext(),R.anim.translate_left_to_right);
        showLogin.startAnimation(translate);

        Signup.setVisibility(View.VISIBLE);
        Login.setVisibility(View.GONE);
        loginFragmentLoader();

    }

    private void signupFragmentLoader(){
        getFragmentManager().beginTransaction().replace(R.id.signup_container, SignupFragement.getInstance(true)).commit();
    }


    private void loginFragmentLoader(){
        getFragmentManager().beginTransaction().replace(R.id.login_container, LoginFragment.getInstance(true, true)).commit();
    }

}
