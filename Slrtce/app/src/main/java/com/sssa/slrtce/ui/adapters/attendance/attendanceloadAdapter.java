package com.sssa.slrtce.ui.adapters.attendance;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.sssa.slrtce.R;
import com.sssa.slrtce.base.BaseRecyclerViewAdapter;
import com.sssa.slrtce.data.model.AttendanceModel;
import com.sssa.slrtce.misc.utils.Extras;

import java.util.ArrayList;
import java.util.List;

import static com.sssa.slrtce.misc.utils.Constants.ATDATA;

/**
 * Created by coolalien on 18,March,2017
 */

public class attendanceloadAdapter extends BaseRecyclerViewAdapter<AttendanceModel, attendanceloadAdapter.attendanceloadViewholder> {


    private List<Integer> integerList = new ArrayList<>();

    public attendanceloadAdapter(Context context, @NonNull List<AttendanceModel> data) {
        super(context, data);
    }

    @Override
    public attendanceloadViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.attendance_list, parent, false);
        return new attendanceloadViewholder(view);
    }

    @Override
    public void onBindViewHolder(final attendanceloadViewholder holder, final int position) {
        final AttendanceModel attendanceModel = getItem(position);
        holder.rollno.setText(attendanceModel.getAtrollno());
        holder.checkBox.setChecked(attendanceModel.isChecked());
        holder.checkBox.setTag(position);
        holder.checkBox.setOnCheckedChangeListener(null);
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
               attendanceModel.setChecked(b);
            }
        });
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(attendanceModel.isChecked()) {
                    integerList.add((int) holder.checkBox.getTag() + 1);
                    new Extras(getContext()).saveatData(integerList);
                    holder.rollno.setTextColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
                } else  {
                    integerList.clear();
                    new Extras(getContext()).getSharedPreferences().edit().remove(ATDATA).commit();
                    holder.rollno.setTextColor(ContextCompat.getColor(getContext(), android.R.color.black));
                }
            }
        });
    }



    public class attendanceloadViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView rollno;
        private CheckBox checkBox;

        public attendanceloadViewholder(View itemView) {
            super(itemView);
            rollno = (TextView) itemView.findViewById(R.id.rollno);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
        }

        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();
            triggerOnItemClickListener(pos, view);
        }
    }

}