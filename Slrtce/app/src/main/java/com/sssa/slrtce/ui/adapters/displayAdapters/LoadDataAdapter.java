package com.sssa.slrtce.ui.adapters.displayAdapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sssa.slrtce.R;
import com.sssa.slrtce.base.BaseRecyclerViewAdapter;
import com.sssa.slrtce.data.model.Result;

import java.util.List;

/**
 * Created by Coolalien on 2/27/2017.
 */

public class LoadDataAdapter extends BaseRecyclerViewAdapter<Result, LoadDataAdapter.loadViewholder> {

    public LoadDataAdapter(@NonNull Context context, @NonNull List<Result> data) {
        super(context, data);
    }

    @Override
    public LoadDataAdapter.loadViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootview = LayoutInflater.from(parent.getContext()).inflate(R.layout.loaddata_list, parent, false);
        return new loadViewholder(rootview);
    }

    @Override
    public void onBindViewHolder(LoadDataAdapter.loadViewholder holder, int position) {
        holder.syllabus_dtime.setText(getItem(position).getChecked());
        holder.syllabus_count.setText(getItem(position).getId());
        holder.syllabus_title.setText(getItem(position).getSyllabus());
    }

    public class loadViewholder extends RecyclerView.ViewHolder {

        private TextView syllabus_count, syllabus_title, syllabus_dtime;

        public loadViewholder(View itemView) {
            super(itemView);

            syllabus_count = (TextView) itemView.findViewById(R.id.syllabus_count);
            syllabus_title = (TextView) itemView.findViewById(R.id.syllabus_title);
            syllabus_dtime = (TextView) itemView.findViewById(R.id.syllabus_dtime);
        }
    }
}
