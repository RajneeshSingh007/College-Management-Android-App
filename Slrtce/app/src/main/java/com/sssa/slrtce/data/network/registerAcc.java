package com.sssa.slrtce.data.network;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.sssa.slrtce.misc.utils.Constants;
import com.sssa.slrtce.misc.utils.Extras;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Coolalien on 2/22/2017.
 */

public class registerAcc {

    /**
     * Default Constructor
     */
    public registerAcc(){
    }


    /**
     * Register user account
     * @param view
     * @param username
     * @param usermail
     * @param userpass
     * @param inputview
     * @param inputview2
     * @param inputview3
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void RegisterAccount(final Context context, final View view, final String fullName, final String username, final String usermail, final String userpass, final String confirmpass, final inputview inputview, final inputview inputview2, final inputview inputview3, final  inputview inputview4, final  inputview inputview5) {
        final Extras prefrences = new Extras(context);
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(android.R.style.Widget_Material_ProgressBar);
        progressDialog.setIndeterminate(true);
        progressDialog.setTitle("Registration");
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        if (!TextUtils.isEmpty(prefrences.isStudent())) {
            if (prefrences.isStudent().equals("2") && prefrences.studentTrack()){
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        AndroidNetworking.post(Constants.SRegisterUrl)
                                .addBodyParameter("fullname", fullName)
                                .addBodyParameter("username", username)
                                .addBodyParameter("email", usermail)
                                .addBodyParameter("password", userpass)
                                .addBodyParameter("confirmpassword", confirmpass)
                                .setTag("post_registers")
                                .setPriority(Priority.MEDIUM)
                                .build()
                                .getAsJSONObject(new JSONObjectRequestListener() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        inputview.gettext().getText().clear();
                                        inputview2.gettext().getText().clear();
                                        inputview3.gettext().getText().clear();
                                        inputview4.gettext().getText().clear();
                                        inputview5.gettext().getText().clear();
                                        progressDialog.dismiss();
                                        try {
                                            boolean goterror = response.getBoolean("error");
                                            if (!goterror){
                                                Log.d("SignUp", "success"+response.toString());
                                                String msg = "Verification code is sent on your email";
                                                Snackbar.make(view, msg, Snackbar.LENGTH_LONG).setText(msg).show();
                                                Log.d("Student", "Register Success");
                                            }else {
                                                String error_msg = response.getString("error_msg");
                                                progressDialog.dismiss();
                                                Snackbar.make(view, error_msg, Snackbar.LENGTH_LONG).setText(error_msg).show();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }

                                    @Override
                                    public void onError(ANError anError) {

                                    }
                                });
                    }
                },200);
            }
        }else {
            Log.d("Student Register", "Empty");
        }
        if (!TextUtils.isEmpty(prefrences.isTeacher())){
            if (prefrences.isTeacher().equals("5") && prefrences.teacherTrack()){
               new Handler().postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       AndroidNetworking.post(Constants.TRegisterUrl)
                               .addBodyParameter("fullname", fullName)
                               .addBodyParameter("username", username)
                               .addBodyParameter("email", usermail)
                               .addBodyParameter("password", userpass)
                               .addBodyParameter("confirmpassword", confirmpass)
                               .setTag("post_registert")
                               .setPriority(Priority.MEDIUM)
                               .build()
                               .getAsJSONObject(new JSONObjectRequestListener() {
                                   @Override
                                   public void onResponse(JSONObject response) {
                                       inputview.gettext().getText().clear();
                                       inputview2.gettext().getText().clear();
                                       inputview3.gettext().getText().clear();
                                       inputview4.gettext().getText().clear();
                                       inputview5.gettext().getText().clear();
                                       progressDialog.dismiss();
                                       try {
                                           boolean goterror = response.getBoolean("error");
                                           if (!goterror){
                                               progressDialog.dismiss();
                                               Log.d("SignUp", "success"+response.toString());
                                               String msg = "Verification code is sent on your email";
                                               Snackbar.make(view, msg, Snackbar.LENGTH_LONG).setText(msg).show();
                                               Log.d("Teacher", "Register Success");
                                           }else {
                                               progressDialog.dismiss();
                                               String error_msg = response.getString("error_msg");
                                               Snackbar.make(view, error_msg, Snackbar.LENGTH_LONG).setText(error_msg).show();
                                           }
                                       } catch (JSONException e) {
                                           e.printStackTrace();
                                       }

                                   }

                                   @Override
                                   public void onError(ANError anError) {

                                   }
                               });
                   }
               },200);
            }
        }else {
            Log.d("Teacher Register", "Empty");
        }
        if (!TextUtils.isEmpty(prefrences.isNTeacher())){
            if (prefrences.isNTeacher().equals("8") && prefrences.nteacherTrack()){
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        AndroidNetworking.post(Constants.NTegisterUrl)
                                .addBodyParameter("fullname", fullName)
                                .addBodyParameter("username", username)
                                .addBodyParameter("email", usermail)
                                .addBodyParameter("password", userpass)
                                .addBodyParameter("confirmpassword", confirmpass)
                                .setTag("post_registernt")
                                .setPriority(Priority.MEDIUM)
                                .build()
                                .getAsJSONObject(new JSONObjectRequestListener() {

                                    @Override
                                    public void onResponse(JSONObject response) {
                                        inputview.gettext().getText().clear();
                                        inputview2.gettext().getText().clear();
                                        inputview3.gettext().getText().clear();
                                        inputview4.gettext().getText().clear();
                                        inputview5.gettext().getText().clear();
                                        progressDialog.show();
                                        try {
                                            boolean goterror = response.getBoolean("error");
                                            if (!goterror){
                                                progressDialog.dismiss();
                                                Log.d("SignUp", "success"+response.toString());
                                                String msg = "Verification code is sent on your email";
                                                Log.d("NTeacher", "Register Success");
                                                Snackbar.make(view, msg, Snackbar.LENGTH_LONG).setText(msg).show();
                                            }else {
                                                progressDialog.dismiss();
                                                String error_msg = response.getString("error_msg");
                                                Snackbar.make(view, error_msg, Snackbar.LENGTH_LONG).setText(error_msg).show();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }

                                    @Override
                                    public void onError(ANError anError) {

                                    }
                                });
                    }
                },200);
            }
        }else {
            Log.d("NTeacher Register", "Empty");
        }
    }
}
