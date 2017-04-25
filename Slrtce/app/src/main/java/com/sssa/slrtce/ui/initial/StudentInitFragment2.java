package com.sssa.slrtce.ui.initial;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sssa.slrtce.R;
import com.sssa.slrtce.base.BaseFragment;
import com.sssa.slrtce.data.model.YearBranch;
import com.sssa.slrtce.misc.utils.Extras;
import com.sssa.slrtce.misc.widgets.DividerItemDecoration;
import com.sssa.slrtce.ui.activities.MainActivity;
import com.sssa.slrtce.ui.adapters.initAdapters.studentinitAdapters;
import com.sssa.slrtce.ui.fragments.StudentFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Coolalien on 3/8/2017.
 */

public class StudentInitFragment2 extends BaseFragment {


    private TextView toolbartitle, save_title;
    private RecyclerView rv;
    private studentinitAdapters studentinitAdapters;
    private List<YearBranch> yearBranches;
    private Extras prefernces;

    /**
     * Instance of this class
     * @return
     */
    public static StudentInitFragment2 getInstance(){
        return new StudentInitFragment2();
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_studentinit;
    }

    @Override
    protected void ui(View rootview) {
        toolbartitle = (TextView) rootview.findViewById(R.id.toolbar_title);
        save_title = (TextView) rootview.findViewById(R.id.save_init);
        rv = (RecyclerView) rootview.findViewById(R.id.rv);

    }

    @Override
    protected void function() {

        toolbartitle.setText(getString(R.string.setup2));
        save_title.setText("SAVE");

        prefernces = new Extras(getContext());

        yearBranches = new ArrayList<>();

        yearBranches.add(new YearBranch("CMPN"));
        yearBranches.add(new YearBranch("IT"));
        yearBranches.add(new YearBranch("MECH"));
        yearBranches.add(new YearBranch("EXTC"));
        yearBranches.add(new YearBranch("ETRX"));
        yearBranches.add(new YearBranch("CIVIL"));

        studentinitAdapters = new studentinitAdapters(getContext(), yearBranches);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        rv.setLayoutManager(linearLayoutManager);
        rv.setHasFixedSize(true);
        rv.addItemDecoration(new DividerItemDecoration(getContext(), 75));
        rv.setAdapter(studentinitAdapters);

        save_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentLoaders();
            }
        });
    }

    @Override
    protected Fragment setfragment() {
        return null;
    }

    @Override
    protected int setContainerId() {
        return ((MainActivity) getActivity()).setContainerId();
    }

    private void fragmentLoaders(){
        if (!TextUtils.isEmpty(prefernces.getYr()) && !TextUtils.isEmpty(prefernces.getinitStudent())){
            if (prefernces.getYr().equals("Fourth Year") && prefernces.getinitStudent().equals("CMPN")){
                prefernces.setfourthYear(true);
                getFragmentManager().beginTransaction().replace(setContainerId(), StudentFragment.getInstance()).commit();
            }
        }else {
            Log.d("StudentSetup", "init. failed");
        }
    }
}
