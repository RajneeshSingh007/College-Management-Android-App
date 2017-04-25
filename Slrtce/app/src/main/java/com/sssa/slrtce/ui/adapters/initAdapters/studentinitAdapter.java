package com.sssa.slrtce.ui.adapters.initAdapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.sssa.slrtce.R;
import com.sssa.slrtce.base.BaseRecyclerViewAdapter;
import com.sssa.slrtce.data.model.YearBranch;
import com.sssa.slrtce.misc.utils.Extras;

import java.util.List;

/**
 * Created by Coolalien on 2/27/2017.
 */

public class studentinitAdapter extends BaseRecyclerViewAdapter<YearBranch, studentinitAdapter.studentinitViewholder> {

    private static CheckBox lastChecked = null;
    private static int lastCheckedPos = 0;

    public studentinitAdapter(@NonNull Context context, @NonNull List<YearBranch> data) {
        super(context, data);
    }

    @Override
    public studentinitAdapter.studentinitViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_initsetup, parent, false);
        return new studentinitViewholder(view);
    }

    @Override
    public void onBindViewHolder(final studentinitAdapter.studentinitViewholder holder, int position) {
        holder.count.setText(position + 1 + ".");
        holder.yearbranch.setText(getItem(position).getYearbranch());
        holder.checkBox.setChecked(getItem(position).isChecked());
        holder.checkBox.setTag(position);
        if(position == 0 && getItem(0).isChecked() && holder.checkBox.isChecked()) {
            lastChecked = holder.checkBox;
            lastCheckedPos = 0;
        }
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cb = (CheckBox)v;
                int clickedPos = (Integer) cb.getTag();
                if(cb.isChecked()) {
                    if(lastChecked != null) {
                        lastChecked.setChecked(false);
                        getItem(lastCheckedPos).setIsChecked(false);
                    }
                    lastChecked = cb;
                    lastCheckedPos = clickedPos;
                    new Extras(getContext()).setyr(holder.yearbranch.getText().toString());
                } else  {
                    lastChecked = null;
                }
                getItem(clickedPos).setIsChecked(cb.isChecked());
            }
        });
    }

    @Override
    public YearBranch getItem(int position) throws ArrayIndexOutOfBoundsException {
        return super.getItem(position);
    }

    public class studentinitViewholder extends RecyclerView.ViewHolder{

        private TextView yearbranch, count;
        private CheckBox checkBox;

        public studentinitViewholder(View itemView) {
            super(itemView);

            yearbranch = (TextView) itemView.findViewById(R.id.yearbranch);
            count = (TextView) itemView.findViewById(R.id.count);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
        }
    }
}
