package com.example.piumi.disaster_management.question_forum;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.piumi.disaster_management.R;

import java.util.ArrayList;

/**
 * Created by Piumi on 2/24/2018.
 * This is java class to show the available admins in a list
 */

public class AdminUserList extends ArrayAdapter{
    private ArrayList<String> useremail;
    private ArrayList<String> username;
    private Activity context;

    public AdminUserList(Activity  context, ArrayList<String> useremail, ArrayList<String> username) {
        super(context, R.layout.list_structure,useremail);
        this.context = context;
        this.useremail=useremail;
        this.username= username;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View r = convertView;
        ViewHolder viewHolder=null;
        if(r==null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r=layoutInflater.inflate(R.layout.user_list_structure,null,true);
            viewHolder=new ViewHolder(r);
            r.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)r.getTag();
        }

        //set values to the elemnts in the xml file
        viewHolder.tvw1.setText(useremail.get(position));
        viewHolder.tvw2.setText(username.get(position));
        String firstletter = username.get(position).substring(0, 1);
        viewHolder.tvw3.setText(firstletter);

        return r;
    }

    //inner class is used to get the elements in the xml file
    class ViewHolder{
        TextView tvw1;
        TextView tvw2;
        TextView tvw3;

        ViewHolder(View v){
            tvw1 = v.findViewById(R.id.user_email);
            tvw2 = v.findViewById(R.id.user_name);
            tvw3 = v.findViewById(R.id.list_image);


        }
    }
}
