package com.example.piumi.disaster_management.helpLine;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.piumi.disaster_management.R;

public class CallHelpLine extends AppCompatActivity {


    EditText et;
    TextView tv;
    Button call_btn;
    private  String place;
    private  String tel_num;
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_call_help_line );
        Bundle extras = getIntent ().getExtras ();

        if (extras != null) {
            place = extras.getString ("place");
            tel_num = extras.getString ( "tel_no" );

        }
        getPlace();

    }

    private void getPlace(){
        tv = (TextView)findViewById ( R.id.textView1 );
        et = ( EditText ) findViewById ( R.id.editText1 );
        et.setText ( tel_num );
        tv.setText ( place );
        call_btn = ( Button ) findViewById ( R.id.btn_call );
        call_btn.setOnClickListener ( new View.OnClickListener ( ) {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick ( View v ) {
                Intent i = new Intent ( Intent.ACTION_CALL );
                i.setData ( Uri.parse ( "tel:" + tel_num) );
                if (ActivityCompat.checkSelfPermission(CallHelpLine.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(CallHelpLine.this,"You cannot make a call",Toast.LENGTH_SHORT).show();

                    return;
                }
                startActivity(i);
            }
        });
    }
}
