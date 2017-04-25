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
 * Created by Coolalien on 3/4/2017.
 */

public class CommonFragment2 extends BaseFragment {

    private VerticalTextView Login, Forgot;
    private LinearLayout showForgot, showLogin;
    private Extras prefernces;

    /**
     * Instance of this class
     * @return
     */
    public static CommonFragment2 getInstance(){
        return new CommonFragment2();
    }

    @Override
    protected int layoutId() {
        return R.layout.loginprocess_view2;
    }

    @Override
    protected void ui(View rootview) {
        Forgot = (VerticalTextView) rootview.findViewById(R.id.Forgot);
        Login = (VerticalTextView) rootview.findViewById(R.id.Login);
        showForgot = (LinearLayout) rootview.findViewById(R.id.showForgot);
        showLogin = (LinearLayout) rootview.findViewById(R.id.showlogin);
    }

    @Override
    protected void function() {
        showForgotForm();
        Forgot.setOnClickListener(onClick);
        Login.setOnClickListener(onClick);
        prefernces = new Extras(getContext());
    }

    @Override
    protected Fragment setfragment() {
        return  null;
    }

    @Override
    protected int setContainerId() {
       return 0;
    }

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){

                case R.id.Forgot :
                    showForgotForm();
                    break;

                case R.id.Login:
                    showSigninForm();
                    break;
            }
        }
    };


    private void showForgotForm() {
        PercentRelativeLayout.LayoutParams paramsLogin = (PercentRelativeLayout.LayoutParams) showLogin.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoLogin = paramsLogin.getPercentLayoutInfo();
        infoLogin.widthPercent = 0.15f;
        showLogin.requestLayout();

        PercentRelativeLayout.LayoutParams paramsSignup = (PercentRelativeLayout.LayoutParams) showForgot.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoSignup = paramsSignup.getPercentLayoutInfo();
        infoSignup.widthPercent = 0.85f;
        showForgot.requestLayout();

        Forgot.setVisibility(View.GONE);
        Login.setVisibility(View.VISIBLE);
        Animation translate= AnimationUtils.loadAnimation(getContext(),R.anim.translate_right_to_left);
        showForgot.startAnimation(translate);
        forgotFragmentLoader();
    }


    private void showSigninForm() {
        PercentRelativeLayout.LayoutParams paramsLogin = (PercentRelativeLayout.LayoutParams) showLogin.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoLogin = paramsLogin.getPercentLayoutInfo();
        infoLogin.widthPercent = 0.85f;
        showLogin.requestLayout();


        PercentRelativeLayout.LayoutParams paramsSignup = (PercentRelativeLayout.LayoutParams) showForgot.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoSignup = paramsSignup.getPercentLayoutInfo();
        infoSignup.widthPercent = 0.15f;
        showForgot.requestLayout();

        Animation translate= AnimationUtils.loadAnimation(getContext(),R.anim.translate_left_to_right);
        showLogin.startAnimation(translate);

        Forgot.setVisibility(View.VISIBLE);
        Login.setVisibility(View.GONE);
        loginFragmentLoader();

    }



    private void forgotFragmentLoader(){
        getFragmentManager().beginTransaction().replace(R.id.forgot_container, ForgotFragment.getInstance(true)).commit();
    }


    private void loginFragmentLoader(){
        getFragmentManager().beginTransaction().replace(R.id.login_container, LoginFragment.getInstance(false, true)).commit();
    }

}
