package com.example.userlap.servicecenter;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Edit_Service extends AppCompatActivity {

    DatabaseHelper mydb2;
    EditText ID;
    EditText Name;
    EditText Contact;
    EditText location;
    Button Updatebtn,Viewbtn,GetDat;
    String phnValidPattern="[0-9]{10}";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__service);
        mydb2=new DatabaseHelper(this);
        ID=(EditText)findViewById(R.id.Service_ID);
        Name=(EditText)findViewById(R.id.Add_Name);
        Contact=(EditText)findViewById(R.id.Add_Contact);
        location=(EditText)findViewById(R.id.Add_Location);
        Updatebtn=(Button)findViewById(R.id.update_Service);
        Viewbtn=(Button)findViewById(R.id.ViewService);
        GetDat=(Button)findViewById(R.id.getDet);

        updateService();
        getData();
    }
    public void updateService(){
        Updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(ID.getText().toString()) || TextUtils.isEmpty(Name.getText().toString())|| TextUtils.isEmpty(Contact.getText().toString()) || TextUtils.isEmpty(location.getText().toString())) {
                    Toast.makeText(Edit_Service.this, "Detected Empty Fields", Toast.LENGTH_LONG).show();
                }
                else{
                    if(Contact.getText().toString().matches(phnValidPattern)){
                        boolean isUpdated=mydb2.updateService(ID.getText().toString(),Name.getText().toString(),Contact.getText().toString(),location.getText().toString());
                        if (isUpdated==true)
                            Toast.makeText(Edit_Service.this,"Data successfully updated",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Edit_Service.this,"NOT successfully updated",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(Edit_Service.this,"Invalid Phone no",Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
    }

    public void getData(){
        GetDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(ID.getText().toString())){
                    Toast.makeText(Edit_Service.this,"Please enter a valid ID",Toast.LENGTH_LONG).show();
                }
                else{
                    Cursor res=mydb2.getServiceDataUPD(ID.getText().toString());
                    if(res.getCount()==0){

                        Toast.makeText(Edit_Service.this,"No database record",Toast.LENGTH_LONG).show();
                        return;
                    }
                    while(res.moveToNext()){
                        ID.setText(res.getString(0));
                        Name.setText(res.getString(1));
                        Contact.setText(res.getString(2));
                        location.setText(res.getString(3));

                    }
                }
            }
        });
    }
}
