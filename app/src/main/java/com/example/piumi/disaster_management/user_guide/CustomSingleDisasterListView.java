package com.example.piumi.disaster_management.user_guide;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.piumi.disaster_management.R;

/**
 * Created by Piumi on 2/19/2018.
 */

public class CustomSingleDisasterListView extends ArrayAdapter {

    private String[] single_disaster_listview;
    private Activity context;
    public CustomSingleDisasterListView(Activity context, String[] single_disaster_listview) {
        super(context, R.layout.disaster_list_structure,single_disaster_listview);
        this.context = context;
        this.single_disaster_listview = single_disaster_listview;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View r = convertView;
        ViewHolder viewHolder=null;
        if(r==null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r=layoutInflater.inflate(R.layout.disaster_list_structure,null,true);
            viewHolder=new ViewHolder(r);
            r.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)r.getTag();
        }

        viewHolder.tvw1.setText(single_disaster_listview[position]);

        return r;
    }
    class ViewHolder{
        TextView tvw1;

        ViewHolder(View v){
            tvw1 = v.findViewById(R.id.unique_disaster_list);

        }
    }



}
