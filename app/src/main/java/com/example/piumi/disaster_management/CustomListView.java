package com.example.piumi.disaster_management;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Piumi on 2/18/2018.
 */
//use adapter pattern
public class CustomListView  extends ArrayAdapter{

    private String[] disastername;
    private Integer[] img_id;
    private Activity context;

    public CustomListView(Activity  context, String[] disastername, Integer[] img_id) {
        super(context, R.layout.list_structure,disastername);
        this.context = context;
        this.disastername=disastername;
        this.img_id = img_id;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View r = convertView;
        ViewHolder viewHolder=null;
        if(r==null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r=layoutInflater.inflate(R.layout.list_structure,null,true);
            viewHolder=new ViewHolder(r);
            r.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)r.getTag();
        }
        viewHolder.ivw.setImageResource(img_id[position]);
        viewHolder.tvw1.setText(disastername[position]);

        return r;
    }

     class ViewHolder{
        TextView tvw1;
        ImageView ivw;
        ViewHolder(View v){
            tvw1 = v.findViewById(R.id.disaster_name);
            ivw = v.findViewById(R.id.list_image_view);
        }
     }
}
