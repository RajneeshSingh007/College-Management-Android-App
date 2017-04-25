package com.sssa.slrtce.misc.utils;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.support.v7.graphics.Palette;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.palette.BitmapPalette;
import com.palette.GlidePalette;
import com.sssa.slrtce.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.graphics.Paint.ANTI_ALIAS_FLAG;

/**
 * Created by Coolalien on 3/2/2017.
 */

public class Helper {

    private static String imagePath;

    /**
     * Px to Dp Convertor
     * @param dp
     * @param c
     * @return
     */
    public static int px2Dp(int dp, Context c) {
        DisplayMetrics displayMetrics = c.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.ydpi / DisplayMetrics.DENSITY_DEFAULT));
    }


    /**
     * Download File onClick
     * @param Name
     * @param fileurl
     */
    public static void downloadFile(Context context,String Name, String fileurl){
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(fileurl));
        request.setTitle(Name)
                .setNotificationVisibility(0)
                .setVisibleInDownloadsUi(true)
                .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                .setDescription("Downloading in progress...")
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileurl)
                .allowScanningByMediaScanner();
        downloadManager.enqueue(request);
    }

    /**
     * Menu Work
     * @param context
     * @param view
     * @param name
     * @param url
     */
    /*public static void menuWork(final Context context, View view){
        final PopupMenu popupMenu = new PopupMenu(context, view);
        MenuInflater menuInflater = popupMenu.getMenuInflater();
        menuInflater.inflate(R.menu.menu_option, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
        popupMenu.show();
    }*/


    /**
     * File Picker
     * @param code
     * @param fragment
     */
    public static void filePicker(int code, Fragment fragment){
        new MaterialFilePicker()
                .withSupportFragment(fragment)
                .withTitle("Select file for upload")
                .withRequestCode(code)
                .withFilterDirectories(true)
                .withHiddenFiles(false)
                .start();
    }

    /**
     * store pallete color in int array
     * @param context
     * @param palette
     * @return
     */
    public static int[] paletteColor (Context context, Palette palette){
        int color[] = new int[3];
        if (palette.getDarkVibrantSwatch() != null){
            color[0] = palette.getDarkVibrantSwatch().getTitleTextColor();
            color[1] = palette.getDarkVibrantSwatch().getBodyTextColor();
            color[2] = palette.getDarkVibrantSwatch().getRgb();
        }else if (palette.getDarkMutedSwatch() != null){
            color[0] = palette.getDarkMutedSwatch().getTitleTextColor();
            color[1] = palette.getDarkMutedSwatch().getBodyTextColor();
            color[2] = palette.getDarkMutedSwatch().getRgb();
        }else if (palette.getVibrantSwatch() != null){
            color[0] = palette.getVibrantSwatch().getTitleTextColor();
            color[1] = palette.getVibrantSwatch().getBodyTextColor();
            color[2] = palette.getVibrantSwatch().getRgb();
        }else if (palette.getDarkMutedSwatch() != null){
            color[0] = palette.getDarkMutedSwatch().getTitleTextColor();
            color[1] = palette.getDarkMutedSwatch().getBodyTextColor();
            color[2] = palette.getDarkMutedSwatch().getRgb();
        }else {
            color[0] = ContextCompat.getColor(context, android.R.color.black);
            color[1] = ContextCompat.getColor(context, android.R.color.black);
            color[2] = ContextCompat.getColor(context, R.color.colorAccent);
        }
        return color;
    }

    /**
     * Load Image using glide
     * @param context
     * @param imageView
     * @param path
     * @param pallete
     */
    public static void artworkLoad(Context context, ImageView imageView, String path, final pallete pallete){
        Glide.with(context)
                .load(path)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .format(DecodeFormat.PREFER_ARGB_8888)
                .override(300,300)
                .listener(GlidePalette.with(path).intoCallBack(new BitmapPalette.CallBack() {
                    @Override
                    public void onPaletteLoaded(@Nullable Palette palette) {
                        pallete.palettework(palette);
                    }
                }))
                .into(imageView);
    }


    /**
     *  Shared Transition between images via fragment
     *
     * @param activity
     * @param firstFragment
     * @param secondFragment
     * @param transitionViews
     */
    @SafeVarargs
    @SuppressLint("NewApi")
    public static void setFragmentTransition(FragmentActivity activity, Fragment firstFragment, Fragment secondFragment, @Nullable Pair<View, String>... transitionViews) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            // Inflate transitions to apply
            Transition changeTransform = TransitionInflater.from(activity).inflateTransition(R.transition.change_image_transform);
            Transition explodeTransform = TransitionInflater.from(activity).inflateTransition(R.transition.change_image_transform);
            // Setup exit transition on first fragment
            firstFragment.setSharedElementReturnTransition(changeTransform);
            firstFragment.setExitTransition(explodeTransform);
            // Setup enter transition on second fragment
            secondFragment.setSharedElementEnterTransition(changeTransform);
            secondFragment.setEnterTransition(explodeTransform);
        }
        FragmentTransaction ft = activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, secondFragment)
                .addToBackStack("transaction");
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && transitionViews != null) {

            for(Pair<View, String> tr : transitionViews) {
                ft.addSharedElement(tr.first, tr.second);
            }
        }
        ft.commit();
    }


    /**
     * Create Image File and save in storage when user click by camera
     * @return
     */
    public static File createImageFile(){
        // Create an image file name
        String imageFileName = "JPEG_" + now() + "_";
        File folder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/slrtce/"); //location
        File image = null;
        try {
            if (!folder.exists()){
                folder.mkdirs();
            }
            image = File.createTempFile(
                    imageFileName,  // prefix
                    ".jpg",         // suffix
                    folder      // directory
            );
            setImagePath(image.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    /**
     * Date format also return current date & time
     * @return
     */
    public static String now() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }

    /**
     * Set imagepaths
     * @param imagePaths
     */
    private static void setImagePath(String imagePaths) {
        imagePath = imagePaths;
    }

    /**
     * Get ImagePath
     * @return
     */
    public static String getImagePath() {
        return imagePath;
    }


    /**
     * Convert text to bitmap
     * @param text
     * @param textSize
     * @param textColor
     * @return
     */
    public static Bitmap textAsBitmap(String text, float textSize, int textColor) {
        Paint paint = new Paint(ANTI_ALIAS_FLAG);
        paint.setTextSize(textSize); //text size
        paint.setColor(textColor); //text color
        paint.setTextAlign(Paint.Align.LEFT); //align center
        float baseline = -paint.ascent(); // ascent() is negative
        int width = (int) (paint.measureText(text) + 0.0f); // round
        int height = (int) (baseline + paint.descent() + 0.0f);
        Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(image);
        canvas.drawText(text, 0, baseline, paint); //draw text
        return image;
    }

}
