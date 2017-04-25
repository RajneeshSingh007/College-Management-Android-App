package com.sssa.slrtce.ui.adapters;

import android.content.Context;
import android.graphics.Color;
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
import com.sssa.slrtce.data.model.Result;
import com.sssa.slrtce.misc.utils.Constants;
import com.sssa.slrtce.misc.utils.Extras;

import java.util.ArrayList;
import java.util.List;

import static com.sssa.slrtce.misc.utils.Helper.now;


/**
 * Created by Coolalien on 2/26/2017.
 */

public class SyllabusAdapter extends BaseRecyclerViewAdapter<Result, SyllabusAdapter.SyllabusViewholder> {


    private List<String> stringList = new ArrayList<>();

    public SyllabusAdapter(Context context, @NonNull List<Result> data, String tableName) {
        super(context, data, tableName);
    }

    @Override
    public SyllabusViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootview = LayoutInflater.from(parent.getContext()).inflate(R.layout.syllabus_list, parent, false);
        return new SyllabusViewholder(rootview);
    }

    @Override
    public void onBindViewHolder(final SyllabusViewholder holder, final int position) {
        final Result result = getItem(position);
        holder.syllabus_title.setText(result.getSyllabus());
        holder.syllabus_count.setText(result.getId()+". ");
        holder.syllabus_dtime.setText(now());
        holder.checkBox.setChecked(result.ischecked());
        holder.checkBox.setTag(position);
        holder.checkBox.setOnCheckedChangeListener(null);
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                result.setIschecked(b);
            }
        });
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(result.ischecked()) {
                    /*AndroidNetworking.post(Constants.SAVINGSYLLABUS)
                            .addBodyParameter("chaptername", holder.syllabus_title.getText().toString())
                            .addBodyParameter("checked", now())
                            .addBodyParameter("tablename", getTableName())
                            .setTag("saving_data")
                            .setPriority(Priority.MEDIUM)
                            .build()
                            .getAsJSONObject(new JSONObjectRequestListener() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        boolean goterror = response.getBoolean("error");
                                        if (!goterror) {
                                            Log.d("Login", "" + response); //response from json
                                            Snackbar.make(holder.itemView,"Saved In Db", Snackbar.LENGTH_LONG).setText("Saved in database").show();
                                        } else {
                                            String errorMsg = response.getString("error_msg");
                                            Log.d("syllabusAdapter", errorMsg);
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onError(ANError anError) {

                                }
                            });*/
                    stringList.add(holder.syllabus_title.getText().toString());
                    new Extras(getContext()).saveSyllabusData(stringList);
                    holder.syllabus_title.setTextColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
                } else  {
                    stringList.clear();
                    new Extras(getContext()).getSharedPreferences().edit().remove(Constants.SYLLABUS).commit();
                    holder.syllabus_title.setTextColor(Color.BLACK);
                    /*AndroidNetworking.post(Constants.UNSAVINGSYLLABUS)
                            .addBodyParameter("chaptername", holder.syllabus_title.getText().toString())
                            .addBodyParameter("checked", now())
                            .addBodyParameter("tablename", getTableName())
                            .setTag("saving_data")
                            .setPriority(Priority.MEDIUM)
                            .build()
                            .getAsJSONObject(new JSONObjectRequestListener() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        boolean goterror = response.getBoolean("error");
                                        if (!goterror) {
                                            Log.d("Login", "" + response); //response from json
                                            Snackbar.make(holder.itemView,"Saved In Db", Snackbar.LENGTH_LONG).setText("Unsaved in database").show();
                                        } else {
                                            String errorMsg = response.getString("error_msg");
                                            Log.d("syllabusAdapter", errorMsg);
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onError(ANError anError) {

                                }
                            });*/
                }
            }
        });

    }

    @Override
    public Result getItem(int position) throws ArrayIndexOutOfBoundsException {
        return super.getItem(position);
    }


    public class SyllabusViewholder extends RecyclerView.ViewHolder{

        private TextView syllabus_count, syllabus_title,syllabus_dtime;
        private CheckBox checkBox;

        public SyllabusViewholder(View itemView) {
            super(itemView);

            syllabus_count = (TextView) itemView.findViewById(R.id.syllabus_count);
            syllabus_dtime = (TextView) itemView.findViewById(R.id.syllabus_dtime);
            syllabus_title = (TextView) itemView.findViewById(R.id.syllabus_title);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
        }
    }
}
