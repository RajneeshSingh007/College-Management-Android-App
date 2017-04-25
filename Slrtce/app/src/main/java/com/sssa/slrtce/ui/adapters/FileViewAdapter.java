package com.sssa.slrtce.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sssa.slrtce.R;
import com.sssa.slrtce.base.BaseRecyclerViewAdapter;
import com.sssa.slrtce.data.model.FileviewModel;
import com.sssa.slrtce.misc.utils.Helper;
import com.sssa.slrtce.misc.utils.pallete;

import java.util.List;

/**
 * Created by Coolalien on 3/2/2017.
 */

public class FileViewAdapter extends BaseRecyclerViewAdapter<FileviewModel, FileViewAdapter.FileviewHolder> {


    public FileViewAdapter(@NonNull Context context, @NonNull List<FileviewModel> data) {
        super(context, data);
    }

    @Override
    public FileviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fileview_list, parent, false);
        return new FileviewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FileviewHolder holder, int position) {

        Helper.artworkLoad(getContext(), holder.fileViewArtwork, getItem(position).getUrl(), new pallete() {
            @Override
            public void palettework(Palette palette) {
                int color[] = Helper.paletteColor(getContext(), palette);
                holder.background.setBackgroundColor(color[2]);
            }
        });
        holder.fileName.setText(getItem(position).getName());

    }

    /***
     * View Holder for this adapter
     */
    public class FileviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView fileViewArtwork;
        private TextView fileName;
        private ImageButton menubutton, downloadbutton;
        private LinearLayout background;

        public FileviewHolder(View itemView) {
            super(itemView);

            fileViewArtwork = (ImageView) itemView.findViewById(R.id.fileview_Artwork);
            fileName = (TextView) itemView.findViewById(R.id.FileName);
            //menubutton = (ImageButton) itemView.findViewById(R.id.menu_button);
            downloadbutton = (ImageButton) itemView.findViewById(R.id.download_button);
            background = (LinearLayout) itemView.findViewById(R.id.background);
            itemView.setOnClickListener(this);
            itemView.findViewById(R.id.file_view).setOnClickListener(this);
            downloadbutton.setOnClickListener(this);
            fileViewArtwork.setOnClickListener(this);
            //menubutton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();
            triggerOnItemClickListener(pos, view);
        }
    }
}
