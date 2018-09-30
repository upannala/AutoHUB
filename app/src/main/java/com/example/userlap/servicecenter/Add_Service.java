package com.example.userlap.servicecenter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add_Service extends AppCompatActivity {
    DatabaseHelper mydb;
    EditText AddName;
    EditText AddTelephone;
    EditText AddLocation;
    Button AddService;
    String phnValidPattern="[0-9]{10}";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__service);
        AddName =(EditText)findViewById(R.id.Add_Name);
        AddTelephone=(EditText)findViewById(R.id.Add_Contact);
        AddLocation=(EditText)findViewById(R.id.Add_Location);
        AddService=(Button)findViewById(R.id.update_Service);



        mydb=new DatabaseHelper(this);

            /*AddService.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(TextUtils.isEmpty(AddName.getText().toString()) || TextUtils.isEmpty(AddTelephone.getText().toString())|| TextUtils.isEmpty(AddLocation.getText().toString()) ){
                    Intent intent=new Intent(Add_Service.this,Add_Service.class);
                    startActivity(intent);}


                }
            });*/


        addService();

    }
    public void addService(){
        AddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(TextUtils.isEmpty(AddName.getText().toString()) || TextUtils.isEmpty(AddTelephone.getText().toString())|| TextUtils.isEmpty(AddLocation.getText().toString()) ) {
                    Toast.makeText(Add_Service.this, "Detected Empty Fields", Toast.LENGTH_LONG).show();
                }
               else{
                    if(AddTelephone.getText().toString().matches(phnValidPattern)){
                        boolean isInserted=mydb.insert_service(AddName.getText().toString(),AddTelephone.getText().toString(),AddLocation.getText().toString());

                        if(isInserted=true)
                            Toast.makeText(Add_Service.this,"Data inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Add_Service.this,"Data Not Inserted",Toast.LENGTH_LONG).show();}
                    else{
                        Toast.makeText(Add_Service.this,"Invalid Phone no",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
