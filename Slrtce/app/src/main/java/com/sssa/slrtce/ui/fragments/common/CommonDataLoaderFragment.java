package com.sssa.slrtce.ui.fragments.common;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.sssa.slrtce.R;
import com.sssa.slrtce.base.BaseFragment;
import com.sssa.slrtce.data.model.Result;
import com.sssa.slrtce.misc.utils.Constants;
import com.sssa.slrtce.misc.widgets.DividerItemDecoration;
import com.sssa.slrtce.ui.adapters.displayAdapters.LoadDataAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Coolalien on 3/8/2017.
 */

public class CommonDataLoaderFragment extends BaseFragment {

    private Toolbar toolbar;
    private RecyclerView rv;
    private List<Result> resultList;
    private LoadDataAdapter loadDataAdapter;
    private static String tableName;

    /**
     * Instance of this class
     * @return
     */
    public static CommonDataLoaderFragment getInstance(String tableName){
        setTableName(tableName);
        return new CommonDataLoaderFragment();
    }

    @Override
    protected int layoutId() {
        return R.layout.common_rv;
    }

    @Override
    protected void ui(View rootview) {
        toolbar = (Toolbar) rootview.findViewById(R.id.toolbar);
        rv = (RecyclerView) rootview.findViewById(R.id.rv);
    }

    @Override
    protected void function() {
        toolbar.setTitle("Syllabus Updates");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        rv.setLayoutManager(linearLayoutManager);
        rv.addItemDecoration(new DividerItemDecoration(getContext(), 75));
        rv.setHasFixedSize(true);
        loadSyllabusData();
        resultList = new ArrayList<>();
        loadDataAdapter = new LoadDataAdapter(getContext(), resultList);
        rv.setAdapter(loadDataAdapter);
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
     * Load syllabus Data
     */
    private void loadSyllabusData(){
        AndroidNetworking.post(Constants.LOADSYLLABUS)
                .addBodyParameter("tablename", getTableName())
                .setPriority(Priority.HIGH)
                .setTag("get_results")
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response.length() > 0){
                            for (int i = 0; i < response.length(); i++) {
                                Result result = new Result();
                                JSONObject json;
                                try {
                                    json = response.getJSONObject(i);
                                    result.setSyllabus(json.getString("chaptername"));
                                    result.setId(String.valueOf(i+1));
                                    result.setChecked(json.getString("checked"));
                                    Log.d("fespa", json.getString("id") +" "+ json.getString("chaptername"));
                                    resultList.add(result);
                                    loadDataAdapter.addDataList(resultList);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            loadDataAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });

    }

    public static String getTableName() {
        return tableName;
    }

    public static void setTableName(String tableNames) {
        tableName = tableNames;
    }

}
