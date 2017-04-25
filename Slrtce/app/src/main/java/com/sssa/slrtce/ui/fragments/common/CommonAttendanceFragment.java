package com.sssa.slrtce.ui.fragments.common;

import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.sssa.slrtce.R;
import com.sssa.slrtce.base.BaseFragment;
import com.sssa.slrtce.data.model.AttendanceModel;
import com.sssa.slrtce.misc.utils.Constants;
import com.sssa.slrtce.misc.utils.Extras;
import com.sssa.slrtce.misc.utils.Helper;
import com.sssa.slrtce.ui.activities.MainActivity;
import com.sssa.slrtce.ui.adapters.attendance.attendanceloadAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by coolalien on 18,March,2017
 */

public class CommonAttendanceFragment extends BaseFragment{

    private RecyclerView rv;
    private attendanceloadAdapter attendanceAdapter;
    private Toolbar toolbar;
    private List<AttendanceModel> attendanceModels;
    private AttendanceModel attendanceModel;
    private Extras prefrences;
    private FloatingActionButton saveAtButton;

    /**
     * Instance of this clas
     * @return
     */
    public static CommonAttendanceFragment getInstance(){
        return new CommonAttendanceFragment();
    }

    @Override
    protected int layoutId() {
        return R.layout.common_rv;
    }

    @Override
    protected void ui(View rootview) {
        rv = (RecyclerView) rootview.findViewById(R.id.rv);
        toolbar = (Toolbar) rootview.findViewById(R.id.toolbar);
        saveAtButton = (FloatingActionButton) rootview.findViewById(R.id.save_at);
    }

    @Override
    protected void function() {
        prefrences = new Extras(getContext());
        saveAtButton.setVisibility(View.VISIBLE);
        toolbar.setTitle("Attendance");
        attendanceModels = new ArrayList<>();
        toolbar.showOverflowMenu();
        for (int i=1; i<=50; i++){
            attendanceModel = new AttendanceModel(String.valueOf(i));
            attendanceModel.setAtrollno(String.valueOf(i));
            attendanceModels.add(attendanceModel);
            attendanceAdapter = new attendanceloadAdapter(getContext(), attendanceModels);
            rv.setHasFixedSize(true);
            rv.setLayoutManager(new GridLayoutManager(getContext(), 4, GridLayoutManager.VERTICAL, false));
            rv.setAdapter(attendanceAdapter);
        }
        setHasOptionsMenu(true);
        MainActivity mainActivity = ((MainActivity) getActivity());
        if (toolbar != null) {
            mainActivity.setSupportActionBar(toolbar);
            if (mainActivity.getSupportActionBar() != null) {
                mainActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                mainActivity.getSupportActionBar().setHomeButtonEnabled(true);
                mainActivity.getSupportActionBar().setDisplayShowHomeEnabled(true);
            }
        }
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy >0){
                    saveAtButton.hide();
                }else {
                    saveAtButton.show();
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        saveAtButton.setImageBitmap(Helper.textAsBitmap("Save", 16, Color.WHITE));
        saveAtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefrences.saveCounter(prefrences.getCounter() +1);
                for (int rollno : prefrences.getatData()){
                    AndroidNetworking.post(Constants.TAKEATTENDANCE)
                            .addBodyParameter("tablename", prefrences.getsubjectName())
                            .addBodyParameter("atrollno", String.valueOf(rollno))
                            .addBodyParameter("atdate",    Helper.now())
                            .addBodyParameter("atstatus", "present")
                            .setTag("saving_data")
                            .setPriority(Priority.MEDIUM)
                            .build()
                            .getAsJSONObject(new JSONObjectRequestListener() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        boolean goterror = response.getBoolean("error");
                                        if (!goterror) {
                                            Log.d("Saving Attendance", "" + response); //response from json
                                            Snackbar.make(rv,"Saved In Db", Snackbar.LENGTH_LONG).setText("Saved in database").show();
                                        } else {
                                            String errorMsg = response.getString("error_msg");
                                            Log.d("attendanceAdapter", errorMsg);
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
            }
        });
    }

    @Override
    protected Fragment setfragment() {
        return CommonViewAttendanceFragment.getInstance();
    }

    @Override
    protected int setContainerId() {
        return ((MainActivity) getActivity()).setContainerId();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.attendance_view, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.attendance_view:
                FrgamentLoader();
                break;
        }
        return true;
    }

}
