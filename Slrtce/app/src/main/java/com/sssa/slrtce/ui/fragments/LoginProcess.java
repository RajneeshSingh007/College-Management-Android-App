package com.sssa.slrtce.ui.fragments;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sssa.slrtce.R;
import com.sssa.slrtce.base.BaseFragment;
import com.sssa.slrtce.misc.utils.Extras;
import com.sssa.slrtce.ui.activities.MainActivity;

/**
 * Created by Coolalien on 2/22/2017.
 */

public class LoginProcess extends BaseFragment {

    private ImageView student,teacher,other;
    private Extras prefernces;

    /**
     * instance of this class
     * @return
     */
    public static LoginProcess getInstance(){
        return new LoginProcess();
    }

    @Override
    protected int layoutId() {
        return R.layout.login_process;
    }

    @Override
    protected void ui(View rootview) {
        student = (ImageView) rootview.findViewById(R.id.student_login);
        teacher = (ImageView) rootview.findViewById(R.id.teacher_login);
        other = (ImageView) rootview.findViewById(R.id.nonteacher_login);
    }

    @Override
    protected void function() {
        other.setOnClickListener(onClick);
        student.setOnClickListener(onClick);
        teacher.setOnClickListener(onClick);
        prefernces = new Extras(getContext());
        Glide.with(getContext())
                .load(R.drawable.reading)
                .crossFade()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .override(300,300)
                .into(student);

        Glide.with(getContext())
                .load(R.drawable.professor)
                .crossFade()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .override(300,300)
                .into(teacher);

        Glide.with(getContext())
                .load(R.drawable.other)
                .crossFade()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .override(300,300)
                .into(other);
    }

    @Override
    protected Fragment setfragment() {
        return CommonFragment.getInstance();
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
     * OnClickListener
     */
    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){

                case R.id.student_login :
                    FrgamentLoader();
                    //save prefences
                    prefernces.setStudent("0");
                    prefernces.setTeacher(null);
                    prefernces.setStudentTrack(true);
                    prefernces.setNTeacherTrack(false);
                    prefernces.setTeacherTrack(false);
                    break;

                case R.id.teacher_login:
                    FrgamentLoader();
                    //save prefences
                    prefernces.setTeacher("3");
                    prefernces.setStudentTrack(false);
                    prefernces.setNTeacherTrack(false);
                    prefernces.setTeacherTrack(true);
                    break;

                case R.id.nonteacher_login:
                    FrgamentLoader();
                    //save prefences
                    prefernces.setNTeacher("6");
                    prefernces.setStudentTrack(false);
                    prefernces.setNTeacherTrack(true);
                    prefernces.setTeacherTrack(false);
                    break;
            }
        }
    };
}
