package com.sssa.slrtce.ui.fragments;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sssa.slrtce.R;
import com.sssa.slrtce.base.BaseFragment;

/**
 * Created by Coolalien on 3/7/2017.
 */

public class ImageViewFragment extends BaseFragment {


    private ImageView fileviewerArtwork;
    private static String fileUrl;

    /**
     * Instance of this class
     * @return
     */
    public static ImageViewFragment getInstance(String fileurl){
        setFileUrl(fileurl);
        return new ImageViewFragment();
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_imageview;
    }

    @Override
    protected void ui(View rootview) {
        fileviewerArtwork = (ImageView) rootview.findViewById(R.id.fileviewer_Artwork);
    }

    @Override
    protected void function() {
        Glide.with(getContext())
                .load(getFileUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .fitCenter()
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .into(fileviewerArtwork);
    }

    @Override
    protected Fragment setfragment() {
        return null;
    }

    @Override
    protected int setContainerId() {
        return 0;
    }

    public static String getFileUrl() {
        return fileUrl;
    }

    public static void setFileUrl(String fileUrls) {
        fileUrl = fileUrls;
    }
}
