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
 * Created by Coolalien on 3/2/2017.
 */

public class ForgotPass {

    /**
     * Default Constructor
     */
    public ForgotPass() {

    }

    /**
     * Forgot password
     * @param contexts
     * @param view
     * @param username
     * @param userid
     * @param userpass
     * @param confirmpass
     * @param inputview
     * @param inputview2
     * @param inputview3
     * @param inputview4
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void ForgotPassAcc(final Context contexts, final View view, final String username, final String userid, final String userpass, final String confirmpass, final inputview inputview, final inputview inputview2, final inputview inputview3, final  inputview inputview4) {
        Extras prefernces = new Extras(contexts);
        final ProgressDialog progressDialog = new ProgressDialog(contexts);
        progressDialog.setProgressStyle(android.R.style.Widget_Material_ProgressBar);
        progressDialog.setIndeterminate(true);
        progressDialog.setTitle("Reset password");
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        if (!TextUtils.isEmpty(prefernces.isStudent())){
            if (prefernces.isStudent().equals("9") && prefernces.studentTrack()) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        AndroidNetworking.post(Constants.FORGOTPASS)
                                .addBodyParameter("username", username)
                                .addBodyParameter("grno", userid)
                                .addBodyParameter("password", userpass)
                                .addBodyParameter("confirmpassword", confirmpass)
                                .setPriority(Priority.MEDIUM)
                                .setTag("forgot_passStudent")
                                .build()
                                .getAsJSONObject(new JSONObjectRequestListener() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        inputview.gettext().getText().clear();
                                        inputview2.gettext().getText().clear();
                                        inputview3.gettext().getText().clear();
                                        inputview4.gettext().getText().clear();
                                        try {
                                            boolean goterror = response.getBoolean("error");
                                            JSONObject jsonObject = response.optJSONObject("user");
                                            if (!goterror) {
                                                Log.d("Forgot", "" + response);
                                                String confirm = jsonObject.optString("success");
                                                Log.d("ForgotPass",confirm);
                                                progressDialog.dismiss();
                                                Snackbar.make(view, confirm, Snackbar.LENGTH_LONG).setText(confirm).show();
                                            } else {
                                                String errorMsg = response.optString("error_msg");
                                                progressDialog.dismiss();
                                                Snackbar.make(view, errorMsg, Snackbar.LENGTH_LONG).setText(errorMsg).show();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void onError(ANError anError) {
                                        Log.d("ForgotPass", "Failed");
                                        anError.printStackTrace();
                                    }
                                });
                    }
                }, 500);
            }
            prefernces.setStudent("0");
        }else {
            Log.d("ForgotPass", "failed_Student");
        }
        if (!TextUtils.isEmpty(prefernces.isTeacher())){
            if (prefernces.isTeacher().equals("10") && prefernces.teacherTrack()) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        AndroidNetworking.post(Constants.FORGOTPASST)
                                .addBodyParameter("username", username)
                                .addBodyParameter("teacherid", userid)
                                .addBodyParameter("password", userpass)
                                .addBodyParameter("confirmpassword", confirmpass)
                                .setPriority(Priority.MEDIUM)
                                .setTag("forgot_passStudent")
                                .build()
                                .getAsJSONObject(new JSONObjectRequestListener() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        inputview.gettext().getText().clear();
                                        inputview2.gettext().getText().clear();
                                        inputview3.gettext().getText().clear();
                                        inputview4.gettext().getText().clear();
                                        try {
                                            boolean goterror = response.getBoolean("error");
                                            if (!goterror) {
                                                Log.d("Forgot", "" + response);
                                                String confirm = response.getString("password");
                                                progressDialog.dismiss();
                                                Snackbar.make(view, confirm, Snackbar.LENGTH_LONG).setText(confirm).show();
                                            } else {
                                                String errorMsg = response.getString("error_msg");
                                                progressDialog.dismiss();
                                                Snackbar.make(view, errorMsg, Snackbar.LENGTH_LONG).setText(errorMsg).show();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void onError(ANError anError) {
                                        Log.d("ForgotPass", "Failed");
                                        anError.printStackTrace();
                                    }
                                });
                    }
                }, 500);
            }
            prefernces.setTeacher("3");
        }else {
            Log.d("ForgotPass", "failed_Teacher");
        }
        if (!TextUtils.isEmpty(prefernces.isNTeacher())){
            if (prefernces.isNTeacher().equals("11") && prefernces.nteacherTrack()) {
               new Handler().postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       AndroidNetworking.post(Constants.FORGOTPASSNT)
                               .addBodyParameter("username", username)
                               .addBodyParameter("nonteachid", userid)
                               .addBodyParameter("password", userpass)
                               .addBodyParameter("confirmpassword", confirmpass)
                               .setPriority(Priority.MEDIUM)
                               .setTag("forgot_passStudent")
                               .build()
                               .getAsJSONObject(new JSONObjectRequestListener() {
                                   @Override
                                   public void onResponse(JSONObject response) {
                                       inputview.gettext().getText().clear();
                                       inputview2.gettext().getText().clear();
                                       inputview3.gettext().getText().clear();
                                       inputview4.gettext().getText().clear();
                                       try {
                                           boolean goterror = response.getBoolean("error");
                                           if (!goterror) {
                                               Log.d("Forgot", "" + response);
                                               String confirm = response.getString("password");
                                               progressDialog.dismiss();
                                               Snackbar.make(view, confirm, Snackbar.LENGTH_LONG).setText(confirm).show();
                                           } else {
                                               String errorMsg = response.getString("error_msg");
                                               progressDialog.dismiss();
                                               Snackbar.make(view, errorMsg, Snackbar.LENGTH_LONG).setText(errorMsg).show();
                                           }
                                       } catch (JSONException e) {
                                           e.printStackTrace();
                                       }
                                   }

                                   @Override
                                   public void onError(ANError anError) {
                                       Log.d("ForgotPass", "Failed");
                                       anError.printStackTrace();
                                   }
                               });
                   }
               }, 500);
            }
            prefernces.setNTeacher("6");
        }else {
            Log.d("ForgotPass", "failed_NTeacher");
        }
    }
}
