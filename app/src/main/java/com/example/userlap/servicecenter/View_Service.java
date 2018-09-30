package com.example.userlap.servicecenter;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class View_Service extends AppCompatActivity {
    Button View_Service;
    Button Delete_Service;
    EditText DeleteID;
    DatabaseHelper mydb1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__service);
        View_Service=(Button)findViewById(R.id.View_Service);
        Delete_Service=(Button)findViewById(R.id.Delete_Service_BTN) ;
        DeleteID=(EditText) findViewById(R.id.DeleteID);
        mydb1=new DatabaseHelper(this);
        ViewService();
        delete_Service();

    }
    public void ViewService(){
        View_Service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(View_Service.this,"Clicked",Toast.LENGTH_LONG).show();
                Cursor res=mydb1.getAllServices();
                if(res.getCount()==0){
                    ShowMsg("Error","Nothing Found on Databse");
                    //Toast.makeText(View_Service.this,"Count=0",Toast.LENGTH_LONG).show();
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(res.moveToNext()){
                    //Toast.makeText(View_Service.this,"while",Toast.LENGTH_LONG).show();
                    buffer.append("ID:"+res.getString(0)+"\n");
                    buffer.append("Service Name:"+res.getString(1)+"\n");
                    buffer.append("HotLine:"+res.getString(2)+"\n");
                    buffer.append("Location:"+res.getString(3)+"\n\n");


                }
                ShowMsg("Data",buffer.toString());
                //ShowMsg("Data",res.getString(1));
            }
        });


    }

    public void ShowMsg(String Title,String Msg){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(Title);
        builder.setMessage(Msg);
        builder.show();
        //Toast.makeText(View_Service.this,"ShowMsg",Toast.LENGTH_LONG).show();
    }

    public void delete_Service(){
        Delete_Service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedServices=mydb1.delete_Service(DeleteID.getText().toString());
                if(deletedServices>0)
                    Toast.makeText(View_Service.this,"Data Affected",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(View_Service.this,"Data not affected",Toast.LENGTH_LONG).show();
            }
        });
    }
}
