package com.example.piumi.disaster_management.helpLine;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.piumi.disaster_management.R;

import java.util.ArrayList;

public class CallActivity extends ArrayAdapter{
    private ArrayList<String> helpcenter_name;
    private ArrayList<String> tel_nos;
    private Integer[] img_id;
    private Activity context;

    public CallActivity(Activity  context, ArrayList<String> helpcenter_names, Integer[] img_id,ArrayList<String> tel_nos) {
        super(context, R.layout.helpline_list_structure,helpcenter_names);
        this.context = context;
        this.helpcenter_name=helpcenter_names;
        this.img_id = img_id;
        this.tel_nos = tel_nos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View r = convertView;
        ViewHolder viewHolder=null;
        if(r==null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r=layoutInflater.inflate(R.layout.helpline_list_structure,null,true);
            viewHolder=new ViewHolder(r);
            r.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)r.getTag();
        }
        viewHolder.ivw.setImageResource(img_id[0]);
        viewHolder.tvw1.setText(helpcenter_name.get ( position ));
        viewHolder.tvw2.setText(tel_nos.get ( position ));

        return r;
    }

    class ViewHolder{
        TextView tvw1;
        TextView tvw2;
        ImageView ivw;
        ViewHolder(View v){
            tvw1 = v.findViewById(R.id.helpcenter_location);
            tvw2 = v.findViewById ( R.id.tel_no );
            ivw = v.findViewById(R.id.list_image_view);
        }
    }

}
