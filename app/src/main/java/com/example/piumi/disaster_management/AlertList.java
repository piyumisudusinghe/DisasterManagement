package com.example.piumi.disaster_management;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

public class AlertList extends ArrayAdapter {
    private ArrayList<String> alert_body;
    private String title;
    private Integer[] img_id;
    private Activity context;

    public AlertList ( Activity  context, ArrayList<String> alert_body, Integer[] img_id, String title) {
        super(context, R.layout.alertlist_structure,alert_body);
        this.context = context;
        this.alert_body = alert_body;
        this.img_id = img_id;
        this.title = title;
    }

    @NonNull
    @Override
    public View getView( int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View r = convertView;
        ViewHolder viewHolder=null;
        if(r==null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r=layoutInflater.inflate(R.layout.alertlist_structure,null,true);
            viewHolder = new ViewHolder (r);
            r.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder )r.getTag();
        }
        viewHolder.ivw.setImageResource(img_id[0]);
        viewHolder.tvw1.setText(title);
        viewHolder.tvw2.setText(alert_body.get ( position ));

        return r;
    }

    class ViewHolder{
        TextView tvw1;
        TextView tvw2;
        ImageView ivw;
        ViewHolder(View v){
            tvw1 = v.findViewById(R.id.alert_title);
            tvw2 = v.findViewById ( R.id.alert_body );
            ivw = v.findViewById(R.id.list_image_view);
        }
    }

}
