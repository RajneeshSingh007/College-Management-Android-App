package com.sssa.slrtce.ui.fragments.common;

import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
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
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.sssa.slrtce.R;
import com.sssa.slrtce.base.BaseFragment;
import com.sssa.slrtce.data.model.Result;
import com.sssa.slrtce.misc.utils.Constants;
import com.sssa.slrtce.misc.utils.Extras;
import com.sssa.slrtce.misc.utils.Helper;
import com.sssa.slrtce.misc.widgets.DividerItemDecoration;
import com.sssa.slrtce.ui.activities.MainActivity;
import com.sssa.slrtce.ui.adapters.SyllabusAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.sssa.slrtce.misc.utils.Helper.now;

/**
 * Created by Coolalien on 3/8/2017.
 */

public class CommonSyllabusLoaderFragment extends BaseFragment {

    private Toolbar toolbar;
    private RecyclerView rv;
    private SyllabusAdapter syllabusAdapter;
    private List<Result> resultList;
    private static String toolbarTitle;
    private static String tableName;
    private Extras prefernces;
    private FloatingActionButton saveButton;

    /**
     * Instance of this class
     */
    public static CommonSyllabusLoaderFragment getInstance(String toolbarTitle, String tableName){
        setToolbarTitle(toolbarTitle);
        setTableName(tableName);
        return new CommonSyllabusLoaderFragment();
    }

    @Override
    protected int layoutId() {
        return R.layout.common_rv;
    }

    @Override
    protected void ui(View rootview) {
        toolbar = (Toolbar) rootview.findViewById(R.id.toolbar);
        rv = (RecyclerView) rootview.findViewById(R.id.rv);
        saveButton = (FloatingActionButton) rootview.findViewById(R.id.save_at);
    }

    @Override
    protected void function() {
        prefernces = new Extras(getContext());
        toolbar.setTitle(getToolbarTitle());
        toolbar.showOverflowMenu();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        rv.setLayoutManager(linearLayoutManager);
        rv.addItemDecoration(new DividerItemDecoration(getContext(), 75));
        rv.setHasFixedSize(true);
        loadData();
        resultList = new ArrayList<>();
        syllabusAdapter = new SyllabusAdapter(getContext(), resultList, getTableName());
        rv.setAdapter(syllabusAdapter);
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
        saveButton.setVisibility(View.VISIBLE);
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy >0){
                    saveButton.hide();
                }else {
                    saveButton.show();
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        saveButton.setImageBitmap(Helper.textAsBitmap("Save", 16, Color.WHITE));
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (String chaptername : prefernces.getSyllabusData()){
                    AndroidNetworking.post(Constants.SAVINGSYLLABUS)
                            .addBodyParameter("chaptername", chaptername)
                            .addBodyParameter("checked", now())
                            .addBodyParameter("tablename", getTableName())
                            .setTag("saving_data")
                            .setPriority(Priority.MEDIUM)
                            .build()
                            .getAsJSONObject(new JSONObjectRequestListener() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        boolean goterror = response.getBoolean("error");
                                        if (!goterror) {
                                            Log.d("Login", "" + response); //response from json
                                            Snackbar.make(rv,"Saved In Db", Snackbar.LENGTH_LONG).setText("Saved in database").show();
                                        } else {
                                            String errorMsg = response.getString("error_msg");
                                            Log.d("syllabusAdapter", errorMsg);
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
        return CommonAttendanceFragment.getInstance();
    }

    @Override
    protected int setContainerId() {
        return ((MainActivity) getActivity()).setContainerId();
    }


    /**
     * Load data
     */
    private void loadData(){
        AndroidNetworking.post(Constants.SYLLABUSDATA)
                .addBodyParameter("tablename", getTableName())
                .setPriority(Priority.HIGH)
                .setTag("get_result")
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response.length() > 0){
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject json;
                                Result result = new Result();
                                try {
                                    json = response.getJSONObject(i);
                                    result.setSyllabus(json.getString("chaptername"));
                                    result.setId(json.getString("id"));
                                    Log.d("seoopm", json.getString("id") +" "+ json.getString("chaptername"));
                                    resultList.add(result);
                                    syllabusAdapter.addDataList(resultList);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            syllabusAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });

    }

    public static void setToolbarTitle(String toolbarTitles) {
        toolbarTitle = toolbarTitles;
    }

    public static String getToolbarTitle() {
        return toolbarTitle;
    }

    public static String getTableName() {
        return tableName;
    }

    public static void setTableName(String tableNames) {
        tableName = tableNames;
    }


    @Override
    public void FrgamentLoader() {
        super.FrgamentLoader();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.attendance, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.attendance:
                FrgamentLoader();
                break;
        }
        return true;
    }
}
