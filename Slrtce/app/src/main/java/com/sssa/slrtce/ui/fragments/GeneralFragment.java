package com.sssa.slrtce.ui.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;
import com.sssa.slrtce.R;
import com.sssa.slrtce.base.BaseFragment;
import com.sssa.slrtce.base.BaseRecyclerViewAdapter;
import com.sssa.slrtce.data.model.FileviewModel;
import com.sssa.slrtce.misc.utils.Constants;
import com.sssa.slrtce.misc.utils.Helper;
import com.sssa.slrtce.misc.widgets.GridSpacingItemDecoration;
import com.sssa.slrtce.ui.adapters.FileViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Coolalien on 2/23/2017.
 */

public class GeneralFragment extends BaseFragment {

    private FloatingActionButton uploadButton;
    private RecyclerView rv;
    private FileViewAdapter fileViewAdapter;
    private List<FileviewModel> fileviewModels;
    private int ReqCode = 1;
    private static boolean torf, toolbarconfig;
    private FileviewModel fileviewModel;
    private Toolbar toolbar;
    private static final int ReqCodeImage = 2;
    /**
     * Instance of this class
     * @return
     */
    public static GeneralFragment getInstance(boolean torf, boolean toolbarconfig){
        setTorf(torf);
        setToolbarconfig(toolbarconfig);
        return new GeneralFragment();
    }

    @Override
    protected int layoutId() {
        return R.layout.common_uploadview;
    }

    @Override
    protected void ui(View rootview) {
        uploadButton = (FloatingActionButton) rootview.findViewById(R.id.upload_file);
        rv = (RecyclerView) rootview.findViewById(R.id.rv);
        toolbar = (Toolbar) rootview.findViewById(R.id.toolbar);
    }

    @Override
    protected void function() {
        fileviewModels = new ArrayList<>();
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        rv.setLayoutManager(layoutManager);
        rv.addItemDecoration(new GridSpacingItemDecoration(2, Helper.px2Dp(2, getContext()), true));
        rv.setHasFixedSize(true);
        loadData();
        fileViewAdapter = new FileViewAdapter(getContext(), fileviewModels);
        fileViewAdapter.setOnItemClickListener(onClickAdapter);
        rv.setAdapter(fileViewAdapter);
        if (!torf){
            uploadButton.setOnClickListener(onClick);
            rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    if(dy > 0){
                        uploadButton.hide();
                    } else{
                        uploadButton.show();
                    }

                    super.onScrolled(recyclerView, dx, dy);
                }
            });
        }else {
            uploadButton.setVisibility(View.GONE);
            uploadButton.hide();
        }
        if (toolbarconfig){
            toolbar.setTitle("General");
            toolbar.setVisibility(View.VISIBLE);
        }else {
            toolbar.setVisibility(View.GONE);
        }

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
     * Click Listerner
     */
    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.upload_file:
                    filepickerDialog();
                    break;
            }
        }
    };


    /**
     * Adpater Click Listerner
     */
    private BaseRecyclerViewAdapter.OnItemClickListener onClickAdapter = new BaseRecyclerViewAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(int position, View view) {
            fileviewModel = fileviewModels.get(position);

            switch (view.getId()){

                case R.id.fileview_Artwork:

                    ImageView imageView = (ImageView) view.findViewById(R.id.fileview_Artwork);
                    imageviewTransition(ImageViewFragment.getInstance(fileviewModel.getUrl()), imageView);
                    break;

                case R.id.download_button:
                    Helper.downloadFile(getContext(), fileviewModel.getName(), fileviewModel.getUrl());
                    break;

            }
        }
    };

    /**
     * Transition
     * @param fragment
     * @param imageView
     */
    private void imageviewTransition(Fragment fragment, ImageView imageView){
        ViewCompat.setTransitionName(imageView, "fileviewArtwork");
        Helper.setFragmentTransition(getActivity(),GeneralFragment.this, fragment, new Pair<View, String>(imageView,"fileviewArtwork"));
    }

    /**
     * Activity result
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ReqCode && resultCode == Activity.RESULT_OK) {
            String path = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH); //return path of selected file from user
            File file = new File(path);
            uploadData(file, path);
        }
        if (requestCode == ReqCodeImage && resultCode == Activity.RESULT_OK){
            File file = new File(Uri.parse(Helper.getImagePath()).toString());
            uploadData(file, Uri.parse(Helper.getImagePath()).toString());
        }

    }

    /**
     *
     * @param torfs
     */
    public static void setTorf(boolean torfs) {
        torf = torfs;
    }

    /**
     *
     * @param toolbarconfigs
     */
    public static void setToolbarconfig(boolean toolbarconfigs) {
        toolbarconfig = toolbarconfigs;
    }

    /**
     * Load files
     */
    private void loadData (){
        AndroidNetworking.get(Constants.FILEVIEWS2)
                .setPriority(Priority.HIGH)
                .setTag("get_fileresult")
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response.length() > 0){
                            for (int i = 0; i < response.length(); i++) {
                                fileviewModel = new FileviewModel();
                                JSONObject json;
                                try {
                                    json = response.getJSONObject(i);
                                    fileviewModel.setName(json.getString("name"));
                                    fileviewModel.setUrl(json.getString("url"));
                                    Log.d("FileView", json.getString("name"));
                                    Log.d("FileView", json.getString("url"));
                                    fileviewModels.add(fileviewModel);
                                    fileViewAdapter.addDataList(fileviewModels);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            fileViewAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    private ProgressDialog mDialog;

    /**
     * Upload File
     * @param file
     * @param path
     */
    private void uploadData (File file, String path){
        mDialog = new ProgressDialog(getContext());
        mDialog.setMax(100);
        mDialog.setMessage("Uploading " + file.getName());
        mDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mDialog.setProgress(0);
        mDialog.show();
        AndroidNetworking.upload(Constants.UPLOADFILESG)
                .addMultipartParameter("name", path)
                .addMultipartFile("file", file)
                .setPriority(Priority.HIGH)
                .setTag("uploading_file")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (response.length() > 0) {
                            try {
                                boolean error = response.getBoolean("error");
                                if (!error) {
                                    Log.d("GeneralFragment", response.toString());
                                    String name = response.optString("name").trim();
                                    Toast.makeText(getContext(), name, Toast.LENGTH_LONG).show();
                                    fileViewAdapter.notifyDataSetChanged();
                                    mDialog.dismiss();
                                } else {
                                    String error_msg = response.optString("error_msg");
                                    mDialog.dismiss();
                                    Toast.makeText(getContext(), error_msg, Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        anError.printStackTrace();
                    }
                });
    }


    private ImageView filePicker, cameraPicker;

    /**
     * Material Dialog Picker for file upload i.e. from camera or directory
     */
    private void filepickerDialog(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.filepicker_dailog, null);
        filePicker  = (ImageView) view.findViewById(R.id.file_picker);
        cameraPicker = (ImageView) view.findViewById(R.id.camera_picker);
        final MaterialDialog.Builder builder = new MaterialDialog.Builder(getContext());
        builder.title("Upload");
        builder.negativeText(android.R.string.cancel);
        builder.positiveText(android.R.string.ok);
        builder.onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                builder.autoDismiss(true);
            }
        });
        builder.customView(view,false);
        builder.show();
        cameraPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (cameraIntent.resolveActivity(getContext().getPackageManager()) != null) {
                    // Create the File where the photo should go
                    File photoFile = Helper.createImageFile();
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                        startActivityForResult(cameraIntent, ReqCodeImage);
                    }
                    builder.autoDismiss(true);
                }
            }
        });
        filePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.filePicker(ReqCode, GeneralFragment.this);
                builder.autoDismiss(true);
            }
        });
    }
}
