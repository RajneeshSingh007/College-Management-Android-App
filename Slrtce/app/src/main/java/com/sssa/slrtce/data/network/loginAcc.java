package com.sssa.slrtce.data.network;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.sssa.slrtce.misc.utils.Constants;
import com.sssa.slrtce.misc.utils.Extras;
import com.sssa.slrtce.ui.activities.MainActivity;
import com.sssa.slrtce.ui.fragments.NonTeachFragment;
import com.sssa.slrtce.ui.fragments.StudentFragment;
import com.sssa.slrtce.ui.fragments.TeacherFragment;
import com.sssa.slrtce.ui.initial.StudentInitFragment;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Coolalien on 2/22/2017.
 */

public class loginAcc {

    /**
     * Default constructor
     */
    public loginAcc() {

    }

    /**
     * Login userAccount
     *
     * @param view
     * @param context
     * @param username
     * @param userpass
     * @param userid
     * @param inputview
     * @param inputview2
     * @param inputview3
     * @param extras
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void LoginAccount(final Context contexts, final View view, final Fragment context, final String username, final String userpass, final String userid, final inputview inputview, final inputview inputview2, final inputview inputview3, final extras extras) {
        final Extras prefernces = new Extras(contexts);
        final ProgressDialog progressDialog = new ProgressDialog(contexts);
        progressDialog.setProgressStyle(android.R.style.Widget_Material_ProgressBar);
        progressDialog.setIndeterminate(true);
        progressDialog.setTitle("Authenication");
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        if (!TextUtils.isEmpty(prefernces.isStudent())) {
            if ((prefernces.isStudent().equals("0") || prefernces.isStudent().equals("2")) && prefernces.studentTrack()) {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        AndroidNetworking.post(Constants.SLoginUrl)
                                .addBodyParameter("username", username)
                                .addBodyParameter("password", userpass)
                                .addBodyParameter("grno", userid)
                                .setTag("post_login")
                                .setPriority(Priority.MEDIUM)
                                .build()
                                .getAsJSONObject(new JSONObjectRequestListener() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        inputview.gettext().getText().clear();
                                        inputview2.gettext().getText().clear();
                                        inputview3.gettext().getText().clear();
                                        progressDialog.dismiss();
                                        try {
                                            boolean goterror = response.getBoolean("error");
                                            JSONObject jsonObject = response.optJSONObject("user");
                                            if (!goterror) {
                                                Log.d("Login", "" + response);
                                                Log.d("Logged as :", jsonObject.optString("username"));//response from json
                                                Toast.makeText(context.getActivity(), "Logged In", Toast.LENGTH_LONG).show();
                                                String username = jsonObject.optString("username");
                                                extras.getExtras().setuserLogginSession(username, true);
                                                int containerId = ((MainActivity) context.getActivity()).setContainerId(); // container id;
                                                int count = prefernces.getfirstScreen();

                                                if (count == 0) {
                                                    context.getFragmentManager().beginTransaction().replace(containerId, StudentInitFragment.getInstance()).addToBackStack(null).commit();
                                                    count++;
                                                    prefernces.putinitScreen(count);
                                                } else {
                                                    context.getFragmentManager().beginTransaction().replace(containerId, StudentFragment.getInstance()).addToBackStack(null).commit();
                                                }
                                            } else {
                                                progressDialog.dismiss();
                                                String errorMsg = response.getString("error_msg");
                                                Snackbar.make(view, errorMsg, Snackbar.LENGTH_LONG).setText(errorMsg).show();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void onError(ANError anError) {
                                        Log.d("Login", "Failed");
                                        anError.printStackTrace();
                                    }
                                });
                    }
                }, 200);
                prefernces.setStudent("1");
                Log.d("Student", "LoginPage");
            }
        } else {
            Log.d("Student", "empty Student");
        }
        if (!TextUtils.isEmpty(prefernces.isTeacher())) {
            if ((prefernces.isTeacher().equals("3") || prefernces.isTeacher().equals("5")) && prefernces.teacherTrack()) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        AndroidNetworking.post(Constants.TLoginUrl)
                                .addBodyParameter("username", username)
                                .addBodyParameter("password", userpass)
                                .addBodyParameter("teacherid", userid)
                                .setTag("post_login")
                                .setPriority(Priority.MEDIUM)
                                .build()
                                .getAsJSONObject(new JSONObjectRequestListener() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        inputview.gettext().getText().clear();
                                        inputview2.gettext().getText().clear();
                                        inputview3.gettext().getText().clear();
                                        progressDialog.dismiss();
                                        try {
                                            boolean goterror = response.getBoolean("error");
                                            JSONObject jsonObject = response.optJSONObject("user");
                                            if (!goterror) {
                                                Log.d("Login", "" + response); //response from json
                                                Toast.makeText(context.getActivity(), "Logged In", Toast.LENGTH_LONG).show();
                                                Log.d("Logged as :", jsonObject.optString("username"));//response from json
                                                String username = jsonObject.optString("username");
                                                extras.getExtras().setuserLogginSession(username, true);
                                                int id = ((MainActivity) context.getActivity()).setContainerId();
                                                context.getFragmentManager().beginTransaction().replace(id, TeacherFragment.getInstance()).addToBackStack(null).commit();
                                            } else {
                                                progressDialog.dismiss();
                                                String errorMsg = response.getString("error_msg");
                                                Snackbar.make(view, errorMsg, Snackbar.LENGTH_LONG).setText(errorMsg).show();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void onError(ANError anError) {
                                        Log.d("Login", "Failed");
                                        anError.printStackTrace();
                                    }
                                });
                    }
                }, 200);
                prefernces.setTeacher("4");
                Log.d("Teacher", "LoginPage");
            }
        } else {
            Log.d("Teacher", "empty teacher");
        }
        if (!TextUtils.isEmpty(prefernces.isNTeacher())) {
            if ((prefernces.isNTeacher().equals("6") || prefernces.isNTeacher().equals("8")) && prefernces.nteacherTrack()) {
               new Handler().postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       AndroidNetworking.post(Constants.NTLoginUrl)
                               .addBodyParameter("username", username)
                               .addBodyParameter("password", userpass)
                               .addBodyParameter("nonteachid", userid)
                               .setTag("post_loginn")
                               .setPriority(Priority.MEDIUM)
                               .build()
                               .getAsJSONObject(new JSONObjectRequestListener() {
                                   @Override
                                   public void onResponse(JSONObject response) {
                                       inputview.gettext().getText().clear();
                                       inputview2.gettext().getText().clear();
                                       inputview3.gettext().getText().clear();
                                       progressDialog.dismiss();
                                       try {
                                           boolean goterror = response.getBoolean("error");
                                           JSONObject jsonObject = response.optJSONObject("user");
                                           if (!goterror) {
                                               Log.d("Login", "" + response); //response from json
                                               Toast.makeText(context.getActivity(), "Logged In", Toast.LENGTH_LONG).show();
                                               Log.d("Logged as :", jsonObject.optString("username"));//response from json
                                               String username = jsonObject.optString("username");
                                               extras.getExtras().setuserLogginSession(username, true);
                                               int id = ((MainActivity) context.getActivity()).setContainerId();
                                               context.getFragmentManager().beginTransaction().replace(id, NonTeachFragment.getInstance()).addToBackStack(null).commit();
                                           } else {
                                               progressDialog.dismiss();
                                               String errorMsg = response.getString("error_msg");
                                               Snackbar.make(view, errorMsg, Snackbar.LENGTH_LONG).setText(errorMsg).show();
                                           }
                                       } catch (JSONException e) {
                                           e.printStackTrace();
                                       }
                                   }

                                   @Override
                                   public void onError(ANError anError) {
                                       Log.d("Login", "Failed");
                                       anError.printStackTrace();
                                   }
                               });
                   }
               }, 200);
                prefernces.setNTeacher("7");
                Log.d("NTeacher", "LoginPage");
            }
        } else {
            Log.d("NTeacher", "empty NTeacher");
        }


    }
}
