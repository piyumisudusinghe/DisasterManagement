package com.example.piumi.disaster_management.question_forum;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.piumi.disaster_management.R;

import java.util.ArrayList;

/**
 * Thsi class is used to show the available admins.they viewd as a list.this adapter class is used to put the items in to list
 */

public class MessageList extends ArrayAdapter {
    private ArrayList<String> msg;
    private ArrayList<String> msg_time;
    private ArrayList<String> msg_sender;
    private Activity context;
    private String mobile_user_mail;

    public MessageList ( Activity context, ArrayList<String> msg, ArrayList<String> msg_time, ArrayList<String> msg_sender, String mobile_user_mail ) {
        super ( context, R.layout.chat_structure, msg );
        this.context = context;
        this.msg = msg;
        this.msg_time = msg_time;
        this.msg_sender = msg_sender;
        this.mobile_user_mail = mobile_user_mail;

    }

    @NonNull
    @Override
    public View getView ( int position, @Nullable View convertView, @NonNull ViewGroup parent ) {
        View r = convertView;
        MessageList.ViewHolder viewHolder = null;
        if (r == null) {
            LayoutInflater layoutInflater = context.getLayoutInflater ( );
            r = layoutInflater.inflate ( R.layout.chat_structure, null, true );
            viewHolder = new ViewHolder ( r );
            r.setTag ( viewHolder );
        } else {
            viewHolder = ( MessageList.ViewHolder ) r.getTag ( );
        }


        viewHolder.tvw1.setText ( msg.get ( position ) );
        viewHolder.tvw3.setText ( msg_time.get ( position ) );
        String firstletter = msg_sender.get ( position ).substring ( 0, 1 );
        /*if(msg_sender.get(position)== mobile_user_mail){
            //viewHolder.tvw2.win
        }*/
        viewHolder.tvw2.setText ( firstletter );

        return r;
    }

    //inner class of getting elemnts of the xml file
    class ViewHolder {
        TextView tvw1;
        TextView tvw2;
        TextView tvw3;

        ViewHolder ( View v ) {
            tvw1 = v.findViewById ( R.id.user_message );
            tvw2 = v.findViewById ( R.id.list_image );
            tvw3 = v.findViewById ( R.id.time );

        }
    }
}



